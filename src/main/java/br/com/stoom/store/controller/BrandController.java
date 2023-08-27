package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Brand>> findAll(){
        List<Brand> b = brandService.findAll();
        if(!b.isEmpty()){
            return new ResponseEntity<>(b, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Brand> findById(@PathVariable("id") Long id) {
        Brand b = brandService.findById(id);
        if(null!=b)
            return new ResponseEntity<>(b, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Brand> createProduct(@RequestBody Brand b) {
        try{
            Brand _b = brandService.save(b);
            return new ResponseEntity<>(_b, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Brand> updateProduct(@PathVariable("id") Long id, @RequestBody Brand b){
        Brand _p = brandService.update(id, b);
        if(null!=_p)
            return new ResponseEntity<>(_p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id){
        try{
            brandService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
