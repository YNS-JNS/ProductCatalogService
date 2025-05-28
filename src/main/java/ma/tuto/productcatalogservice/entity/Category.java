package ma.tuto.productcatalogservice.entity;

import jakarta.persistence.*; // Importations pour les annotations JPA (Java Persistence API)
import lombok.Data; // Annotation Lombok pour générer getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor; // Annotation Lombok pour générer un constructeur sans arguments
import lombok.AllArgsConstructor; // Annotation Lombok pour générer un constructeur avec tous les arguments

import java.util.ArrayList;
import java.util.List; // Utilisation de l'interface List pour la collection de produits

@Entity // Indique que cette classe est une entité JPA, mappée à une table en base de données
@Table(name = "categories") // Spécifie le nom de la table ("categories") en base de données
@Data // Génère les méthodes standard (getters, setters, etc.)
@NoArgsConstructor // Constructeur sans arguments requis par JPA
@AllArgsConstructor // Constructeur avec tous les champs
public class Category {

    @Id // Marque ce champ comme clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configure la génération automatique de l'ID (auto-incrément)
    private Long id; // Identifiant unique de la catégorie

    @Column(name = "name", nullable = false, unique = true, length = 100) // Définit la colonne "name"
    // nullable=false: ne peut pas être nul
    // unique=true: doit être unique
    // length=100: longueur maximale de 100 caractères
    private String name; // Nom de la catégorie

    @Column(name = "description", length = 500) // Définit la colonne "description" avec une longueur maximale de 500
    private String description; // Description de la catégorie

    // Relation Un-à-Plusieurs avec l'entité Product
    // Une catégorie peut avoir plusieurs produits
    // Relation bidirectionnelle optionnelle
    @OneToMany(
            mappedBy = "category", // "category" est le nom du champ dans l'entité Product qui établit la relation
            cascade = CascadeType.ALL, // Les opérations (persist, merge, remove, etc.) sur Category sont propagées aux Products liés
            orphanRemoval = true, // Si un Product est retiré de cette liste, il est supprimé de la base
            fetch = FetchType.LAZY // Les produits ne sont chargés que lorsque la liste `products` est accédée explicitement
    )
    private List<Product> products = new ArrayList<>(); // Liste des produits appartenant à cette catégorie, initialisée
}
