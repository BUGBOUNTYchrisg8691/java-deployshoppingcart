package com.lambdaschool.deployshoppingcart.repository;

import com.lambdaschool.deployshoppingcart.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends CrudRepository<Product, Long>
{
}
