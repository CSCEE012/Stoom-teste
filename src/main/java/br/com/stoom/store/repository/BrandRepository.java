package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findBrandsByPublished(Boolean published);
    Optional<Brand> findBrandByPublishedAndId(Boolean published, Long id);
}
