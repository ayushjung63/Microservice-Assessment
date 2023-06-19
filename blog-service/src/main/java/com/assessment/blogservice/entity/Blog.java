package com.assessment.blogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @SequenceGenerator(name = "blog_seq_gen",sequenceName = "blog_seq",allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blog_seq_gen")
    private Integer id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",
            columnDefinition = "TEXT",
            nullable = false)
    private String description;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "publised_date",nullable = false)
    private LocalDate publishedDate;
}
