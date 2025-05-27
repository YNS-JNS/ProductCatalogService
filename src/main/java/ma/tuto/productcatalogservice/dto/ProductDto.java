package ma.tuto.productcatalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive; // Pour la validation : le nombre doit être strictement positif
import jakarta.validation.constraints.PositiveOrZero; // Pour la validation : le nombre doit être positif ou zéro
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id; // Identifiant du produit

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String name; // Nom du produit

    @Size(max = 1000, message = "Product description can be up to 1000 characters")
    private String description; // Description du produit

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be positive") // Le prix doit être positif
    private BigDecimal price; // Prix du produit

    @NotNull(message = "Quantity in stock cannot be null")
    @PositiveOrZero(message = "Quantity in stock must be positive or zero") // La quantité peut être zéro
    private Integer quantityInStock; // Quantité en stock

    @NotNull(message = "Category ID cannot be null for a product")
    private Long categoryId; // ID de la catégorie (utilisé pour la création/mise à jour)

    private String categoryName; // Nom de la catégorie (utilisé pour l'affichage, rempli par le service/mapper)

    /*
    NB: Note sur ProductDto: J'ai inclus categoryId pour l'entrée (création/mise à jour)
        et categoryName pour la sortie. Une approche plus poussée pourrait utiliser des DTOs distincts
        (ex: ProductCreateDto, ProductResponseDto). Pour cet exemple,
         on va gérer cela dans le mapper/service.
     */
}