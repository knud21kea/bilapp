package com.example.wishlist.controllers;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.WishList;
import com.example.wishlist.services.AccountService;
import com.example.wishlist.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class WishlistController {
    private AccountService as = new AccountService();
    private WishlistService ws = new WishlistService();

    @GetMapping("/createwishlist")
    public String createWishlist (){
        return "createwishlist";
    }

    @PostMapping("/createwishlist")
    public String createWishList (WebRequest request, HttpSession session){

        String wishlistName = request.getParameter("wishlistName");

        Account account = (Account) session.getAttribute("sessionAccount");
        ws.createWishList(account, wishlistName);

        return "redirect:/addwish";
    }

    @PostMapping("/addwish")
    public String addwish (WebRequest request, WishList wishList){

        String wishName = request.getParameter("wishName");
        String wishDescription = request.getParameter("wishDescription");
        double wishPrice = Double.parseDouble(request.getParameter("wishPrice"));
        String wishURL = request.getParameter("wishURL");
        ws.createWish(wishName,wishDescription,wishPrice,wishURL,wishList);
        return "redirect:/addwish";
    }

    @GetMapping("/addwish")
    public String addWish (Model model){
        return "addwish";
    }

    @GetMapping("/specificWishList")
    public String specificWishList(){
        return "specificWishList";
    }

}
