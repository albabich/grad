package com.albabich.grad.util;

import com.albabich.grad.model.MenuItem;
import com.albabich.grad.to.MenuItemTo;

public class MenuItemUtil {
    public static MenuItem createNewFromTo(MenuItemTo menuItemTo) {
        return new MenuItem(null, null, menuItemTo.getName(),menuItemTo.getPrice());
    }

    public static MenuItem updateFromTo(MenuItem menuItem, MenuItemTo menuItemTo) {
        menuItem.setName(menuItemTo.getName());
        menuItem.setPrice(menuItemTo.getPrice());
        return menuItem;
    }
}
