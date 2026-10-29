package br.com.pedrosa.adapter.in.controller.request;

import br.com.pedrosa.core.domain.entity.ProductEntity;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
    public ProductEntity toEntity() {
        return new ProductEntity(name, price);
    }
}
