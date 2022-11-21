package com.example.coursepaper2;

import model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.JavaQuestionService;
import service.QuestionService;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void AfterEach() {
        questionService.getAll().forEach(questionService::remove);    }

    @ParameterizedTest
    @MethodSource("params")
    void addPositiveTest(String question, String answer) {
        assertThat(questionService.getAll()).isEmpty();

        Question expected = questionService.add(new Question(question, answer));

        assertThat(questionService.getAll())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(questionService.add(question, answer)).isEqualTo(null);
    }

    @ParameterizedTest
    @MethodSource("params")
    void removePositiveTest(String question, String answer) {
        assertThat(questionService.getAll()).isEmpty();

        questionService.add(new Question(question, answer));
        Question expected = questionService.remove(new Question(question, answer));

        assertThat(expected).isEqualTo(new Question(question, answer));
        assertThat(questionService.remove(new Question(question, answer))).isEqualTo(null);
    }


    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Question1", "Answer1")
        );
    }
}