package com.albabich.grad.web;

import com.albabich.grad.AuthorizedUser;
import com.albabich.grad.model.AbstractBaseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ+1;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}