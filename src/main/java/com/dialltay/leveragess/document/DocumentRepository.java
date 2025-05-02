package com.dialltay.leveragess.document;

import org.springframework.stereotype.Repository;

import java.util.Map;

public interface DocumentRepository {
    Document findDocument(String code);
    Document findDocumentByOwner(String owner);
}

@Repository
class DocumentRepositoryImpl implements DocumentRepository {
    private final Map<String, Document> documents = Map.of(
            "doc12", new Document("alphamar"),
            "doc21", new Document("betamar"),
            "doc00", new Document("unknown")
    );

    @Override
    public Document findDocument(String code) {
        return this.documents.get(code);
    }

    @Override
    public Document findDocumentByOwner(String owner) {
        return this.documents.values().stream()
                .filter(document -> document.owner().equals(owner))
                .findFirst()
                .orElse(new Document("unknown"));
    }
}