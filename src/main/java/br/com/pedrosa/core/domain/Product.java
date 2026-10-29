package br.com.pedrosa.core.domain;

import java.math.BigDecimal;

public record Product(Long id, String name, BigDecimal price) {
    public Product(String name, BigDecimal price) {
        this(null, name, price);
    }
}
