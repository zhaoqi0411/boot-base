package com.papaxiong.support.model;

import lombok.Data;

import java.util.List;

/**
 * @author zhaoqi
 * @since 2020-11-09
 */
@Data
public class ItemList<T>{

    private List<T> items;

    ItemList(List<T> items) {
        this.items = items;
    }

    public static <T> ItemList<T> buildItems(List<T> items) {
        ItemList<T> itemList = new ItemList<>(items);
        return itemList;
    }


}
