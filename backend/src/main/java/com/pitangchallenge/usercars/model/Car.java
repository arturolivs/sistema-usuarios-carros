package com.pitangchallenge.usercars.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacture_year")
    private int year;

    private String licensePlate;
    private String model;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}