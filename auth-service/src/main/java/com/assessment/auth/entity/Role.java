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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen",sequenceName = "role_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "permission_id", referencedColumnName = "id")
    )
    private List<Permission> permissionList;

    public Role(String name){
        this.name=name;
    }
}