package ru.example.service;


import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component(service = AppUserService.class)
public class AppUserService {


    public List<User> users(List<Role> roles) {
        Set<User> users = new HashSet<>();

        for (Role role : roles) {
            users.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
        }

        return new ArrayList<>(users);
    }

    public User getUserById(long id) {
        List<User> users = UserLocalServiceUtil.getService().getUsers(-1, -1);
        User user =  users.stream().filter(u -> u.getUserId() == id)
                .findAny().orElse(null);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("Пользователь не найден на портале userId = " + id);
        }
    }
}
