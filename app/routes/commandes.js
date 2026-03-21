const express = require('express');
const router = express.Router();

const { Commande } = require('../services/mongodb');
const redis = require('../services/redis');
const { publierCommande } = require('../services/rabbitmq');

const INSTANCE = process.env.INSTANCE || 'Instance-?';
const TTL = 30;

// POST /commandes → créer une commande
router.post('/', async (req, res) => {
  try {
    const { produit, quantite, client } = req.body;

    // Validation
    if (!produit || !quantite || !client) {
      return res.status(400).json({ erreur: 'Champs manquants' });
    }

    // Sauvegarde MongoDB
    const commande = new Commande({ produit, quantite, client });
    await commande.save();

    // Invalider le cache
    await redis.del('toutes_commandes');

    // Envoyer message RabbitMQ
    publierCommande({ id: commande._id, produit, client });

    res.status(201).json({
      message: 'Commande créée',
      commande,
      traitee_par: INSTANCE
    });

  } catch (err) {
    res.status(500).json({ erreur: err.message });
  }
});

// GET /commandes → avec cache Redis
router.get('/', async (req, res) => {
  try {
    const cached = await redis.get('toutes_commandes');

    if (cached) {
      console.log('⚡ Réponse depuis Redis');
      return res.json({
        source: '⚡ CACHE Redis',
        traitee_par: INSTANCE,
        commandes: JSON.parse(cached)
      });
    }

    console.log('🗄 Réponse depuis MongoDB');

    const commandes = await Commande.find().sort({ date: -1 });

    await redis.set('toutes_commandes', JSON.stringify(commandes), 'EX', TTL);
    console.log('✅ Cache Redis enregistré');

    res.json({
      source: '🗄 MongoDB',
      traitee_par: INSTANCE,
      commandes
    });

  } catch (err) {
    res.status(500).json({ erreur: err.message });
  }
});

module.exports = router;
