package com.lambdaschool.deployshoppingcart.controllers;

import com.lambdaschool.deployshoppingcart.models.CartItem;
import com.lambdaschool.deployshoppingcart.models.User;
import com.lambdaschool.deployshoppingcart.services.CartItemService;
import com.lambdaschool.deployshoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Cart controller.
 */
@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;
    
    /**
     * List cart items by user id response entity.
     *
     * @param userid the userid
     * @return the response entity
     */
    @GetMapping(value = "/user/{userid}",
        produces = {"application/json"})
    public ResponseEntity<?> listCartItemsByUserId(
        @PathVariable
            long userid)
    {
        User u = userService.findUserById(userid);
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }
    
    /**
     * Add to cart response entity.
     *
     * @param userid    the userid
     * @param productid the productid
     * @return the response entity
     */
    @PutMapping(value = "/add/user/{userid}/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> addToCart(
        @PathVariable
            long userid,
        @PathVariable
            long productid)
    {
        CartItem addCartTtem = cartItemService.addToCart(userid,
            productid,
            "I am not working");
        return new ResponseEntity<>(addCartTtem,
            HttpStatus.OK);
    }
    
    /**
     * Remove from cart response entity.
     *
     * @param userid    the userid
     * @param productid the productid
     * @return the response entity
     */
    @DeleteMapping(value = "/remove/user/{userid}/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> removeFromCart(
        @PathVariable
            long userid,
        @PathVariable
            long productid)
    {
        CartItem removeCartItem = cartItemService.removeFromCart(userid,
            productid,
            "I am still not working");
        return new ResponseEntity<>(removeCartItem,
            HttpStatus.OK);
    }
}
