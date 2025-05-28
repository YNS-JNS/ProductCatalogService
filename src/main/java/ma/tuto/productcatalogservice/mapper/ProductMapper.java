package ma.tuto.productcatalogservice.mapper;

import ma.tuto.productcatalogservice.dto.ProductDto;
import ma.tuto.productcatalogservice.entity.Product;
import ma.tuto.productcatalogservice.entity.Category; // Nécessaire pour le mapping de categoryId
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Pour spécifier des mappings personnalisés entre champs
import org.mapstruct.MappingTarget;
import org.mapstruct.Named; // Pour définir des méthodes de mapping personnalisées réutilisables

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) // 'uses' permet d'utiliser d'autres mappers (CategoryMapper ici)
public interface ProductMapper {

    // Mappe l'entité Product vers ProductDto
    // Source "category.id" vers target "categoryId"
    // Source "category.name" vers target "categoryName"
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDto productToProductDto(Product product);

    /*
    * Nb:
    * uses = CategoryMapper.class
    * Permet à MapStruct d’appeler CategoryMapper pour tout mapping Category ↔ CategoryDto.
    * @Mapping(target = "categoryId", source = "category.id")
    * Quand on convertit l’entité Product en ProductDto, la propriété dto.categoryId prend la valeur entity.getCategory().getId().
    * @Mapping(target = "categoryName", source = "category.name")
    * On récupère aussi category.name pour l’affichage.
    * */

    // Mappe ProductDto vers l'entité Product
    // Ignore "categoryName" car il n'est pas un champ de l'entité Product
    // Le champ "category" (de type Category) sera géré par le service (fetch by categoryId)
    @Mapping(target = "category", ignore = true) // Le service s'occupera de lier la catégorie
    Product productDtoToProduct(ProductDto productDto);

    // Convertit une liste d'entités Product en une liste de ProductDto
    List<ProductDto> productsToProductDtos(List<Product> products);

    // Met à jour une entité Product existante à partir d'un ProductDto
    // Ignore "id" car l'ID ne doit pas être modifié par le DTO
    // Le champ "category" sera géré par le service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateProductFromDto(ProductDto productDto, @MappingTarget Product productEntity);
}