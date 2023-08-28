package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByPublished(Boolean published);
    Optional<Product> findProductByPublishedAndId(Boolean published, Long id);


    List<Product> findProductsByNameContainingIgnoreCaseOrSkuContainingIgnoreCaseAndPublished(String name, String sku, Boolean published);
}