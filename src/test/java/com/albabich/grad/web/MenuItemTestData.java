package com.albabich.grad.web;

import com.albabich.grad.model.MenuItem;

import java.time.Month;
import java.util.List;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class MenuItemTestData {
    public static final MatcherFactory<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant");;

    public static final int NOT_FOUND = 10;
    public static final int MENU_ITEM1_ID = START_SEQ + 7;
    public static final MenuItem menuItem1 = new MenuItem(MENU_ITEM1_ID, now(), "салат", 25000);
    public static final MenuItem menuItem2 = new MenuItem(MENU_ITEM1_ID + 1, now(), "хачапури", 28000);
    public static final MenuItem menuItem3 = new MenuItem(MENU_ITEM1_ID + 2, now(), "лобио", 20000);
    public static final MenuItem menuItem4 = new MenuItem(MENU_ITEM1_ID + 3, of(2021, Month.MARCH, 24), "пхали", 5000);
    public static final MenuItem menuItem5 = new MenuItem(MENU_ITEM1_ID + 4, of(2021, Month.MARCH, 24), "вино", 26000);
    public static final MenuItem menuItem6 = new MenuItem(MENU_ITEM1_ID + 5, of(2021, Month.MARCH, 24), "салат", 23050);
    public static final MenuItem menuItem7 = new MenuItem(MENU_ITEM1_ID + 6, of(2021, Month.MARCH, 24), "ребрышки BBQ", 55050);
    public static final MenuItem menuItem8 = new MenuItem(MENU_ITEM1_ID + 7, now(), "стейк", 75050);
    public static final MenuItem menuItem9 = new MenuItem(MENU_ITEM1_ID + 8, of(2021, Month.MARCH, 24), "шашлык", 45000);
    public static final MenuItem menuItem10 = new MenuItem(MENU_ITEM1_ID + 9, of(2021, Month.MARCH, 24), "пиво", 29000);
    public static final MenuItem menuItem11 = new MenuItem(MENU_ITEM1_ID + 10, of(2021, Month.MARCH, 24), "салат", 20000);
    public static final MenuItem menuItem12 = new MenuItem(MENU_ITEM1_ID + 11, of(2021, Month.MARCH, 24), "рулька", 65000);
    public static final MenuItem menuItem13 = new MenuItem(MENU_ITEM1_ID + 12, now(), "курица", 45000);
    public static final MenuItem menuItem14 = new MenuItem(MENU_ITEM1_ID + 13, of(2021, Month.MARCH, 24), "стейк рибай", 95000);
    public static final MenuItem menuItem15 = new MenuItem(MENU_ITEM1_ID + 14, of(2021, Month.MARCH, 24), "пиво", 35000);

    public static final List<MenuItem> menuItems = List.of(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5,
            menuItem6, menuItem7, menuItem8, menuItem9, menuItem10, menuItem11, menuItem12, menuItem13, menuItem14, menuItem15);

    public static final List<MenuItem> menuItemsRest1Today = List.of( menuItem1, menuItem2, menuItem3);
    public static final List<MenuItem> menuItemsRest2Today = List.of( menuItem8);
    public static final List<MenuItem> menuItemsRest3Today = List.of( menuItem13);

    public static MenuItem getNew() {
        return new MenuItem(null, now(), "newMenuItem", 59900);
    }

    public static MenuItem getUpdated() {
        MenuItem updated = new MenuItem(menuItem1);
        updated.setName("UpdatedMenuItem");
        updated.setPrice(52500);
        updated.setDate(of(2021, Month.APRIL, 24));
        return updated;
    }
}
