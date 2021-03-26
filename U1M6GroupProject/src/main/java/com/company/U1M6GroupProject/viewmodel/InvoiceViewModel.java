package com.company.U1M6GroupProject.viewmodel;

import com.company.U1M6GroupProject.model.Customer;
import com.company.U1M6GroupProject.model.InvoiceItem;
import com.company.U1M6GroupProject.model.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
    private int id;
    private Customer customer;
    private LocalDate order_date;
    private LocalDate return_date;
    private BigDecimal late_fee;
    private List<InvoiceItem> itemList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public BigDecimal getLate_fee() {
        return late_fee;
    }

    public void setLate_fee(BigDecimal late_fee) {
        this.late_fee = late_fee;
    }

    public List<InvoiceItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<InvoiceItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return id == that.id && Objects.equals(customer, that.customer) && Objects.equals(order_date, that.order_date) && Objects.equals(return_date, that.return_date) && Objects.equals(late_fee, that.late_fee) && Objects.equals(itemList, that.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, order_date, return_date, late_fee, itemList);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "id=" + id +
                ", customer=" + customer +
                ", order_date=" + order_date +
                ", return_date=" + return_date +
                ", late_fee=" + late_fee +
                ", itemList=" + itemList +
                '}';
    }
}
