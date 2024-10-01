package model.request;

import lombok.Getter;
import lombok.Setter;
import model.ProjectStatus;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProjectRequestDTO {
    @NotNull(message = "proje adı boş olamaz.")
    private String name;

    @NotNull(message = "proje idsi boş olamaz.")
    private int id;

    @NotNull(message = "açıklama boş olamaz.")
    private String note;

    @NotNull(message = "Kategori boş bırakılamaz.")
    private String category;

    @NotNull(message = "durum boş bırakılamaz")
    private ProjectStatus situation;;
}
