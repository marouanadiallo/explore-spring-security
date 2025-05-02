package com.dialltay.leveragess.document;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

public interface DocumentService {
    Document getDocument(String code);
    Document getDocumentByCode(String owner);
    Document getDocumentByOwner(String owner);
}

@Service
class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * Get a document by its code.
     * This method is secured with a post-authorization check.
     * Which use hasPermission to check if the user has the required authority.
     * --- It's our duty to implement the permission logic.
     * @see DocumentsPermissionEvaluator#hasPermission(Authentication, Object, Object)
     * @param code the code of the document
     * @return the document
     */
    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    @Override
    public Document getDocument(String code) {
        return this.documentRepository.findDocument(code);
    }

    /**
     * Get a document by its owner.
     * This method is secured with a pre-authorization check.
     * Which use hasPermission to check if the user has the required authority.
     * --- It's our duty to implement the permission logic.
     * @see DocumentsPermissionEvaluator#hasPermission(Authentication, Serializable, String, Object)
     * @param code the owner of the document
     * @return the document
     */
    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    @Override
    public Document getDocumentByCode(String code) {
        return this.documentRepository.findDocument(code);
    }

    public Document getDocumentByOwner(String owner) {
        return this.documentRepository.findDocumentByOwner(owner);
    }
}