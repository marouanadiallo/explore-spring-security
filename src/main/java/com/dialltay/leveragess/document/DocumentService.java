package com.dialltay.leveragess.document;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

public interface DocumentService {
    Document getDocument(String code);
}

@Service
class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * Get a document by its code.
     * This method is secured with a pro-authorization check.
     * Which use hasPermission to check if the user has the required authority.
     * --- It's our duty to implement the permission logic.
     * @see PermissionEvaluator
     * @param code the code of the document
     * @return the document
     */
    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    @Override
    public Document getDocument(String code) {
        return this.documentRepository.findDocument(code);
    }
}