package br.com.pedrosa.core.port.in;

import br.com.pedrosa.core.domain.Product;
import br.com.pedrosa.core.port.out.PageQuery;
import br.com.pedrosa.core.port.out.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListProductsUseCase {
    PageResult<Product> listAll(PageQuery page);
}
