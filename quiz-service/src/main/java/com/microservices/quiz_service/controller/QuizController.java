package com.microservices.quiz_service.controller;

import com.microservices.quiz_service.model.QuestionWrapper;
import com.microservices.quiz_service.model.QuizDto;
import com.microservices.quiz_service.model.Response;
import com.microservices.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("get/{quizid}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizid){
        return quizService.getQuizQuestions(quizid);
    }

    @PostMapping("submit/{quizid}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizid, @RequestBody List<Response> response){
        System.out.println("Your quiz score is: ");
        return quizService.calculateResult(quizid, response);

    }

}
