package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    @Autowired
    private CategoryBO categoryService;

    @Autowired
    private BrandBO brandService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> p = productService.findAll();
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product p = productService.findById(id);
        if(null!=p)
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Product> createProduct(@RequestBody Product p) {
        try{
            Product _p = productService.save(p);
            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product p){
        Product _p = productService.update(id, p);
        if(null!=_p)
            return new ResponseEntity<>(_p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id){
        try{
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}/categories")
    public ResponseEntity<Product> addCategory(@PathVariable("id") Long id, @RequestBody Category c){
        Product pData = productService.findById(id);

        if(pData != null){
            long cId = c.getId();
            if(cId != 0L){
                Category cData = categoryService.findById(cId);
                if(cData == null ){
                    categoryService.save(c);
                }

                if(!pData.getCategories().stream().anyMatch(cat -> cat.getId() == cId)){
                    pData.addCategory(cData);
                }

                productService.save(pData);
                return new ResponseEntity<>(pData, HttpStatus.OK);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/{id}/categories")
    public ResponseEntity<Product> removeCategory(@PathVariable("id") Long id, @RequestBody Category c){
        Product pData = productService.findById(id);

        if(pData != null){
            long cId = c.getId();
            if(cId != 0L){
                Category cData = categoryService.findById(cId);
                if(cData != null ) {
                    pData.removeCategory(cId);
                    productService.save(pData);
                    return new ResponseEntity<>(pData, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping(value = "/{id}/brands")
    public ResponseEntity<Product> addBrand(@PathVariable("id") Long id, @RequestBody Brand b){
        Product pData = productService.findById(id);

        if(pData != null){
            long bId = b.getId();
            if(bId != 0L){
                Brand bData = brandService.findById(bId);
                if(bData == null ){
                    brandService.save(b);
                }

                if(!pData.getCategories().stream().anyMatch(cat -> cat.getId() == bId)){
                    pData.addBrand(bData);
                }

                productService.save(pData);
                return new ResponseEntity<>(pData, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/{id}/brands")
    public ResponseEntity<Product> removeBrand(@PathVariable("id") Long id, @RequestBody Brand b) {
        Product pData = productService.findById(id);

        if (pData != null) {
            long bId = b.getId();
            if (bId != 0L) {
                Brand bData = brandService.findById(bId);
                if (bData != null) {
                    pData.removeBrand(bId);
                    productService.save(pData);
                    return new ResponseEntity<>(pData, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
