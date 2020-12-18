package com.lambdaschool.deployshoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Cart item.
 */
@Entity
@IdClass(CartItemId.class)
@Table(name = "cartitems")
public class CartItem
    extends Auditable
    implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "carts",
        allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonIgnoreProperties(value = "carts",
        allowSetters = true)
    private Product product;

    private long quantity;

    private String comments;
    
    /**
     * Instantiates a new Cart item.
     */
    public CartItem()
    {

    }
    
    /**
     * Instantiates a new Cart item.
     *
     * @param user     the user
     * @param product  the product
     * @param quantity the quantity
     * @param comments the comments
     */
    public CartItem(
        User user,
        Product product,
        long quantity,
        String comments)
    {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.comments = comments;
    }
    
    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser()
    {
        return user;
    }
    
    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user)
    {
        this.user = user;
    }
    
    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct()
    {
        return product;
    }
    
    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public long getQuantity()
    {
        return quantity;
    }
    
    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }
    
    /**
     * Gets comments.
     *
     * @return the comments
     */
    public String getComments()
    {
        return comments;
    }
    
    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments)
    {
        this.comments = comments;
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

        CartItem that = (CartItem) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((product == null) ? 0 : product.getProductid()) == ((that.product == null) ? 0 : that.product.getProductid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
