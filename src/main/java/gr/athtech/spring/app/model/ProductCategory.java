package gr.athtech.spring.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "PRODUCT_CATEGORIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRDUCT_CATEGORIES_SEQ", initialValue = 1, allocationSize = 1)
public class ProductCategory extends BaseModel{
    @NotNull
    @Column(length = 50, nullable = false)
    private String name;
}
