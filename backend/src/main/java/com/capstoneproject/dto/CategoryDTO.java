/**
 * This contains the Category DTO class.
 */
package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the class containing DTO class for category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

  /**
   * This is category Id field for Category class.
   */
  private Long categoryId;

  /**
   * This is Category Title Name field.
   */
  @NotBlank(message = ValidationMessages.CATEGORY_NAME_NOTBLANK)
  private String categoryName;

  /**
   * This is Category Description.
   */
  @NotBlank(message = ValidationMessages.CATEGORY_DESC_NOTBLANK)
  private String description;
}
