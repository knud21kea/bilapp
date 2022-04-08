package com.example.wishlist.controllers;

import com.example.wishlist.models.Account;
import com.example.wishlist.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class WishlistController {
    private WishlistService ws = new WishlistService();

    @GetMapping("/createwishlist")
    public String createWishlist(Model model, HttpSession session) {
        Account sessionUser = (Account) session.getAttribute("sessionAccount");
        model.addAttribute("userName", sessionUser.getAccountName());
        return "createwishlist";
    }

    @PostMapping("/createwishlist")
    public String createWishList(WebRequest request, HttpSession session) {
        String wishlistName = request.getParameter("wishlistName");
        Account account = (Account) session.getAttribute("sessionAccount");
        int currentWishlistID = ws.createWishList(account, wishlistName);
        session.setAttribute("currentWishlistID", currentWishlistID);
        return "redirect:/addwish";
    }

    @PostMapping("/addwish")
    public String addWish(WebRequest request, HttpSession session) {
        int currentWishlistID = (int) session.getAttribute("currentWishlistID");
        String wishName = request.getParameter("wishName");
        String wishDescription = request.getParameter("wishDescription");
        double wishPrice = Double.parseDouble(request.getParameter("wishPrice"));
        String wishURL = request.getParameter("wishURL");
        ws.createWish(wishName, wishDescription, wishPrice, wishURL, currentWishlistID);
        return "redirect:/addwish";
    }

    @GetMapping("/addwish")
    public String addWish(Model model, HttpSession session) {
        Account sessionUser = (Account) session.getAttribute("sessionAccount");
        model.addAttribute("userName", sessionUser.getAccountName());
        return "addwish";
    }

    @GetMapping("/specificwishlist")
    public String specificWishList() {
        return "specificwishlist";
    }
}
