package gr.athtech.spring.app.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseModel implements Serializable {
    private Long id;
}
