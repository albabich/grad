package com.albabich.grad;

import com.albabich.grad.model.User;
import com.albabich.grad.to.UserTo;
import com.albabich.grad.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
//    @Serial
//    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}