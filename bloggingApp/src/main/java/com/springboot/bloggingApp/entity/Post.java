package com.springboot.bloggingApp.entity;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "posts_title", length = 100 , nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String addImage;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;
}
