package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentRequestDTO {
    @NotNull(message = "ödeme türü boş bırakılamaz")
    private String type;

    @NotNull(message = "isim boş bırakılamaz")
    private String name;

    @NotNull(message = "id boş bırakılamaz.")
    private int id;

    @NotNull(message = "miktar boş bırakılamaz.")
    private int amount;

    @NotNull(message = "açıklama boş bırakılamaz.")
    private String note;
}
