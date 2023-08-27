package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryBO categoyService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> c = categoyService.findAll();
        if(!c.isEmpty())
            return new ResponseEntity<>(c, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        Category c = categoyService.findById(id);
        if(null!=c)
            return new ResponseEntity<>(c, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Category> createProduct(@RequestBody Category c) {
        try{
            Category _c = categoyService.save(c);
            return new ResponseEntity<>(_c, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateProduct(@PathVariable("id") Long id, @RequestBody Category c){
        Category _c = categoyService.update(id, c);
        if(null!=_c)
            return new ResponseEntity<>(_c, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id){
        try{
            categoyService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
