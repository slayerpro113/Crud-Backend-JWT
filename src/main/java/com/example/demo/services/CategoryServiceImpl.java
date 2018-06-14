package com.example.demo.services;

import com.example.demo.constants.ErrorMessageConstants;
import com.example.demo.constants.SuccessMessageContants;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    private String checkCategoryValid(Category category) {
        if (category.getCategoryName() == null || category.getCategoryName().equals("")) {
            return ErrorMessageConstants.BOOK_CATEGORY_INVALID;
        }
        return SuccessMessageContants.MODEL_VALID;
    }

    @Override
    public Category addCategory(Category category) {
        if (checkCategoryValid(category).equals(SuccessMessageContants.MODEL_VALID)) {
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
