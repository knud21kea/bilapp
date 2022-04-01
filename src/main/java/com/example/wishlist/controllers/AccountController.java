package com.example.wishlist.controllers;

import com.example.wishlist.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class AccountController
{
    // simulates the db
    private AccountService as = new AccountService();
    private ArrayList<String> userNames = new ArrayList<>(Arrays.asList("", "a", "ab", "abc"));
    private ArrayList<String> passwords = new ArrayList<>(Arrays.asList("0", "a1", "ab2", "abc3"));
    private String sessionUser = "Guest";
    private boolean loggedin = false;

    @GetMapping("/index")
    public String start(Model model)
    {
        model.addAttribute("userName", sessionUser);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("userNames", userNames);
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(WebRequest account, Model model)
    {
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        userNames.add(user);
        passwords.add(pass);
        model.addAttribute("userNames", userNames);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String accountCreated() {
        return "/login";
    }

    @PostMapping("/login")
    public String submitLogin(WebRequest account, Model model)
    {
        //todo: add username and password to session, here a temp test
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        loggedin = as.checkLoginCredentials(user, pass, userNames, passwords);
        if (loggedin) {
            sessionUser = user;
        }
        else {
            sessionUser = "Guest";
        }
        return "redirect:/index";
    }
}
