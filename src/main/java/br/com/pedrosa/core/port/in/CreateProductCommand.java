package br.com.pedrosa.core.port.in;

import java.math.BigDecimal;

public record CreateProductCommand(String name, BigDecimal price) {}
