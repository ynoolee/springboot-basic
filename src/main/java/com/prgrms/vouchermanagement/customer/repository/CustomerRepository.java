package com.prgrms.vouchermanagement.customer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.prgrms.vouchermanagement.customer.domain.Customer;

public interface CustomerRepository {
	Customer insert(Customer customer);

	Customer update(Customer customer);

	List<Customer> findAll();

	Optional<Customer> findById(UUID customerId);

	Optional<Customer> findByName(String name);

	Optional<Customer> findByEmail(String email);

	long deleteById(UUID customerId);

	long deleteAll();

}