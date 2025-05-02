package com.dialltay.leveragess.product;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity{
        @Id
        @GeneratedValue
        private final Long id;
        private String name;
        private String owner;

        public ProductEntity() {
            this.id = null;
        }

        public String getName() {
                return name;
        }

        public String getOwner() {
                return owner;
        }
}
