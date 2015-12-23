package com.demo.dao;

import com.demo.common.Const;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ResourceDao {
    private static Map<String, Collection<ConfigAttribute>> resourceMap = loadResourceMap();

    private synchronized static Map<String, Collection<ConfigAttribute>> loadResourceMap() {
        Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        final SecurityConfig scTeacher = new SecurityConfig(Const.ROLE_TEACHER);
        final SecurityConfig scStudent = new SecurityConfig(Const.ROLE_STUDENT);
        final SecurityConfig scNotice = new SecurityConfig(Const.ROLE_NOTICE);

        resourceMap.put("/student.html", new ArrayList<ConfigAttribute>(){{
            add(scStudent);
        }});

        resourceMap.put("/student/**", new ArrayList<ConfigAttribute>(){{
            add(scStudent);
        }});

        resourceMap.put("/teacher.html", new ArrayList<ConfigAttribute>(){{
            add(scTeacher);
        }});

        resourceMap.put("/class.html", new ArrayList<ConfigAttribute>(){{
            add(scStudent);
            add(scTeacher);
        }});

        resourceMap.put("/notice.html", new ArrayList<ConfigAttribute>(){{
            add(scTeacher);
            add(scNotice);
        }});

        return resourceMap;
    }

    public Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return resourceMap;
    }
}
