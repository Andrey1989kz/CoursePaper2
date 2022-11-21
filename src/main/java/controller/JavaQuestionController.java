package controller;

import model.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public Object addQuestion(    @RequestParam(value = "question") String questionText,
                                  @RequestParam(value = "answer") String answerText) {
        Question question;
        try {
            question = questionService.add(questionText, answerText);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return question;
    }

    @GetMapping(path = "/remove")
    public Object removeQuestion(
            @RequestParam(value = "question") String questionText,
            @RequestParam(value = "answer") String answerText) {
        Question question = new Question(questionText, answerText);
        try {
            question = questionService.remove(question);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return question;
    }

    @GetMapping()
    public Object getAll() {
        Collection<Question> questions = null;
        try {
            questions = questionService.getAll();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return questions;
    }
}