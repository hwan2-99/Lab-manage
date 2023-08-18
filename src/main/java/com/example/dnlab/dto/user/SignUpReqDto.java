package com.example.dnlab.dto.user;
import javax.validation.constraints.NotBlank;
import com.example.dnlab.domain.User;
import lombok.Getter;

@Getter
public class SignUpReqDto {
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;
    @NotBlank(message = "학번은 필수 입력 항목입니다.")
    private int studentId;
    @NotBlank(message = "id는 필수 입력 항목입니다.")
    private String id;
    @NotBlank(message = "패스워드는 필수 입력 항목입니다.")
    private String pw;

    public User toEntity(){
        return User.builder()
                .name(name)
                .studentId(studentId)
                .id(id)
                .pw(pw)
                .build();
    }
}
