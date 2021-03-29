package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Customer;
import com.company.U1M6GroupProject.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice (Invoice invoice);
    void deleteInvoice (int invoice_id);
 List<Invoice> getAllInvoice( );
    Invoice getInvoiceByCustomerId(int customer_id);
    void updateInvoice(Invoice invoice);
}
