package com.lambdaschool.deployshoppingcart.services;

import com.lambdaschool.deployshoppingcart.models.CartItem;

/**
 * The interface Cart item service.
 */
public interface CartItemService
{
    /**
     * Add to cart cart item.
     *
     * @param userid    the userid
     * @param productid the productid
     * @param comment   the comment
     * @return the cart item
     */
    CartItem addToCart(
        long userid,
        long productid,
        String comment);
    
    /**
     * Remove from cart cart item.
     *
     * @param userid    the userid
     * @param productid the productid
     * @param comment   the comment
     * @return the cart item
     */
    CartItem removeFromCart(
        long userid,
        long productid,
        String comment);
}
