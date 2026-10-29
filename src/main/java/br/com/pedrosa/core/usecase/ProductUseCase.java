package br.com.pedrosa.core.usecase;

import br.com.pedrosa.adapter.in.controller.request.ProductRequest;
import br.com.pedrosa.core.domain.dto.ProductDTO;
import br.com.pedrosa.core.domain.reposoitory.ProductRepository;
import br.com.pedrosa.core.port.out.ProductPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProductUseCase implements ProductPortOut {

    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO save(ProductRequest productRequest) {
        return ProductDTO.fromEntity(productRepository.save(productRequest.toEntity()));
    }

    @Override
    public Page<ProductDTO> listAll(Pageable page) {
        return productRepository.findAll(page).map(ProductDTO::fromEntity);
    }
}