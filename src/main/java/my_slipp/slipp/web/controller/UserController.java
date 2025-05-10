package my_slipp.slipp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
  private List<User> users = new ArrayList<>();

  @PostMapping("/user/create")
  public String createUser(User user) {
    if (user == null){
      throw new IllegalArgumentException("user is not created");
    }
    users.add(user);
    return "redirect:/list";
  }


  @GetMapping("/list")
  public String list(Model model){
    model.addAttribute("users", users);
    return "list";
  }
}
