package br.com.pedrosa.adapter.in.web;

import br.com.pedrosa.core.port.in.CreateProductCommand;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
    public CreateProductCommand toCommand() {
        return new CreateProductCommand(name, price);
    }
}
