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
@RequestMapping("/question")
public class QuestionController {
  @Autowired
  QuestionRepository questionRepository;

  @GetMapping("/form")
  public String form(Model model){
    model.addAttribute("questions", questionRepository.findAll());
    return "questionForm";
  }

  @GetMapping("/updateForm/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/";
    }

    model.addAttribute("question", question.get());
    return "questionUpdateForm";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Long id, Question updateQuestion){
    updateQuestion.setCreatedAt(LocalDateTime.now());
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/";
    }
    Question existQuestion = question.get();
    existQuestion.update(updateQuestion);

    questionRepository.save(existQuestion);
    return "redirect:/";
  }

  @GetMapping("/{id}")
  public String questionShow(@PathVariable Long id, Model model){
    System.out.println("questionShow function!!");
    System.out.println("Id: " + id);
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/";
    }
    model.addAttribute("question", question.get());
    return "questionShow";
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

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id){
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/";
    }
    Question deleteQuestion = question.get();
    questionRepository.delete(deleteQuestion);
    return "redirect:/";
  }
}
