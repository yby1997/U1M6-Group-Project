package com.company.U1M6GroupProject.service;

import com.company.U1M6GroupProject.dao.CustomerDao;
import com.company.U1M6GroupProject.dao.InvoiceDao;
import com.company.U1M6GroupProject.dao.InvoiceItemDao;
import com.company.U1M6GroupProject.dao.ItemDao;
import com.company.U1M6GroupProject.model.Customer;
import com.company.U1M6GroupProject.model.Invoice;
import com.company.U1M6GroupProject.model.InvoiceItem;
import com.company.U1M6GroupProject.model.Item;
import com.company.U1M6GroupProject.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;


    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(invoiceViewModel.getCustomer().getCustomer_id());
        invoice.setOrder_date(invoiceViewModel.getOrder_date());
        invoice.setReturn_date(invoiceViewModel.getReturn_date());
        invoice.setLate_fee(invoiceViewModel.getLate_fee());
        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setId(invoice.getInvoice_id());

        List<InvoiceItem> invoiceItemList = invoiceViewModel.getItemList();
        invoiceItemList.stream().forEach(i -> {
            i.setInvoice_item_id(invoiceViewModel.getId());
            invoiceItemDao.addInvoiceItem(i);
        });

        invoiceItemList = invoiceItemDao.getInvoiceItemById(invoiceViewModel.getId());
        invoiceViewModel.setItemList(invoiceItemList);
        return invoiceViewModel;
    }

    private InvoiceViewModel buildInoviceViewModel(Invoice invoice) {
        //get associated Customer
        Customer customer = customerDao.getCustomerById(invoice.getCustomer_id());
        //get invoice item
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemById(invoice.getInvoice_id());

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getInvoice_id());
        ivm.setCustomer(customer);
        ivm.setOrder_date(invoice.getOrder_date());
        ivm.setReturn_date(invoice.getReturn_date());
        ivm.setLate_fee(invoice.getLate_fee());
        ivm.setItemList(invoiceItemList);
        return ivm;
    }

    public InvoiceViewModel findInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoiceByCustomerId(id);
        return buildInoviceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoice() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel ivm = buildInoviceViewModel(invoice);
            invoiceViewModels.add(ivm);
        }
        return invoiceViewModels;
    }

    @Transactional
    public void updateInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(invoiceViewModel.getId());
        invoice.setCustomer_id(invoiceViewModel.getCustomer().getCustomer_id());
        invoice.setReturn_date(invoiceViewModel.getReturn_date());
        invoice.setLate_fee(invoiceViewModel.getLate_fee());

        invoiceDao.updateInvoice(invoice);

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemById(invoice.getInvoice_id());
        invoiceItemList.stream().forEach(a -> invoiceItemDao.deleteInvoiceItem(a.getInvoice_item_id()));

        List<InvoiceItem> invoiceItems = invoiceViewModel.getItemList();
        invoiceItems.stream().forEach(i -> {
            i.setInvoice_id(invoiceViewModel.getId());
            i = invoiceItemDao.addInvoiceItem(i);
        });
    }

    @Transactional
    public void removeInvoice(int id) {

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemById(id);

        invoiceItemList.stream().forEach(i -> invoiceItemDao.deleteInvoiceItem(i.getInvoice_item_id()));

        invoiceDao.deleteInvoice(id);
    }

}
