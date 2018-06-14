package com.example.demo.repositories;

import com.example.demo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public List<Category> findAll();
    public Category findByCategoryName(String categoryName);

}
