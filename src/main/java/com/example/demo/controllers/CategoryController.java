package com.example.demo.controllers;

import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @PostMapping("/new_category")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity addCategory(@Validated @RequestBody Category category){

        Category todo = null;

            todo = categoryService.addCategory(category);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/list_categories")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity getListCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
}
