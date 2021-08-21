package com.albabich.grad;

import com.albabich.grad.model.User;
import com.albabich.grad.to.UserTo;
import com.albabich.grad.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public int getId() {
        return userTo.getId();
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}