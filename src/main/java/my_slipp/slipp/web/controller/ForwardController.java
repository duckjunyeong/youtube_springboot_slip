package my_slipp.slipp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

  @GetMapping("/form")
  public String form(){
    return "form";
  }
}
