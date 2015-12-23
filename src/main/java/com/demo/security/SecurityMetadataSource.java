package com.demo.security;

import com.demo.dao.ResourceDao;
import com.demo.security.tools.AntUrlPathMatcher;
import com.demo.security.tools.UrlMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.Map;

public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    private ResourceDao resourceDao = new ResourceDao();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public SecurityMetadataSource() {
        resourceMap = resourceDao.getResourceMap();
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation)object).getRequestUrl();

        for(String resURL : resourceMap.keySet()) {
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
