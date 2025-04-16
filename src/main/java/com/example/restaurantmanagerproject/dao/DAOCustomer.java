package com.example.restaurantmanagerproject.dao;

import com.example.restaurantmanagerproject.model.Customer;
import java.util.List;

public interface DAOCustomer {

    Customer findCustomerById(String id);

    List<Customer> findAllCustomers();

    boolean saveCustomer(Customer customer);

    boolean deleteCustomer(String id);
}
