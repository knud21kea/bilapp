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
    public String createAccount(WebRequest account, Model model)
    {
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        String mail = account.getParameter("emailAddress");
        Account sessionAccount = new Account(user, pass, mail); // Account object
        as.addAccountToDb(sessionAccount); // added to db
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
    public String submitLogin(HttpServletRequest request, WebRequest account)
    {
        //todo: add account to session, here a temp workaround
        String user = account.getParameter("userName");
        String pass = account.getParameter("password");
        loggedin = as.checkLoginCredentials(user, pass, userNames, passwords); // temp overload
        if (loggedin) {
            sessionUser = user;
            HttpSession session = request.getSession();
            Account sessionAccount = as.getAccountFromUsername(user);
            session.setAttribute("sessionAccount", sessionAccount); // add account to session
        }
        else {
            sessionUser = "Guest";
        }
        return "redirect:/index";
    }
}
