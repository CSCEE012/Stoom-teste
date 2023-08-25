package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> pData = productRepository.findById(id);

        if(pData.isPresent()){
            Product p = pData.get();
            p.setSku(product.getSku());

            return productRepository.save(p);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
