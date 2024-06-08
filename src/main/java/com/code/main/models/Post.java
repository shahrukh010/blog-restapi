package com.code.main.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", columnDefinition = "varchar(100)", nullable = true)
    private String title;
    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

}
