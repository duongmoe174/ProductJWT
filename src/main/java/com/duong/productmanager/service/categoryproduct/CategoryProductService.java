package com.duong.productmanager.service.categoryproduct;

import com.duong.productmanager.entity.CategoryProduct;
import com.duong.productmanager.repository.ICategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryProductService implements ICategoryProductService{
    @Autowired
    private ICategoryProductRepository categoryProductRepository;
    @Override
    public Iterable<CategoryProduct> findAll() {
        return categoryProductRepository.findAll();
    }

    @Override
    public Optional<CategoryProduct> findById(Long id) {
        return categoryProductRepository.findById(id);
    }

    @Override
    public CategoryProduct save(CategoryProduct categoryProduct) {
        return categoryProductRepository.save(categoryProduct);
    }

    @Override
    public void remove(Long id) {
        categoryProductRepository.deleteById(id);
    }
}
