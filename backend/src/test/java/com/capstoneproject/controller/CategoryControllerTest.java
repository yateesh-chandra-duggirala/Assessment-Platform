package com.capstoneproject.controller;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("rawtypes")
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryDTO category1 = new CategoryDTO(1L, "Category Name",  "This is Description");
        CategoryDTO category2 = new CategoryDTO(1L, "Category Name",  "This is Description");
        categories.add(category1);
        categories.add(category2);
        assertEquals(2, categories.size());

        when(categoryService.getCategories()).thenReturn(categories);
        Response response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.CATEGORY_FETCH, response.getMessage());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDTO category = new CategoryDTO(categoryId, "Category Name",  "This is Description");
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        Response response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.CATEGORY_FETCH_BY_ID, response.getMessage());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testAddCategory() {
        Long categoryId = 1L;
        CategoryDTO categoryDto = new CategoryDTO(categoryId, "Category Name",  "This is Description");

        Response response = categoryController.addCategory(categoryDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(categoryDto);
        assertEquals(SuccessMessages.CATEGORY_ADD_SUCCESS, response.getMessage());
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;

        Response response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.CATEGORY_DELETE_SUCCESS, response.getMessage());
    }

    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDTO updatedCategoryDto = new CategoryDTO();

        Response response = categoryController.updateCategory(categoryId, updatedCategoryDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.CATEGORY_UPDATED_SUCCESS, response.getMessage());

    }
}
