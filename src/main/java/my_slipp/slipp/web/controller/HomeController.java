package my_slipp.slipp.web.controller;

import my_slipp.slipp.web.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  QuestionRepository questionRepository;

  @GetMapping("/")
  public String home(Model model){
    model.addAttribute("questions", questionRepository.findAll());
    return "index";
  }

  @GetMapping("/loginForm")
  public String loginForm(Model model){
    return "loginForm";
  }

  @GetMapping("/signupForm")
  public String signupForm(Model model){
    return "SignupForm";
  }
}
