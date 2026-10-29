package br.com.pedrosa.adapter.out.persistence;

import br.com.pedrosa.core.domain.Product;

public class ProductMapper {
    private ProductMapper() {
    }

    static ProductJpaEntity toJpa(Product product) {
        return new ProductJpaEntity(product.name(), product.price());
    }

    static Product toDomain(ProductJpaEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }
}
