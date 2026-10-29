package br.com.pedrosa.config;

import br.com.pedrosa.core.port.out.ProductRepositoryPort;
import br.com.pedrosa.core.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }
}
