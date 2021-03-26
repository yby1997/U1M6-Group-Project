package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Customer;
import com.company.U1M6GroupProject.model.Item;

import java.util.List;

public interface ItemDao {
    Item addItem (Item item);
    Item getItemById(int item_id);
    List <Item> getALlItems ();
    void deleteItemById(int item_id);
    void updateItem (Item item);

}
