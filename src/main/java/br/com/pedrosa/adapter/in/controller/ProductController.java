package br.com.pedrosa.adapter.in.controller;

import br.com.pedrosa.adapter.in.controller.request.ProductRequest;
import br.com.pedrosa.adapter.in.controller.response.ProductResponse;
import br.com.pedrosa.core.port.out.ProductPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductPortOut productPortOut;

    public ProductController(ProductPortOut productPortOut) {
        this.productPortOut = productPortOut;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return ProductResponse.fromDTO(productPortOut.save(productRequest));
    }

    @GetMapping
    public Page<ProductResponse> listAll(Pageable page) {
        return productPortOut.listAll(page).map(ProductResponse::fromDTO);
    }
}
