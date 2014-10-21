package com.basecolon.FireJemblem.items;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class ItemFactory {
    public static <SomeItem extends Item> SomeItem create(Class<SomeItem> item) {
        try {
            //noinspection unchecked
            return (SomeItem) Class.forName(item.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
