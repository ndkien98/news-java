package com.t3h.news.controller;

import com.t3h.news.model.CategoryModel;
import com.t3h.news.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    private ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getList(){
        return ResponseEntity.ok(iCategoryService.getList());
    }
}
