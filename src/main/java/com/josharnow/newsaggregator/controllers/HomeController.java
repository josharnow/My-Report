package com.josharnow.newsaggregator.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.josharnow.newsaggregator.models.LoginUser;
import com.josharnow.newsaggregator.models.User;
import com.josharnow.newsaggregator.services.ArticleService;
import com.josharnow.newsaggregator.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  private UserService userServ;
  private ArticleService articleServ;

  public HomeController(UserService userServ, ArticleService articleServ) {
    this.userServ = userServ;
    this.articleServ = articleServ;
  }

  // Login and Registration landing page
  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("newUser", new User());
    model.addAttribute("newLogin", new LoginUser());
    return "index.jsp";
  }

  // Registration action
  @PostMapping("/register")
  public String register(
      @Valid @ModelAttribute("newUser") User newUser,
      BindingResult result,
      Model model,
      HttpSession session) {
    // if (session.getAttribute("logged_in") == null) {
    //   session.setAttribute("logged_in", false);
    // }
    // boolean logged_in = false;

    userServ.register(newUser, result);

    if (result.hasErrors()) {
      model.addAttribute("newLogin", new LoginUser());

      return "index.jsp";
    }

    // logged_in = true;
    // session.setAttribute("logged_in", logged_in);
    session.setAttribute("user_id", newUser.getId());

    return "redirect:/dashboard";
  }
  
  // Login action
  @PostMapping("/login")
  public String login(
      @Valid @ModelAttribute("newLogin") LoginUser newLogin,
      BindingResult result,
      Model model,
      HttpSession session) {
    // if (session.getAttribute("logged_in") == null) {
    //   session.setAttribute("logged_in", false);
    // }
    // boolean logged_in = false;

    User user = userServ.login(newLogin, result);

    if (result.hasErrors()) {
      model.addAttribute("newUser", new User());

      return "index.jsp";
    }

    // logged_in = true;
    // session.setAttribute("logged_in", logged_in);
    session.setAttribute("user_id", user.getId());

    return "redirect:/dashboard";
  }

  // Dashboard for all links
  @RequestMapping("/dashboard")
  public String dashboard() {
    return "dashboard.jsp";
  }

  // New Article render JSP
  @RequestMapping("/newArticle")
  public String newArticle() {
    return "newArticle.jsp";
  }

  // Creating new Article action

  // Edit Article render JSP
  @RequestMapping("/editArticle/{id}")
  public String editArticle() {
    return "editArticle.jsp";
  }

  // Editing Article action

  
  // Render one Article JSP
  @RequestMapping("/oneArticle/{id}")
  public String oneArticle() {
    return "oneArticle.jsp";
  }
}
