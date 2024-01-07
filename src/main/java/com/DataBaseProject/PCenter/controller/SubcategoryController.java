package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.common.ApiResponse;
import com.DataBaseProject.PCenter.data.Subcategory;
import com.DataBaseProject.PCenter.data.Subcategory;
import com.DataBaseProject.PCenter.service.SubcategoryService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategories")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;
    @PostMapping("/create")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<ApiResponse> createSubcategory(@RequestBody Subcategory subcategory){
        subcategoryService.createSubcategory(subcategory);
        return new ResponseEntity<>(new ApiResponse(true, "a new subcategory created"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<Subcategory> listCategories(){
        return subcategoryService.listCategories();
    }
    @GetMapping("/{id}")
    public Optional<Subcategory> findById(@PathVariable int id){
        return subcategoryService.getById(id);
    }
    @PostMapping("/update/{categoryId}")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<ApiResponse> updateSubcategory(@PathVariable ("subcategoryId") int subcategoryId, @RequestBody Subcategory subcategory){
        if(!subcategoryService.findById(subcategoryId))
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "subcategory not found"), HttpStatus.NOT_FOUND);
        subcategoryService.editSubcategory(subcategoryId,subcategory);
        return new ResponseEntity<>(new ApiResponse(true, "subcategory has been updated"), HttpStatus.OK);
    }
}
