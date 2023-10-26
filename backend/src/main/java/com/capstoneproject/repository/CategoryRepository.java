package com.capstoneproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.Category;

/**
 * This interface contains the JPA Repository.
 */
@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * This gets the Category by Name.
     * @param categoryName .
     * @return Category based on the name.
     */
    @Query("select categ from Category as categ where "
            + "categ.categoryName = :categoryName")
    Optional<Category> getCategoryByName(String categoryName);
}
