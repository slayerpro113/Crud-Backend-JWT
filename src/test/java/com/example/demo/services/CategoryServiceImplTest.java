package com.example.demo.services;


import com.example.demo.FlamingoApplication;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlamingoApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    /*---------addCategory(Category category)---------*/
    @Test
    public void whenAddCategory_withCategoryIsValid_thenReturnCategoryObject(){
        Category category = new Category();
        category.setCategoryName("Enime");
        assertEquals(category, categoryService.addCategory(category));
    }

    @Test
    public void whenAddCategory_withCategoryNameIsInvalid_thenReturnCategoryNameIsInvalidError(){
        Category category = new Category();
        category.setCategoryName("");

        assertEquals(null, categoryService.addCategory(category));
    }

    @Test
    public void whenAddCategory_withCategoryNameIsNull_thenReturnCategoryNameIsInvalidError(){
        Category category = new Category();
        category.setCategoryName(null);

        assertEquals(null, categoryService.addCategory(category));
    }

}
