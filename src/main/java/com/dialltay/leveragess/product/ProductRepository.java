package com.dialltay.leveragess.product;

import com.dialltay.leveragess.SafetyMethodConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @PostFilter("filterObject.owner == authentication.name")
    List<ProductEntity> findAllByNameContains(String term);

    /**
     * Spring Security injects an authentication object. For this to work, we need to add
     * a SecurityEvaluationContextExtension bean to the context.
     * @see SafetyMethodConfig#securityEvaluationContextExtension()
     * @param term search term
     * @return List of products that the authenticated user is owned
     */
    @Query("""
            SELECT p
            FROM ProductEntity p
            WHERE p.name LIKE CONCAT('%', :term, '%')
            AND p.owner = ?#{authentication.name}
     """)
    List<ProductEntity> findProducts(String term);
}
