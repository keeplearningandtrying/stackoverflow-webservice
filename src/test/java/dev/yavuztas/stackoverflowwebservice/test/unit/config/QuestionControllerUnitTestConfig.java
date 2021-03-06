package dev.yavuztas.stackoverflowwebservice.test.unit.config;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.repository.QuestionRepository;
import dev.yavuztas.stackoverflowwebservice.service.IQuestionService;
import dev.yavuztas.stackoverflowwebservice.service.QuestionService;
import dev.yavuztas.stackoverflowwebservice.test.mock.MockQuestions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TestConfiguration
public class QuestionControllerUnitTestConfig {

    @MockBean
    public QuestionRepository questionRepository;

    @Bean
    public IQuestionService questionService() {
        QuestionService questionService = Mockito.mock(QuestionService.class);
        List<Question> mocks = Arrays.asList(MockQuestions.question1, MockQuestions.question2);

        Mockito.when(questionService.getAllQuestions()).thenReturn(mocks);
        Mockito.when(questionService.getQuestion(1L)).thenReturn(MockQuestions.question1);
        Mockito.when(questionService.getQuestion(99L)).thenThrow(new QuestionNotFoundException(99L));
        Mockito.doThrow(new QuestionNotFoundException(99L)).when(questionService).deleteQuestion(99L);
        Mockito.when(questionService.getQuestionsByTags(MockQuestions.TAGS1)).thenReturn(Collections.singletonList(MockQuestions.question1));

        return questionService;
    }

}
