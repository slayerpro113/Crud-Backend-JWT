package com.example.demo.services;

import com.example.demo.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public List<Category> findAll();
}
