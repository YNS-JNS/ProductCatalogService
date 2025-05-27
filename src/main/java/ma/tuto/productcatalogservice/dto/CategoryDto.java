package ma.tuto.productcatalogservice.dto;

import jakarta.validation.constraints.NotBlank; // Pour la validation : le champ ne doit pas être nul et doit contenir au moins un caractère non blanc
import jakarta.validation.constraints.Size; // Pour la validation : contrainte sur la taille
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Génère getters, setters, etc.
@NoArgsConstructor // Constructeur sans arguments
@AllArgsConstructor // Constructeur avec tous les arguments
public class CategoryDto {

    private Long id; // Identifiant de la catégorie (généralement non fourni à la création)

    @NotBlank(message = "Category name cannot be blank") // Valide que le nom n'est pas vide ou nul
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters") // Valide la longueur du nom
    private String name; // Nom de la catégorie

    @Size(max = 500, message = "Category description can be up to 500 characters") // Valide la longueur de la description
    private String description; // Description de la catégorie
}