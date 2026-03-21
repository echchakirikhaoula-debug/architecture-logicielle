package com.ecommerce.monolith.customer.repository;

import com.ecommerce.monolith.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
