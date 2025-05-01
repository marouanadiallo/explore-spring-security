package com.dialltay.leveragess.document;

import org.springframework.stereotype.Repository;

import java.util.Map;

public interface DocumentRepository {
    Document findDocument(String code);
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
}