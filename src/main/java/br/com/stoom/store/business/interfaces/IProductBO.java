package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Product;

import java.util.List;

public interface IProductBO {

    List<Product> findAll();
    Product findById(Long id);
    List<Product> findAllPublishedProducts();
    Product findPublishedById(Long id);
    List<Product> findAllPublishedProductsByKeyWord(String keyWord);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);


}
