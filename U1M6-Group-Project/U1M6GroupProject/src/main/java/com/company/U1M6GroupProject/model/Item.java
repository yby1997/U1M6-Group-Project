package com.company.U1M6GroupProject.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private int item_id;
    private String name;
    private String description;
    private BigDecimal daily_rate;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", daily_rate=" + daily_rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(daily_rate, item.daily_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, name, description, daily_rate);
    }

}
//item_id int(11) not null auto_increment primary key,
//        name varchar(50) not null,
//        description varchar(255),
//        daily_rate decimal(8,2) not null
