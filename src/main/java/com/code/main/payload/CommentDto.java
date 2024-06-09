package com.code.main.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CommentDto {

    @NotEmpty
    @Size(min = 3,message = "name should be minimum 3 character")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "min body should be 10")
    private String body;
}
