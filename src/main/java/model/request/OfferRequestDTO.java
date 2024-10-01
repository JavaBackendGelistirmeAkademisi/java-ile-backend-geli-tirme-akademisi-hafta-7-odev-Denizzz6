package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OfferRequestDTO {
    @NotNull(message = "İsim boş bırakılamaz")
    private String name;

    @NotNull(message = "Fiyat boş bırakılamaz.")
    @Size(message = "Negatif sayı veya karakter girilemez.")
    private int price;

    @NotNull(message = "Açıklama boş bırakılamaz.")
    @Size(message = "10.000 karakterden fazla yazılamaz.")
    private String terms;

    @NotNull(message = "id boş bırakılamaz.")
    private int id;
}
