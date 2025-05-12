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
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/signupForm")
  public String signupForm(Model model){
    return "userSignupForm";
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
  public String login(String userId, String password, HttpSession httpSession, Model model){
    User user = userRepository.findByUserId(userId);
    if (!user.getPassword().equals(password)){
      return "login_failed";
    }

    httpSession.setAttribute("user", user);
    return "redirect:/";
  }

  @GetMapping("/updateForm/{id}")
  public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession){
    User sessionUser = (User) httpSession.getAttribute("user");
    if (sessionUser.getUserId() == null){
      return "redirect:/user/list";
    }

    if (sessionUser.getId() != id){
      return "redirect:/user/list";
    }

    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()){
      return "redirect:/user/list";
    }

    model.addAttribute("user", user.get());
    return "userUpdateForm";
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

  @GetMapping("/logout")
  public String logout(HttpSession httpSession){
    httpSession.removeAttribute("user");
    return "redirect:/";
  }
}
