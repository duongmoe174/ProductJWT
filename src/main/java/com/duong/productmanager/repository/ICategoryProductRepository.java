package com.duong.productmanager.repository;

import com.duong.productmanager.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
}
