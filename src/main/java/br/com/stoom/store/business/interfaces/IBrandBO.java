package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;

import java.util.List;

public interface IBrandBO {
    List<Brand> findAll();
    Brand findById(Long id);
    List<Brand> findAllPublishedBrands();
    Brand findPublishedBrandById(Long id);
    Brand save(Brand brand);
    Brand update(Long id, Brand brand);
    void delete(Long id);
}
