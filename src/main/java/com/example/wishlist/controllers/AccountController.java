package com.example.wishlist.controllers;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AccountController {
    // simulates the db
    private AccountService as = new AccountService();
    private String sessionUser = "Guest";
    private boolean loggedin = false;

    @GetMapping("/")
    public String landPage(Model model) {
        model.addAttribute("userName", sessionUser);
        return "index";
    }

    @GetMapping("/index")
    public String start(Model model) {
        model.addAttribute("userName", sessionUser);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userNamesDb", as.getAllUserNames());
        model.addAttribute("username", sessionUser);
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(WebRequest account, Model model) {
        String user = account.getParameter("userName");
        String mail = account.getParameter("emailAddress");
        String pass = account.getParameter("password");
        Account sessionAccount = new Account(user, mail, pass); // Account object
        as.addAccountToDb(sessionAccount); // added to db
        model.addAttribute("userNamesDb", as.getAllUserNames()); // fetched
        model.addAttribute("username", sessionUser);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String accountCreated(Model model) {
        model.addAttribute("username", sessionUser);
        return "signin";
    }

    @PostMapping("/login")
    public String submitLogin(HttpServletRequest request, WebRequest account)
    {
        //account is now added to the session
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        loggedin = as.checkLoginCredentials(user, pass); // temp overload
        if (loggedin) {
            sessionUser = user;
            HttpSession session = request.getSession();
            Account sessionAccount = as.getAccountFromUsername(user);
            session.setAttribute("sessionAccount", sessionAccount); // add account to session
        }
        else {
            sessionUser = "Guest";
        }
        return (loggedin) ? "redirect:/accountwishlists" : "redirect:/faillogin";
    }

    @GetMapping("/accountwishlists")
    public String seeAccountWistlists(Model model){
        model.addAttribute("userName", sessionUser);
        return "accountwishlists";
    }

    @GetMapping("/faillogin")
    public String faillogin(Model model){
        model.addAttribute("username", sessionUser);
        return "faillogin";
    }

    @GetMapping("/reserve")
    public String reserveWish(@RequestParam int wishlist, Model model) {
        ArrayList<Wish> listOfWishes;
        WishList wlist = as.getWishlistFromId(wishlist);
        WishList wlistWishes = as.getWishesFromWishlist(wlist);
        ArrayList<Wish> listOfWishes2 = as.getUnreservedWishesFromWishlistID(wishlist);
        model.addAttribute("currentWishlist", wlistWishes);
        model.addAttribute("listOfWishes", listOfWishes2);
        model.addAttribute("username", sessionUser);
        return "reserve";
    }

    @GetMapping("/reservewish")
    public String wishReserved(@RequestParam int list, int wish, Model model)
    {
        ArrayList<Wish> listOfWishes = as.getUnreservedWishesFromWishlistID(list);
        model.addAttribute("listOfWishes", listOfWishes);
        model.addAttribute("list", list);
        model.addAttribute("wish", wish);
        as.reserveWish(list, wish);
        return "wishreserved";
    }
}
