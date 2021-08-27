package com.albabich.grad.web.user;

import com.albabich.grad.model.Role;
import com.albabich.grad.model.User;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User user1 = new User(USER_ID, "User1", "user1@mail.ru", "password1", Role.USER);
    public static final User user2 = new User(USER_ID + 1, "User2", "user2@mail.ru", "password2", Role.USER);
    public static final User user3 = new User(USER_ID + 2, "User3", "user3@mail.ru", "password3", Role.USER);
}
