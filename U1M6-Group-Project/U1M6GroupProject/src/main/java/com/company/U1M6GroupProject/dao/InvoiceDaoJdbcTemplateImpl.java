package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Invoice;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, return_date, late_fee) values (?, ?, ?, ?)";
    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";
    private static final String SELECT_INVOICE_BY_CUSTOMER =
            "select * from invoice where customer_id = ?";
    private static final String SELECT_INVOICE_SQL =
            "select * from invoice";
    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, order_date = ?, return_date = ?, late_fee = ? where invoice_id = ?";
    private JdbcTemplate jdbcTemplate; //database handle
@Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getOrder_date(),
                invoice.getReturn_date(),
                invoice.getLate_fee(),
                invoice.getInvoice_id());
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getOrder_date(),
                invoice.getReturn_date(),
                invoice.getLate_fee());
        int id = jdbcTemplate.queryForObject("select last_insert_id()",Integer.class);
        invoice.setInvoice_id(id);
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(SELECT_INVOICE_SQL,this::mapRowToArtist);
    }

    @Override
    public void deleteInvoice(int invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL,invoice_id);
    }

    @Override
    public Invoice getInvoiceByCustomerId(int customer_id) {
    try{
        return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_CUSTOMER,this::mapRowToArtist,customer_id);
    } catch (EmptyResultDataAccessException e){
        return null;
    }
    }

    private Invoice mapRowToArtist(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setCustomer_id(rs.getInt("customer_id"));
        invoice.setOrder_date(rs.getDate("order_date").toLocalDate());
        invoice.setReturn_date(rs.getDate("return_date").toLocalDate());
        invoice.setLate_fee(rs.getBigDecimal("late_fee"));
        return invoice;
    }


}
