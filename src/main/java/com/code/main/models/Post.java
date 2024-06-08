package com.code.main.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;
    @Column(name = "title", columnDefinition = "varchar(100)", nullable = true)
    private String title;
    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;
    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

}
