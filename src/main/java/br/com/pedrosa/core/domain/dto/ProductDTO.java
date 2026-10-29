package br.com.pedrosa.core.domain.dto;

import br.com.pedrosa.core.domain.entity.ProductEntity;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, BigDecimal price) {
    public static ProductDTO fromEntity(ProductEntity productEnity){
        return new ProductDTO(productEnity.getId(),productEnity.getName(),productEnity.getPrice());
    }

}
