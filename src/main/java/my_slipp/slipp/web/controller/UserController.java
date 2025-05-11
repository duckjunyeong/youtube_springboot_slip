package my_slipp.slipp.web.controller;

import my_slipp.slipp.web.domain.User;
import my_slipp.slipp.web.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user/create")
  public String createUser(User user) {
    if (user == null){
      throw new IllegalArgumentException("user is not created");
    }
    userRepository.save(user);
    return "redirect:/list";
  }


  @GetMapping("/list")
  public String list(Model model){
    List<User> users = userRepository.findAll();
    model.addAttribute("users", users);
    return "list";
  }
}
