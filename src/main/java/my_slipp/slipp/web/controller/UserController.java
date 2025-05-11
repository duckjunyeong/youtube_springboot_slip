package my_slipp.slipp.web.controller;

import jakarta.servlet.http.HttpSession;
import my_slipp.slipp.web.domain.User;
import my_slipp.slipp.web.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
  private static final String USER_SESSIONKEY = "loggedInUser";
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/signupForm")
  public String signupForm(Model model){
    return "signupForm";
  }

  @PostMapping("/create")
  public String createUser(User user) {
    if (user == null){
      throw new IllegalArgumentException("user is not created");
    }
    userRepository.save(user);
    return "redirect:/user/list";
  }

  @GetMapping("/loginForm")
  public String loginForm(Model model){
    return "loginForm";
  }

  @PostMapping("/login")
  public String login(String userId, String password, HttpSession httpSession){
    User user = userRepository.findByUserId(userId);
    if (!user.getPassword().equals(password)){
      return "login_failed";
    }

    httpSession.setAttribute(USER_SESSIONKEY, user.getUserId());
    return "redirect:/";
  }

  @GetMapping("/updateForm")
  public String updateForm(String userId, Model model){
    User user = userRepository.findByUserId(userId);
    model.addAttribute("user", user);
    return "updateForm";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Long id, User updateUser){
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()){
      return "redirect:/";
    }
    User existUser = user.get();
    existUser.update(updateUser);

    userRepository.save(existUser);
    return "redirect:/user/list";
  }

  @GetMapping("/list")
  public String list(Model model){
    model.addAttribute("users", userRepository.findAll());
    return "list";
  }
}
