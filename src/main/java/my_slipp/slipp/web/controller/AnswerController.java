package my_slipp.slipp.web.controller;

import my_slipp.slipp.web.domain.Answer;
import my_slipp.slipp.web.domain.AnswerRepository;
import my_slipp.slipp.web.domain.Question;
import my_slipp.slipp.web.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/answer")
public class AnswerController {
  @Autowired
  AnswerRepository answerRepository;

  @Autowired
  QuestionRepository questionRepository;

  @PostMapping("/create/{id}")
  public String create(Answer answer, @PathVariable Long id){
    Optional<Question> question = questionRepository.findById(id);
    if (question.isEmpty()){
      return "redirect:/question/" + id;
    }

    answer.setQuestion(question.get());
    answer.setCreatedAt(LocalDateTime.now());
    answerRepository.save(answer);
    return "redirect:/question/" + id;
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id, String question_id){
    Optional<Answer> answer = answerRepository.findById(id);
    if (answer.isEmpty()){
      return "redirect:/question/" + id;
    }
    answerRepository.delete(answer.get());
    return "redirect:/question/" + id;
  }

}
