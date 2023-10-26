package com.capstoneproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.CategoryService;

import jakarta.validation.Valid;

/**
 * This class contains the controller.
 */
@RestController
@RequestMapping(path = "category")
@CrossOrigin(origins = "*")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryController {

    /**
     * The categoryService variable is used to operate on Category Service.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Creating instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AllResultsController.class);

    /**
     * Gets all the categories.
     * @return the list of categories.
     */
    @GetMapping
    public final Response getAllCategories() {
        List<CategoryDTO> categories = categoryService.getCategories();
        logger.info(SuccessMessages.CATEGORY_FETCH);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.CATEGORY_FETCH, categories);
        return response;
    }

    /**
     * Get the category of requested Id.
     * @param categoryId - a path variable whose category is required.
     * @return the category if exists.
     */
    @GetMapping("/{categoryId}")
    public final Response getCategoryById(
            @PathVariable final Long categoryId) {
        CategoryDTO newCategoryDto = categoryService
                .getCategoryById(categoryId);
        logger.info(SuccessMessages.CATEGORY_FETCH_BY_ID);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.CATEGORY_FETCH_BY_ID, newCategoryDto);
        return response;
    }

    /**
     * Add the category.
     * @param categoryDto of Category type is requested to add into it.
     * @return the success status when added.
     */
    @PostMapping
    public final Response addCategory(
            @RequestBody @Valid final CategoryDTO categoryDto) {
        categoryService.addCategory(categoryDto);
        logger.info(SuccessMessages.CATEGORY_ADD_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.CATEGORY_ADD_SUCCESS);
        return response;
    }

    /**
     * Deletes the Category.
     * @param categoryId to be specified that is not needed.
     * @return deleted successfully if not found.
     */
    @DeleteMapping("/{categoryId}")
    public final Response deleteCategory(
            @PathVariable final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        logger.info(SuccessMessages.CATEGORY_DELETE_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.CATEGORY_DELETE_SUCCESS);
        return response;
    }

    /**
     * Updates the category .
     * @param categoryId of Long Type.
     * @param updatedCategoryDto the updated content.
     * @return the updated category status.
     */
    @PutMapping("/{categoryId}")
    public final Response updateCategory(
            @PathVariable final Long categoryId,
            @RequestBody @Valid final CategoryDTO updatedCategoryDto) {
        categoryService.updateCategory(categoryId, updatedCategoryDto);
        logger.info(SuccessMessages.CATEGORY_UPDATED_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.CATEGORY_UPDATED_SUCCESS);
        return response;
    }

}
