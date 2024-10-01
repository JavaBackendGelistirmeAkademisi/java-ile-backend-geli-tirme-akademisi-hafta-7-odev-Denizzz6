package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class UserRequestDTO {
    @NotNull(message = "İsim boş bırakılamaz.")
    private String name;

    @NotNull(message = "id boş bırakılamaz.")
    private int id;

    @NotNull(message = "kullanıcı rolü boş bırakılamaz.")
    private String role;

    @NotNull(message = "Email boş bırakılamaz.")
    private String email;

    @NotNull(message = "yorum boş bırakılamaz.")
    private String comment;

    @NotNull(message = "derecelendirme boş bırakılamaz.")
    private int rate;
}
