package sopra.exercise.petswebservice;

import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
// import org.springframework.data.mongodb.core.mapping.Document;  

// @Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Number age;
}
