package service;

import exception.MoreQuestionsThanStoredException;
import model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;
    private final Collection<Question> randomQuestions = new HashSet<>();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new MoreQuestionsThanStoredException("Not enough questions");
        }
        randomQuestions.clear();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }
        return randomQuestions;
    }
}
