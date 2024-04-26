package encora.challenger.api.msproducts.infraestructure.adapters.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product-storage")
public class ProductEntity {
  @Id
  private String id;
  private String name;
  private String description;
  @Field("document_number")
  private String documentNumber;
  private String state;
}
