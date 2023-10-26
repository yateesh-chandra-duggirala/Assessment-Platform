package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.CategoryDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.Category;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.response.ExceptionMessages;
import com.capstoneproject.response.SuccessMessages;

/**
 * This class contains the Service for Category.
 */
@Service
public class CategoryService {
    /**
     * This categoryRepository variable performs some operations.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Creating instance for the Category.
     */
    private Logger logger = LoggerFactory.getLogger(CategoryService.class);
    /**
     * gets all categories.
     * @return the List of all categories.
     */
    public final List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        logger.info(SuccessMessages.CATEGORY_FETCH);
        return categories.stream().map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    /**
     * This method converts the Model to DTO.
     * @param category This is the Category type.
     * @return the Category DTO.
     */
    private CategoryDTO convertModelToDto(final Category category) {
        logger.info(SuccessMessages.MODEL_TO_DTO);
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    /**
     * adds the category.
     * @param categoryDto of type Category.
     * @return the Status of the category added.
     */
    public final CategoryDTO addCategory(final CategoryDTO categoryDto) {
        Optional<Category> category = categoryRepository
                .getCategoryByName(categoryDto.getCategoryName());
        if (category.isPresent()) {
            logger.error(ExceptionMessages.CATEGORY_ALREADY_EXISTS);
            throw new AlreadyExistsException(
                    ExceptionMessages.CATEGORY_ALREADY_EXISTS);
        }
        Category newCategory = new Category();
        newCategory.setCategoryId(categoryDto.getCategoryId());
        newCategory.setCategoryName(categoryDto.getCategoryName());
        newCategory.setDescription(categoryDto.getDescription());
        logger.info(SuccessMessages.CATEGORY_ADD_SUCCESS);
        categoryRepository.save(newCategory);
        return categoryDto;
    }

    /**
     * deletes the Category by Id.
     * @param categoryId of Long Type.
     */
    public final void deleteCategory(final Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ElementNotExistsException(
                        ExceptionMessages.CATEGORY_NOT_EXIST));
        logger.info(SuccessMessages.CATEGORY_DELETE_SUCCESS);
        categoryRepository.deleteById(categoryId);
    }

    /**
     * updates the category.
     * @param categoryId      of Long Type.
     * @param updatedCategory of Category Type.
     * @return the status of the updated category.
     */
    public final CategoryDTO updateCategory(final Long categoryId,
            final CategoryDTO updatedCategory) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(
                () -> new ElementNotExistsException(
                        ExceptionMessages.CATEGORY_NOT_EXIST));
        logger.info(SuccessMessages.CATEGORY_FOUND);
        Optional<Category> category = categoryRepository
                .getCategoryByName(updatedCategory.getCategoryName());
        if (category.isPresent()
                && !category.get().getCategoryId().equals(categoryId)) {
            logger.error(ExceptionMessages.CATEGORY_ALREADY_EXISTS);
            throw new AlreadyExistsException(
                    ExceptionMessages.CATEGORY_ALREADY_EXISTS);
        }
        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setDescription(updatedCategory.getDescription());

        logger.info(SuccessMessages.CATEGORY_UPDATED_SUCCESS);
        Category newCategory = categoryRepository.save(existingCategory);
        CategoryDTO newCategoryDto = new CategoryDTO();
        newCategoryDto.setCategoryName(newCategory.getCategoryName());
        newCategoryDto.setDescription(newCategory.getDescription());
        return newCategoryDto;
    }

    /**
     * gets the category by category Id.
     * @param categoryId of Long type.
     * @return the CategoryId if exist.
     */
    public final CategoryDTO getCategoryById(final Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ElementNotExistsException(
                        ExceptionMessages.CATEGORY_NOT_EXIST));
        logger.info(SuccessMessages.CATEGORY_FETCH_BY_ID);
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(existingCategory.getCategoryId());
        categoryDto.setCategoryName(existingCategory.getCategoryName());
        categoryDto.setDescription(existingCategory.getDescription());
        return categoryDto;
    }
}
