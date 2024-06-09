package com.code.main.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    private String name;
    private String email;
    private String body;
}
