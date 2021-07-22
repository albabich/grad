package com.albabich.grad.web;

import com.albabich.grad.model.Role;
import com.albabich.grad.model.User;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingEqualsComparator(User.class);

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User user1 = new User(USER_ID, "User1", "user1@mail.ru",  "password1", Role.USER);
    public static final User user2 = new User(USER_ID+1, "User2", "user2@mail.ru",  "password2", Role.USER);
    public static final User user3 = new User(USER_ID+2, "User3", "user3@mail.ru",  "password3", Role.USER);

//    public static User getNew() {
//        return new User(null, "New",  "newPass", 1555, false, new Date(), Collections.singleton(Role.USER));
//    }
//
//    public static User getUpdated() {
//        User updated = new User(user);
//        updated.setName("UpdatedName");
//        updated.setPassword("newPass");
//        updated.setRoles(Collections.singletonList(Role.ADMIN));
//        return updated;
//    }
}
