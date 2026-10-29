package br.com.pedrosa.adapter.in.web;

import br.com.pedrosa.core.port.in.CreateProductUseCase;
import br.com.pedrosa.core.port.in.ListProductsUseCase;
import br.com.pedrosa.core.port.out.PageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, ListProductsUseCase listProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse save(@RequestBody ProductRequest request) {
        return ProductResponse.fromDomain(createProductUseCase.create(request.toCommand()));
    }

    @GetMapping
    public Page<ProductResponse> list(Pageable pageable) {
        var result = listProductsUseCase.listAll(new PageQuery(pageable.getPageNumber(), pageable.getPageSize()));
        return new PageImpl<>(
                result.content().stream().map(ProductResponse::fromDomain).toList(),
                pageable,
                result.totalElements()
        );
    }
}
