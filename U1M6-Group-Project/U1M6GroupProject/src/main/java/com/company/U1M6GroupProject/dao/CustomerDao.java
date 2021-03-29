package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomerById (int customer_id);
    List<Customer> getAllCustomer ();
    Customer addCustomer(Customer customer);
    void deleteCustomerById(int customer_id);
    void updateCustomer(Customer customer);
}
