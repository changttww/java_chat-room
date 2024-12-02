package com.example.chatchatbackend.entity.vo.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterVO {
    @Length(min = 1, max = 20)
    String username;
    @Length(min = 8, max = 20)
    String password;
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$")
            @Length(min = 6, max = 20)
    String email;
}
