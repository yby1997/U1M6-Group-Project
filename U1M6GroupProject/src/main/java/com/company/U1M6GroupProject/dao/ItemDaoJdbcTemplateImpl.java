package com.company.U1M6GroupProject.dao;

import com.company.U1M6GroupProject.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {
    private static final String INSERT_ITEM_SQL =
            "insert into item (name, description, daily_rate) values (?, ?, ?)";
    private static final String SELECT_ITEM_SQL =
            "select * from item where item_id = ?";
    private static final String SELECT_ALL_ITEMS_SQL =
            "select * from item";
    private static final String UPDATE_ITEM_SQL =
            "update item set name = ?, description = ?, daily_rate = ? where item_id = ?";
    private static final String DELETE_ITEM_SQL =
            "delete from item where item_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item addItem(Item item) {
        jdbcTemplate.update(INSERT_ITEM_SQL,
                item.getName(),
                item.getDescription(),
                item.getDaily_rate());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        item.setItem_id(id);
        return item;
    }

    @Override
    public Item getItemById(int item_id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ITEM_SQL, this::mapRowToItem, item_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> getALlItems() {
        return jdbcTemplate.query(SELECT_ALL_ITEMS_SQL, this::mapRowToItem);
    }

    @Override
    public void deleteItemById(int item_id) {
        jdbcTemplate.update(DELETE_ITEM_SQL, item_id);
    }

    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(UPDATE_ITEM_SQL,

                item.getName(),
                item.getDescription(),
                item.getDaily_rate(),
                item.getItem_id());
    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItem_id(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setDaily_rate(rs.getBigDecimal("daily_rate"));
        return item;
    }


}
