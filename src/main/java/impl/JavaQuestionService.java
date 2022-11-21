package service;

import model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }


    @Override
    public Question add(String question, String answer) {
        Question x = new Question(question, answer);
        return add(x);
    }

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.stream()
                .skip(new Random().nextInt(questions.size()))
                .findFirst()
                .orElse(null);
    }
}