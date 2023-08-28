package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryBO implements ICategoryBO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Category> findAllPublishedCategories() {
        return categoryRepository.findCategoriesByPublished(true);
    }

    @Override
    public Category findPublishedCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findCategoryByPublishedAndId(true, id);

        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Optional<Category> cData = categoryRepository.findById(id);

        if(cData.isPresent()){
            Category c = cData.get();
            c.setName(category.getName());

            return categoryRepository.save(c);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
