package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.CartRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.CartServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Cart;

@Service
public class CartService implements CartServiceInterface {
    Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    CartRepo repo;

    @Override
    public Cart addCart(Cart cart) {
        return repo.save(cart);
    }

    @Override
    public Cart getById(long id) {
        Optional<Cart> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public List<Cart> getAllCart() {
        return repo.findAll();
    }

    @Override
    public List<Cart> getByProductId(long productId) {
        List<Cart> carts = repo.findByProduct(productId);
        return carts;
    }

    @Override
    public Cart updateCart(Cart cart) { return repo.save(cart); }

    @Override
    public void deleteCart(Cart cart) {
        repo.delete(cart);
    }
}
