package ru.example.service;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.*;

@Component(service = AppUserService.class)
public class AppUserService {

    public List<User> users(List<Role> roles) {
        Set<User> users = new HashSet<>();

        for (Role role : roles) {
            users.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
        }

        return new ArrayList<>(users);
    }

//    public User getUserById(int id) {
//        try {
//            User user = UserLocalServiceUtil.getService().getUserById(id);
//            return user;
//        } catch (PortalException e) {
//            throw new RuntimeException("Пользователь не найден на портале userId = " + id);
//        }
//
//    }
}
