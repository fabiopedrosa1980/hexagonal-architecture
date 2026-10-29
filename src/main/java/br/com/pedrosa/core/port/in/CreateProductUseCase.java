package br.com.pedrosa.core.port.in;

import br.com.pedrosa.core.domain.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
