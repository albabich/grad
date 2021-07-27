package com.albabich.grad.web.menuitem;

import com.albabich.grad.model.MenuItem;
import com.albabich.grad.repository.MenuItemRepository;
import com.albabich.grad.util.exception.NotFoundException;
import com.albabich.grad.web.AbstractControllerTest;
import com.albabich.grad.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.albabich.grad.util.ValidationUtil.checkNotFoundWithId;
import static com.albabich.grad.web.MenuItemTestData.*;
import static com.albabich.grad.web.RestaurantTestData.REST1_ID;
import static com.albabich.grad.web.TestUtil.readFromJson;
import static com.albabich.grad.web.TestUtil.userHttpBasic;
import static com.albabich.grad.web.UserTestData.admin;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminMenuItemRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuItemRestController.REST_URL + '/';

    @Autowired
    MenuItemRepository menuItemRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID + "/menu-items/" + MENU_ITEM1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(menuItem1));
    }

    @Test
    void createWithLocation() throws Exception {
        MenuItem newMenuItem = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + REST1_ID + "/menu-items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItem))
                .with(userHttpBasic(admin)))
                .andDo(print());

        MenuItem created = readFromJson(action, MenuItem.class);
        int newId = created.id();
        newMenuItem.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);

        MenuItem actualMenuItem = menuItemRepository.findById(newId).orElse(null);
        MENU_ITEM_MATCHER.assertMatch(actualMenuItem, newMenuItem);
    }

    @Test
    void update() throws Exception {
        MenuItem updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + REST1_ID + "/menu-items/" + MENU_ITEM1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());

        MenuItem actualMenuItem = menuItemRepository.findById(MENU_ITEM1_ID).orElse(null);
        MENU_ITEM_MATCHER.assertMatch(actualMenuItem, updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + REST1_ID + "/menu-items/" + MENU_ITEM1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());

        MenuItem deleted = menuItemRepository.findById(MENU_ITEM1_ID).orElse(null);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(deleted, MENU_ITEM1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + REST1_ID + "/menu-items/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }
}