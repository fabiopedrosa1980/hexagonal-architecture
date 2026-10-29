package br.com.pedrosa.core.service;

import br.com.pedrosa.core.domain.Product;
import br.com.pedrosa.core.port.in.CreateProductCommand;
import br.com.pedrosa.core.port.in.CreateProductUseCase;
import br.com.pedrosa.core.port.in.ListProductsUseCase;
import br.com.pedrosa.core.port.out.PageQuery;
import br.com.pedrosa.core.port.out.PageResult;
import br.com.pedrosa.core.port.out.ProductRepositoryPort;

public class ProductService implements CreateProductUseCase, ListProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product create(CreateProductCommand command) {
        return productRepositoryPort.save(new Product(command.name(),command.price()));
    }

    @Override
    public PageResult<Product> listAll(PageQuery page) {
        return productRepositoryPort.listAll(page);
    }
}
