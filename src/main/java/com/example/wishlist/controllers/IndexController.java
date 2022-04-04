package com.example.wishlist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /*@GetMapping("/index")
    public String index(){
    return "index";
    }*/



    @GetMapping("/wishLists")
    public String wishLists(){
        return "wishLists";
    }

    @GetMapping("/createWish")
    public String createWish(){
        return "createWish";
    }

    @GetMapping("/specificWishList")
    public String specificWishList(){
        return "specificWishList";
    }
}
