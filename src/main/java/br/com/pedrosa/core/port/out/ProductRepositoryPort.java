package br.com.pedrosa.core.port.out;

import br.com.pedrosa.core.domain.Product;


public interface ProductRepositoryPort {
    Product save(Product product);
    PageResult<Product> listAll(PageQuery page);
}
