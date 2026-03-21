const amqp = require('amqplib');

let channel;
const QUEUE = 'commandes';

async function connectRabbitMQ(retries = 10, delay = 5000) {
  for (let i = 1; i <= retries; i++) {
    try {
      const conn = await amqp.connect('amqp://admin:admin@rabbitmq');
      channel = await conn.createChannel();
      await channel.assertQueue(QUEUE, { durable: true });
      console.log('✅ RabbitMQ connecté');
      return;
    } catch (error) {
      console.log(`⏳ Tentative RabbitMQ ${i}/${retries} échouée...`);
      if (i === retries) {
        throw error;
      }
      await new Promise((resolve) => setTimeout(resolve, delay));
    }
  }
}

function publierCommande(commande) {
  const msg = JSON.stringify(commande);
  channel.sendToQueue(QUEUE, Buffer.from(msg), { persistent: true });
  console.log('📤 Message publié dans RabbitMQ');
}

module.exports = { connectRabbitMQ, publierCommande };