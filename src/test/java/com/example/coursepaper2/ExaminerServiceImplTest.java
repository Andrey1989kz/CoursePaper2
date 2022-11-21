package com.example.coursepaper2;

import exception.MoreQuestionsThanStoredException;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ExaminerServiceImpl;
import service.QuestionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        List<Question> questions = List.of(
                new Question("Question 1", "Answer 1"),
                new Question("Question 2", "Answer 2"),
                new Question("Question 3", "Answer 3"),
                new Question("Question 4", "Answer 4"),
                new Question("Question 5", "Answer 5"),
                new Question("Question 6", "Answer 6"),
                new Question("Question 7", "Answer 7"),
                new Question("Question 8", "Answer 8"),
                new Question("Question 9", "Answer 9"),
                new Question("Question 10", "Answer 10")
        );
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getQuestionsPositiveTest() {
        Mockito.when(questionService.getRandomQuestion()).thenReturn(new Question("Question 1","Answer 1" ));

        assertThat(examinerService.getQuestions(1))
                .hasSize(1);

        Mockito.when(questionService.getRandomQuestion()).thenReturn(
                new Question("Question 1", "Answer 1"),
                new Question("Question 2", "Answer 2"),
                new Question("Question 3", "Answer 3")
        );
        assertThat(examinerService.getQuestions(3))
                .hasSize(3);
    }

    @Test
    void getQuestionsNegativeTest() {
        assertThatExceptionOfType(MoreQuestionsThanStoredException.class)
                .isThrownBy(() -> examinerService.getQuestions(
                        questionService.getAll().size() + 1));
    }
}