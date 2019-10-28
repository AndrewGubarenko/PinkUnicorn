package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public static PlainUser UserToPlain (User user) {
        PlainUser result = new PlainUser();

        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setPhone(user.getPhone());
        result.setRoles(user.getRoles());
        if(!(user.getWishList() == null)) {
            result.setWishList(user.getWishList().stream().map(product -> ProductConverter.ProductToPlain(product)).collect(Collectors.toList()));
        } else {
            result.setWishList(new ArrayList<>());
        }

        return result;
    }

    public static User PlainToUser (PlainUser plainUser) {
        User result = new User();

        result.setId(plainUser.getId());
        result.setEmail(plainUser.getEmail());
        result.setPassword(plainUser.getPassword());
        result.setPhone(plainUser.getPhone());
        result.setRoles(plainUser.getRoles());
        result.setWishList(plainUser.getWishList().stream().map(planeProduct -> ProductConverter.PlainToProduct(planeProduct)).collect(Collectors.toList()));

        return result;
    }
}
