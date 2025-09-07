package com.microservices.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String title;
    @ElementCollection   //or collections of basic/embeddable types (value objects).
    private List<Integer> questionIds;
}
