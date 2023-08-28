package br.com.stoom.store.repository;

import br.com.stoom.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByPublished(Boolean published);
    Optional<Category> findCategoryByPublishedAndId(Boolean published, Long id);
}
