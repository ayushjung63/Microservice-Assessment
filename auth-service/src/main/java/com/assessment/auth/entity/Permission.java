package com.assessment.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "permission_seq_gen")
    @SequenceGenerator(name = "permission_seq_gen",sequenceName = "permission_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

}