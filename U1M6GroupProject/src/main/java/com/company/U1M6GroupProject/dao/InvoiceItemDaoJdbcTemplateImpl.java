package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {
    private static final String INSERT_INVOICEITEM_SQL =
            "insert into invoice_item (invoice_id, quantity, unit_rate, discount) values (?, ?, ?, ?)";
    private static final String DELETE_INVOICEITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";
    private static final String SELECT_INVOICEITEM_BY_ID =
            "select from invoice_item where invoice_item_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Override
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICEITEM_SQL,
                invoiceItem.getInvoice_id(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnit_rate(),
                invoiceItem.getDiscount());
        int id = jdbcTemplate.queryForObject("select_last_insert_id()", Integer.class);
        invoiceItem.setInvoice_item_id(id);
        return invoiceItem;
    }

    @Override
    public void deleteInvoiceItem(int invoice_item_id) {
        jdbcTemplate.update(DELETE_INVOICEITEM_SQL, invoice_item_id);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemById(int id) {
        return jdbcTemplate.query(SELECT_INVOICEITEM_BY_ID, this::mapRowTocar,id);
    }

    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private InvoiceItem mapRowTocar(ResultSet rs, int rowNum) throws SQLException{
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoice_id(rs.getInt("invoice_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnit_rate(rs.getBigDecimal("unit_rate"));
        invoiceItem.setDiscount(rs.getBigDecimal("discount"));
        return invoiceItem;
    }
}
