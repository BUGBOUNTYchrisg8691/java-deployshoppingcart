package com.lambdaschool.deployshoppingcart.repository;

import com.lambdaschool.deployshoppingcart.models.CartItem;
import com.lambdaschool.deployshoppingcart.models.CartItemId;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Cart item repository.
 */
public interface CartItemRepository extends CrudRepository<CartItem, CartItemId>
{
}
