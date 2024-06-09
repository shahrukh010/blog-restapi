package com.code.main.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;



@Data
public class PostDto {

    private String uuid;

    @NotBlank
    @Size(min = 2, message = "min title should be 2")
    private String title;
    @NotBlank
    @Size(min = 10, message = "min description should be 10")
    private String description;
    @NotBlank
    @Size(min = 10, message = "min content should be 10")
    private String content;
}
