/*

Original code by @Nicklas Dean
Developed by @Graham Heaven

KEA Datamatiker exercises with Java Spring, Thymeleaf, mySQL
April 2022

Demo for use of Interface as repo, and with Singleton pattern (DB connector).

 */

package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontPageController {
    @GetMapping("/")
    public String start(){
        return "index";

    }@GetMapping("/index")
    public String index(){
        return "index";
    }
}