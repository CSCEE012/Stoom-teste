package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);

        if(brand.isPresent()){
            return brand.get();
        }
        return null;
    }

    @Override
    public List<Brand> findAllPublishedBrands() {

        return brandRepository.findBrandsByPublished(true);
    }

    @Override
    public Brand findPublishedBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findBrandByPublishedAndId(true, id);

        if(brand.isPresent()){
            return brand.get();
        }
        return null;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Long id, Brand brand) {
        Optional<Brand> bData = brandRepository.findById(id);

        if(bData.isPresent()){
            Brand b = bData.get();
            b.setName(brand.getName());

            return brandRepository.save(b);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
