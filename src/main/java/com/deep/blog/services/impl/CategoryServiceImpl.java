package com.deep.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.blog.entities.Category;
import com.deep.blog.exceptions.ResourceNotFoundException;
import com.deep.blog.payloads.CategoryDto;
import com.deep.blog.repositories.CategoryRepo;
import com.deep.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category newCategory = this.modelMapper.map(categoryDto, Category.class);
		Category createdCategory = this.categoryRepo.save(newCategory);
		return this.modelMapper.map(createdCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer category_id) {
		Category category = this.categoryRepo.findById(category_id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", category_id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer category_id) {
		Category category = this.categoryRepo.findById(category_id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category_ID", category_id));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategoryDto(Integer category_id) {
		Category category = this.categoryRepo.findById(category_id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", category_id));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategoryDtos() {
		List<Category> list = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = list.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
