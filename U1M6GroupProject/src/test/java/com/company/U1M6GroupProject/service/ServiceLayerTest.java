package com.company.U1M6GroupProject.service;

import com.company.U1M6GroupProject.dao.*;
import com.company.U1M6GroupProject.model.Customer;
import com.company.U1M6GroupProject.model.Invoice;
import com.company.U1M6GroupProject.model.InvoiceItem;
import com.company.U1M6GroupProject.model.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    private ServiceLayer serviceLayer;
    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Before
    public void setUp() throws Exception   {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();
        serviceLayer = new ServiceLayer(customerDao, invoiceDao,itemDao,invoiceItemDao);
    }


    private void setUpCustomerDaoMock() {
        CustomerDao customerDaoMock = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("group 2");
        customer.setLast_name("group 2");
        customer.setEmail("@cognizant.com");
        customer.setCompany("cognizant");
        customer.setPhone("12345");

        Customer customer2 = new Customer();
        customer2.setFirst_name("group 2");
        customer2.setLast_name("group 2");
        customer2.setEmail("@cognizant.com");
        customer2.setCompany("cognizant");
        customer2.setPhone("12345");

        List<Customer>customers=new ArrayList<>();
        customers.add(customer);

        //get all customers
        doReturn(customers).when(customerDaoMock ).getAllCustomer();
         // customerDaoMock.getAllCustomers() will now return the list "customer".
        doReturn(customer).when(customerDaoMock).addCustomer(customer2);
        doReturn(customer).when(customerDaoMock).getCustomerById(1);
//        customer.setFirst_name("larry");
//        customer.setLast_name("yang");
//        doReturn(customer).when(customerDaoMock).updateCustomer(customer);
//        doReturn(customer).when(customerDaoMock).deleteCustomerById(1);
        this.customerDao = customerDaoMock;
    }
    private void setUpInvoiceDaoMock() {
        InvoiceDao invoiceDaoMock = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setCustomer_id(1);
        invoice.setOrder_date(LocalDate.of(2021,3,26));
        invoice.setReturn_date(LocalDate.of(2021,3,30));
        invoice.setLate_fee(new BigDecimal("22.33"));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomer_id(1);
        invoice2.setOrder_date(LocalDate.of(2021,3,26));
        invoice2.setReturn_date(LocalDate.of(2021,3,30));
        invoice2.setLate_fee(new BigDecimal("22.33"));
        List<Invoice>   invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoiceList   ).when(invoiceDaoMock).getAllInvoice()   ;
        doReturn(invoiceList).when(invoiceDaoMock).addInvoice(invoice2);
        doReturn(invoiceList).when(invoiceDaoMock).getInvoiceByCustomerId(1);
    }
    private void setUpInvoiceItemDaoMock(){
        InvoiceItemDao invoiceItemDaoMock=mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(1);
        invoiceItem.setInvoice_id(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnit_rate(new BigDecimal("2.11"));
        invoiceItem.setDiscount(new BigDecimal("2.33"));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoice_id(1);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnit_rate(new BigDecimal("2.11"));
        invoiceItem2.setDiscount(new BigDecimal("2.33"));
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItems).when(invoiceItemDaoMock).getInvoiceItemById(1);
        doReturn(invoiceItem).when(invoiceItemDaoMock).addInvoiceItem(invoiceItem2);
    }
    private void setUpItemDaoMock(){
        Item item = new Item()  ;
        ItemDao itemDaoMock = mock(ItemDaoJdbcTemplateImpl.class    );
        item.setItem_id(1);
        item.setName("group2");
        item.setDescription("this is group2");
        item.setDaily_rate(new BigDecimal("1.22"));

        Item item2 = new Item();
        item2.setName("group2");
        item2.setDescription("this is group2");
        item2.setDaily_rate(new BigDecimal("1.22"));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(itemList).when(itemDaoMock).getALlItems() ;
        doReturn(itemList).when(itemDaoMock).addItem(item2);
        doReturn(itemList).when(itemDaoMock).getItemById(1);
    }




    @Test
    public void saveInvoice() {
    }

    @Test
    public void findInvoice() {
    }

    @Test
    public void findAllInvoice() {
    }

//    @Test
//    public void updateInvoice() {
//    }
//
//    @Test
//    public void removeInvoice() {
//    }
}