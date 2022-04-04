package com.example.wishlist.controllers;

import com.example.wishlist.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishlistController {
    private AccountService as = new AccountService();

    @GetMapping("/createwishlist")
    public String createWishlist (){

        return "createwishlist";
    }
}
