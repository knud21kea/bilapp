package com.example.wishlist.controllers;

import com.example.wishlist.models.Account;
import com.example.wishlist.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String createAccount(HttpServletRequest request, WebRequest account, Model model)
    {
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        String mail = account.getParameter("emailAddress");
        Account sessionAccount = new Account(user, pass, mail); // Account object
        //as.addAccountToDb(sessionAccount); // need to wait for a db connection
        HttpSession session = request.getSession();
        session.setAttribute("sessionAccount", sessionAccount); // added to session
        userNames.add(user); // temp
        passwords.add(pass); // temp
        model.addAttribute("userNames", userNames); // need to fetch userNames from service
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String accountCreated() {
        return "/login";
    }

    @PostMapping("/login")
    public String submitLogin(HttpServletRequest request, WebRequest account, Model model)
    {
        //todo: add username and password to session, here a temp test
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        loggedin = as.checkLoginCredentials(user, pass, userNames, passwords);
        if (loggedin) {
            sessionUser = user;
            //HttpSession session = request.getSession();
            //session.setAttribute("sessionBasket", mySessionBasket);
        }
        else {
            sessionUser = "Guest";
        }
        return "redirect:/index";
    }
}
