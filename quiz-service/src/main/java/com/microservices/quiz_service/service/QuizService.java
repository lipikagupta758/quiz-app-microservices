package com.microservices.quiz_service.service;

import com.microservices.quiz_service.dao.QuizDao;
import com.microservices.quiz_service.feign.QuizInterface;
import com.microservices.quiz_service.model.Question;
import com.microservices.quiz_service.model.QuestionWrapper;
import com.microservices.quiz_service.model.Quiz;
import com.microservices.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//      RestTemplate
        List<Integer> questions= quizInterface.getQuestionsForQuiz(category, numQ).getBody(); //RestTemplate-call the "generate" api of question microservice to get random questions
//      // Service Discovery is needed using Eureka Client
//      // OpenFeign can also be used here
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizid) {
        Optional<Quiz> quiz= quizDao.findById(quizid);
        List<Integer> questionIds= quiz.get().getQuestionIds();
        List<QuestionWrapper> questionsForUser= quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizid, List<Response> response){
        Integer score= quizInterface.getScore(response).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}