package com.deep.blog.services;

import java.util.List;

import com.deep.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer category_id);
	
	public void deleteCategory(Integer category_id);
	
	CategoryDto getCategoryDto(Integer category_id);
	
	List<CategoryDto> getCategoryDtos();

}
