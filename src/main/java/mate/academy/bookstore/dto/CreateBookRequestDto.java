package mate.academy.bookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Length(min = 1)
    private String title;
    @NotNull
    @Length(min = 1)
    private String author;
    @NotNull
    @Length(min = 10)
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
