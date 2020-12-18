package com.lambdaschool.deployshoppingcart.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The type Cart item id.
 */
@Embeddable
public class CartItemId implements Serializable
{
    private long user;

    private long product;
    
    /**
     * Instantiates a new Cart item id.
     */
    public CartItemId()
    {
    }
    
    /**
     * Instantiates a new Cart item id.
     *
     * @param user    the user
     * @param product the product
     */
    public CartItemId(
        long user,
        long product)
    {
        this.user = user;
        this.product = product;
    }
    
    /**
     * Gets user.
     *
     * @return the user
     */
    public long getUser()
    {
        return user;
    }
    
    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(long user)
    {
        this.user = user;
    }
    
    /**
     * Gets product.
     *
     * @return the product
     */
    public long getProduct()
    {
        return product;
    }
    
    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(long product)
    {
        this.product = product;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CartItemId that = (CartItemId) o;
        return user == that.user &&
            product == that.product;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
