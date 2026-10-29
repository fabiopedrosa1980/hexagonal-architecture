package br.com.pedrosa.adapter.out.persistence;

import br.com.pedrosa.core.domain.Product;
import br.com.pedrosa.core.port.out.PageQuery;
import br.com.pedrosa.core.port.out.PageResult;
import br.com.pedrosa.core.port.out.ProductRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {
    private final ProductJpaRepository productJpaRepository;

    public ProductPersistenceAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }


    @Override
    public Product save(Product product) {
        return ProductMapper.toDomain(productJpaRepository.save(ProductMapper.toJpa(product)));
    }


    @Override
    public PageResult<Product> listAll(PageQuery query) {
        var page = productJpaRepository.findAll(PageRequest.of(query.page(), query.size()));
        return new PageResult<>(
                page.getContent().stream().map(ProductMapper::toDomain).toList(),
                page.getTotalElements()
        );
    }
}
