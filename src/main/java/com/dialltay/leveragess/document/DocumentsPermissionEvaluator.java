package com.dialltay.leveragess.document;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    /**
     * @param authentication spring security provided an authentication object automatically
     * @param targetDomainObject the subject to authorization rule
     * @param permission extra details needed for implementing the permission logic
     * @return true if the user has the permission
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String permissionStr = (String) permission;

        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(authz -> authz.getAuthority().equals(permissionStr));

        return hasPermission || document.owner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
