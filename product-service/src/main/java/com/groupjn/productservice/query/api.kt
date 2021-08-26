package com.groupjn.productservice.query;


import com.groupjn.productservice.entity.ProductSummary
import com.groupjn.productservice.model.Product
import org.axonframework.commandhandling.model.Repository
import org.springframework.data.jpa.repository.JpaRepository

class GetProductsQuery

interface ProductSummaryRepository:JpaRepository<ProductSummary, String>
interface ProductRepository:Repository<Product>
