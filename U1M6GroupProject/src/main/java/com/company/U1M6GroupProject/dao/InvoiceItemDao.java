package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Invoice;
import com.company.U1M6GroupProject.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem  addInvoiceItem (InvoiceItem invoiceItem);
    void deleteInvoiceItem (int invoice_item_id);
    List<InvoiceItem> getInvoiceItemById(int id);
}
