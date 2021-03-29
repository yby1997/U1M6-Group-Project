package com.company.U1M6GroupProject.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    private int invoice_item_id;
    private int invoice_id;
    private int quantity;
    private BigDecimal unit_rate;
    private BigDecimal discount;

    public int getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_rate() {
        return unit_rate;
    }

    public void setUnit_rate(BigDecimal unit_rate) {
        this.unit_rate = unit_rate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoice_item_id == that.invoice_item_id && invoice_id == that.invoice_id && quantity == that.quantity && Objects.equals(unit_rate, that.unit_rate) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_item_id, invoice_id, quantity, unit_rate, discount);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoice_item_id=" + invoice_item_id +
                ", invoice_id=" + invoice_id +
                ", quantity=" + quantity +
                ", unit_rate=" + unit_rate +
                ", discount=" + discount +
                '}';
    }
}

