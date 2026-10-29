package br.com.pedrosa.core.port.out;

import br.com.pedrosa.adapter.in.controller.request.ProductRequest;
import br.com.pedrosa.core.domain.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductPortOut {
    ProductDTO save(ProductRequest productRequest);
    Page<ProductDTO> listAll(Pageable page);
}
