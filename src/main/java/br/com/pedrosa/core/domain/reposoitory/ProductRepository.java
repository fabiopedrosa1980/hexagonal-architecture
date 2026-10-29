package br.com.pedrosa.core.domain.reposoitory;

import br.com.pedrosa.core.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
