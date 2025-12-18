package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.Model.Cart;
import com.inzeph.EmployeeManagement.Model.Product;
import com.inzeph.EmployeeManagement.ServiceInterface.CartServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.inzeph.EmployeeManagement.Utils.Util;

@RestController
@RequestMapping("/cart")
public class CartController {
    Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartServiceInterface service;

    @Autowired
    ProductServiceInterface productService;

    @GetMapping
    public ResponseEntity<Object> getAllCart() throws IOException {
        List<Cart> carts = service.getAllCart();
        if (carts.isEmpty()) {
            return Util.generateResponse("Cart was Empty", HttpStatus.NO_CONTENT, null);
        }
        ArrayList<Object> json = new ArrayList<>();
        for (Cart cart: carts) {
            Product product = productService.getById(cart.getProduct());
            json.add(Util.convertJSON(cart, product, "product"));
        }
        return Util.generateResponse("Successfully retrieved cart product", HttpStatus.OK, json);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getByProductId(@PathVariable("productId") long productId) throws IOException {
        List<Cart> carts = service.getByProductId(productId);
        if (carts.isEmpty()) {
            return Util.generateResponse("Product " + productId + " was not Found", HttpStatus.NOT_FOUND, null);
        }
        ArrayList<Object> json = new ArrayList<>();
        for (Cart cart: carts) {
            Product product = productService.getById(cart.getProduct());
            json.add(Util.convertJSON(cart, product, "product"));
        }
        return Util.generateResponse("Successfully retrieved cart product(s)", HttpStatus.OK, json);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCart(@PathVariable long id, @RequestBody Cart cart) {
        Cart updatedCart = service.getById(id);
        if (updatedCart == null) {
            return Util.generateResponse("Cart " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(cart, updatedCart);
        updatedCart.setId(id);
        return Util.generateResponse("successfully Updated Cart!", HttpStatus.OK,
                service.updateCart(updatedCart));
    }

    @PostMapping
    public ResponseEntity<Object> createCart(@RequestBody Cart cart) throws IOException {
        Product product = productService.getById(cart.getProduct());
        if(product == null) {
            return Util.generateResponse("Product " + cart.getProduct() + " does not exist", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully added cart " + cart.getId(), HttpStatus.CREATED,
                Util.convertJSON(service.addCart(cart), product, "product"));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Object> deleteCart(@PathVariable("cartId") long cartId) {
        Cart cart = service.getById(cartId);
        if (cart == null) {
            return Util.generateResponse("Cart " + cartId + " was not found", HttpStatus.NOT_FOUND, null);
        }
        service.deleteCart(cart);
        return Util.generateResponse("Successfully deleted cart " + cartId, HttpStatus.OK, cart);
    }
}
