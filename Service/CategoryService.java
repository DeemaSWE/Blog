package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category updatedCategory, Integer id){
        Category category = findCategoryById(id);

        category.setName(updatedCategory.getName());

        categoryRepository.save(category);
    }

    public void deleteCategory(Integer id){
        categoryRepository.delete(findCategoryById(id));
    }

    public Category findCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Category with id " + id + " not found"));
    }
}
