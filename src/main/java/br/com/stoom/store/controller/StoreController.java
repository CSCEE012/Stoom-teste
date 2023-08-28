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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private ProductBO productService;

    @Autowired
    private CategoryBO categoryService;

    @Autowired
    private BrandBO brandService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> findAllPublishedProducts() {
        List<Product> p = productService.findAllPublishedProducts();
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> findPublishedProductById(@PathVariable("id") Long id) {
        Product p = productService.findPublishedById(id);
        if(null!=p)
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories/{id}/products")
    public ResponseEntity<List<Product>> findAllPublishedProductsByCategoryId(@PathVariable("id") Long id){
        Category c = categoryService.findPublishedCategoryById(id);

        List<Product> p = c.getProducts().stream().collect(Collectors.toList());

        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/brands/{id}/products")
    public ResponseEntity<List<Product>> findAllPublishedProductsByBrandId(@PathVariable("id") Long id){
        Brand b = brandService.findPublishedBrandById(id);

        List<Product> p = b.getProducts().stream().collect(Collectors.toList());

        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> findAllPublishedCategories() {
        List<Category> c = categoryService.findAllPublishedCategories();
        if(!c.isEmpty())
            return new ResponseEntity<>(c, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Category> findPublishedCategoryById(@PathVariable("id") Long id) {
        Category c = categoryService.findPublishedCategoryById(id);
        if(null!=c)
            return new ResponseEntity<>(c, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/brands")
    public ResponseEntity<List<Brand>> findAllPublishedBrands(){
        List<Brand> b = brandService.findAllPublishedBrands();
        if(!b.isEmpty()){
            return new ResponseEntity<>(b, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<Brand> findPublishedBrandById(@PathVariable("id") Long id) {
        Brand b = brandService.findPublishedBrandById(id);
        if(null!=b)
            return new ResponseEntity<>(b, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "search/{keyword}")
    public ResponseEntity<List<Product>> findPublishedProductsByKeyWord(@PathVariable("keyword") String keyWord){
        List<Product> p = productService.findAllPublishedProductsByKeyWord(keyWord);

        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
