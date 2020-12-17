package com.lambdaschool.deployshoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Product.
 */
@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = "hasprice")
public class Product
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productid;

    @Column(nullable = false,
        unique = true)
    private String name;
    
    /**
     * The Hasprice.
     */
    @Transient
    public boolean hasprice = false;

    private double price;

    private String description;

    private String comments;

    @OneToMany(mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "product",
        allowSetters = true)
    private Set<CartItem> carts = new HashSet<>();
    
    /**
     * Instantiates a new Product.
     */
    public Product()
    {

    }
    
    /**
     * Gets productid.
     *
     * @return the productid
     */
    public long getProductid()
    {
        return productid;
    }
    
    /**
     * Sets productid.
     *
     * @param productid the productid
     */
    public void setProductid(long productid)
    {
        this.productid = productid;
    }
    
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price)
    {
        hasprice = true;
        this.price = price;
    }
    
    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description)
    {
        this.description = description;
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
    
    /**
     * Gets carts.
     *
     * @return the carts
     */
    public Set<CartItem> getCarts()
    {
        return carts;
    }
    
    /**
     * Sets carts.
     *
     * @param carts the carts
     */
    public void setCarts(Set<CartItem> carts)
    {
        this.carts = carts;
    }
}
