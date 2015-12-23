package com.demo.dao;

import com.demo.common.Const;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, User> userMap = loadUser();

    private synchronized static Map<String, User> loadUser() {
        Map<String, User> userInfoMap = new HashMap<String, User>();
        final GrantedAuthority gaTeacher = new SimpleGrantedAuthority(Const.ROLE_TEACHER);
        final GrantedAuthority gaStudent = new SimpleGrantedAuthority(Const.ROLE_STUDENT);
        final GrantedAuthority gaNotice = new SimpleGrantedAuthority(Const.ROLE_NOTICE);

        userInfoMap.put(
                "teacher",
                new User("teacher", "teacher", true, true, true, true, new ArrayList<GrantedAuthority>() {{
                    add(gaTeacher);
                    add(gaNotice);
                }})
        );

        userInfoMap.put(
                "student",
                new User("student", "student", true, true, true, true, new ArrayList<GrantedAuthority>() {{
                    add(gaStudent);
                }})
        );

        userInfoMap.put(
                "notice",
                new User("notice", "notice", true, true, true, true, new ArrayList<GrantedAuthority>() {{
                    add(gaNotice);
                }})
        );
        return userInfoMap;
    }

    public User getUser(String username) {
        return userMap.get(username);
    }
}
