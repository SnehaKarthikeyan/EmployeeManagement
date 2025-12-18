package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Cart;

public interface CartServiceInterface {
    Cart addCart(Cart cart);
    Cart getById(long id);
    List<Cart> getAllCart();
    List<Cart> getByProductId(long productId);
    void deleteCart(Cart cart);
    Cart updateCart(Cart cart);
}
