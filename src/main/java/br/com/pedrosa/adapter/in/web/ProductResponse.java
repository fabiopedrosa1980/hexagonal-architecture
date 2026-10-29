package br.com.pedrosa.adapter.in.web;

import br.com.pedrosa.core.domain.Product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, BigDecimal price) {
    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(product.id(), product.name(), product.price());
    }
}
