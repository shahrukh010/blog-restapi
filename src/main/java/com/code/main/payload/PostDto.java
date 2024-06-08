package com.code.main.payload;

import lombok.Data;

@Data
public class PostDto {

    private String uuid;
    private String title;
    private String description;
    private String content;
}
