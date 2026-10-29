package br.com.pedrosa.adapter.in.controller.response;

import br.com.pedrosa.core.domain.dto.ProductDTO;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, BigDecimal price) {
    public static ProductResponse fromDTO(ProductDTO dto) {
        return new ProductResponse(dto.id(), dto.name(), dto.price());
    }
}
