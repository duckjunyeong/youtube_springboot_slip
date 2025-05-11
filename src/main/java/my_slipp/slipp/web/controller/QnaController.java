package my_slipp.slipp.web.controller;

import my_slipp.slipp.web.domain.Question;
import my_slipp.slipp.web.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/qna")
public class QnaController {
  @Autowired
  QuestionRepository questionRepository;

  @GetMapping("/form")
  public String form(Model model){
    model.addAttribute("questions", questionRepository.findAll());
    return "qnaForm";
  }

  @GetMapping("/show/{id}")
  public String form(@PathVariable Long id, Model model){
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/";
    }
    model.addAttribute("question", question.get());
    return "qnaShow";
  }

  @PostMapping("/create")
  public String create(Question question){
    if (question == null){
      return "redirect:/";
    }

    question.setCreatedAt(LocalDateTime.now());
    questionRepository.save(question);
    return "redirect:/";
  }
}
