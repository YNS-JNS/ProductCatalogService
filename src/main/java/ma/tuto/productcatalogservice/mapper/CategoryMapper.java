package ma.tuto.productcatalogservice.mapper;

import ma.tuto.productcatalogservice.dto.CategoryDto;
import ma.tuto.productcatalogservice.entity.Category;
import org.mapstruct.Mapper; // Annotation principale de MapStruct pour marquer une interface comme mapper
import org.mapstruct.MappingTarget; // Utilisé pour mettre à jour une instance existante
import org.mapstruct.factory.Mappers; // Fournit une instance du mapper (si non géré par Spring)

import java.util.List;

// Intégration Spring : Avec componentModel = "spring", vos mappers deviennent des beans Spring injectables.
// "spring": génère un bean Spring pour ce mapper, permettant l'injection
@Mapper(componentModel = "spring") // MapStruct génère une classe CategoryMapperImpl et l’enregistre comme bean Spring.
public interface CategoryMapper {

    // Optionnel: instance par défaut si vous n'utilisez pas l'injection de Spring partout
    // CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Convertit une entité Category en CategoryDto
    CategoryDto categoryToCategoryDto(Category category);

    // Convertit un CategoryDto en entité Category
    Category categoryDtoToCategory(CategoryDto categoryDto);

    // Convertit une liste d'entités Category en une liste de CategoryDto
    List<CategoryDto> categoriesToCategoryDtos(List<Category> categories);

    // Met à jour une entité Category existante à partir d'un CategoryDto
    // @MappingTarget indique que 'categoryEntity' est la cible de la mise à jour
    void updateCategoryFromDto(CategoryDto categoryDto, @MappingTarget Category categoryEntity);
}