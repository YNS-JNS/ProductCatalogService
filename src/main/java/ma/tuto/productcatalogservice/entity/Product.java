package ma.tuto.productcatalogservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal; // Utilisation de BigDecimal pour la précision monétaire

@Entity // Indique que cette classe est une entité JPA
@Table(name = "products") // Spécifie le nom de la table "products"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-généré
    private Long id; // Identifiant unique du produit

    @Column(name = "name", nullable = false, length = 150) // Colonne "name", non-nul, longueur max 150
    private String name; // Nom du produit

    @Column(name = "description", length = 1000) // Colonne "description", longueur max 1000
    private String description; // Description du produit

    @Column(name = "price", nullable = false, precision = 10, scale = 2) // Colonne "price", non-nul
    // precision: nombre total de chiffres
    // scale: nombre de chiffres après la virgule
    private BigDecimal price; // Prix du produit, BigDecimal pour la précision

    @Column(name = "quantity_in_stock", nullable = false) // Colonne "quantity_in_stock", non-nul
    private Integer quantityInStock; // Quantité disponible en stock

    // Relation Plusieurs-à-Un avec l'entité Category
    // Plusieurs produits peuvent appartenir à une seule catégorie
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: la catégorie n'est chargée que si on y accède
    @JoinColumn(name = "category_id", nullable = false) // Définit la colonne de clé étrangère "category_id"
    // nullable=false: un produit doit toujours avoir une catégorie
    private Category category; // Catégorie à laquelle le produit appartient
}