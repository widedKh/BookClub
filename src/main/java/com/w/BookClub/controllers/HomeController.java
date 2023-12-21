package com.w.BookClub.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.w.BookClub.models.LoginUser;
import com.w.BookClub.models.User;
import com.w.BookClub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
  
	// Add once service is implemented:
    @Autowired
    private UserService userServ;
   
   @GetMapping("/")
   public String index(Model model) {

       model.addAttribute("newUser", new User());
       model.addAttribute("newLogin", new LoginUser());
       return "index.jsp";
   }
   
   @PostMapping("/register")
   public String register(@Valid @ModelAttribute("newUser") User newUser, 
           BindingResult result, Model model, HttpSession session) {
   	userServ.register(newUser, result);
       
       if(result.hasErrors()) {
           // Be sure to send in the empty LoginUser before 
           // re-rendering the page.
           model.addAttribute("newLogin", new LoginUser());
           return "index.jsp";
       }
       
       else {
       	session.setAttribute("user_id", newUser.getId());
       }
   
       return "redirect:/books";
   }
   
   @PostMapping("/login")
   public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
           BindingResult result, Model model, HttpSession session) {
       User user = userServ.login(newLogin, result);
   
       if(result.hasErrors()) {
           model.addAttribute("newUser", new User());
           return "index.jsp";
       }

       else {
       	session.setAttribute("user_id", user.getId());
       }
       return "redirect:/books";
   }
   

   
   @GetMapping("/logout")
   public String logout(HttpSession session) {
	   session.invalidate();
  	 return "redirect:/";
   }

   
}


