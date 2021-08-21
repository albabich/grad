package com.albabich.grad.util;

import com.albabich.grad.model.User;
import com.albabich.grad.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserUtil {
    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

//    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
//        String password = user.getPassword();
//        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
//        user.setEmail(user.getEmail().toLowerCase());
//        return user;
//    }
}
