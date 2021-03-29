package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";
    private static final String SELECT_ALL_CUSTOMER_SQL =
            "select * from customer";
    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name, email, company, phone) values (? ,?, ?, ?, ?)";
    private static final String DELETE_CUSTOMER =
            "delete from customer where customer_id = ?";
    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?, email = ?, company = ?, phone = ? where customer_id = ?";

    public CustomerDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Customer getCustomerById(int customer_id)  {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this ::mapRowToCustomer,customer_id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMER_SQL,this::mapRowToCustomer);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getPhone());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        customer.setCustomer_id(id);
        return customer;
    }

    @Override
    public void deleteCustomerById(int customer_id) {
         jdbcTemplate.update(DELETE_CUSTOMER, customer_id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,

                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getPhone(),
                customer.getCustomer_id());
    }



    private Customer mapRowToCustomer (ResultSet rs, int rowNum) throws SQLException{
        Customer customer= new Customer();
        customer.setCustomer_id(rs.getInt("customer_id"));
        customer.setFirst_name(rs.getString("first_name"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setCompany(rs.getString("company"));
        customer.setPhone(rs.getString("phone"));
        return customer;
    }
}
