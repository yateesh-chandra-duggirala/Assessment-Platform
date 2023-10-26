package com.capstoneproject.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category1", "Category Description1"));
        categories.add(new Category(2L, "Category2", "Category Description2"));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDtos = categoryService.getCategories();
        assertEquals(2, categoryDtos.size());
        assertEquals("Category1", categoryDtos.get(0).getCategoryName());
        assertEquals("Category Description2", categoryDtos.get(1).getDescription());
    }

    @Test
    public void testaddCategory() {
        CategoryDTO categoryDto = new CategoryDTO(1L, "New Category", "New Description");
        when(categoryRepository.getCategoryByName("New Category")).thenReturn(Optional.empty());

        CategoryDTO addedCategory = categoryService.addCategory(categoryDto);
        assertEquals(1L, addedCategory.getCategoryId());
        assertEquals("New Category", addedCategory.getCategoryName());
        assertEquals("New Description", addedCategory.getDescription());
    }
    
    @Test
    public void testAddCategoryAlreadyExists() {
        CategoryDTO categoryDto = new CategoryDTO(null, "Existing Category", "Description");
        when(categoryRepository.getCategoryByName("Existing Category")).thenReturn(Optional.of(new Category()));

        assertThrows(AlreadyExistsException.class, () -> categoryService.addCategory(categoryDto));
    }

    @Test
    public void testDeleteCategory() {
        Long categoryIdToDelete = 1L;
        Category existingCategory = new Category(categoryIdToDelete, "Existing Category","Description");
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.of(existingCategory));

        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryIdToDelete));
    }

    @Test
    public void testDeleteCategoryNotFound() {
        Long categoryIdToDelete = 1L;
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> categoryService.deleteCategory(categoryIdToDelete));
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO existingcategoryDto = new CategoryDTO(categoryId,"Java","Programming language");
        
        Category category = new Category(existingcategoryDto.getCategoryId(),existingcategoryDto.getCategoryName(),
                existingcategoryDto.getDescription());
        
        CategoryDTO updatedcategoryDto = new CategoryDTO();
        updatedcategoryDto.setCategoryName("Spring");
        updatedcategoryDto.setDescription("Programming language");
        Category updatedCategory = new Category(updatedcategoryDto.getCategoryId(),updatedcategoryDto.getCategoryName(),
                updatedcategoryDto.getDescription());
        
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(updatedCategory);
        CategoryDTO categoryDto = categoryService.updateCategory(categoryId, updatedcategoryDto);
        assertNotNull(categoryDto);
        assertEquals(updatedcategoryDto.getCategoryId(),categoryDto.getCategoryId());
        assertEquals(updatedcategoryDto.getCategoryName(),categoryDto.getCategoryName());
    }

    @Test
    public void testUpdateCategoryNotFound() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDTO = new CategoryDTO(categoryId, "UpdatedCategory", "Updated Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> categoryService.updateCategory(categoryId, updatedCategoryDTO));
    }

    @Test
    public void testUpdateCategoryNameExistsForDifferentCategory() {
        Long categoryId = 2L;
        CategoryDTO updatedCategory = new CategoryDTO(categoryId, "Existing Category", "Updated Description");
        Category existingCategory = new Category(categoryId, "Another Category", "Description");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.getCategoryByName(updatedCategory.getCategoryName())).thenReturn(Optional.of(new Category(3L, "Existing Category", "Some Other Description")));

        assertThrows(AlreadyExistsException.class, () -> categoryService.updateCategory(categoryId, updatedCategory));
    }


    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "ExistingCategory", "Description");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        assertEquals("ExistingCategory", categoryDTO.getCategoryName());
        assertEquals("Description", categoryDTO.getDescription());
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> categoryService.getCategoryById(categoryId));
    }
}