package com.dialltay.leveragess.document;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    public DocumentsPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

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


    /**
     * @param authentication spring security provided an authentication object automatically
     * @param targetId the subject to authorization rule (e.g method parameter)
     * @param targetType the type of the subject
     * @param permission extra details needed for implementing the permission logic
     * @return true if the user has the permission
     */
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        String code = (String) targetId;
        String permissionStr = (String) permission;

        Document document = this.documentRepository.findDocument(code);

        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(authz -> authz.getAuthority().equals(permissionStr));

        return hasPermission || document.owner().equals(authentication.getName());
    }
}
