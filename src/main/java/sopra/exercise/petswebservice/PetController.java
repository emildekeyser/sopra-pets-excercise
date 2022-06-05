package sopra.exercise.petswebservice;
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.*;  
import reactor.core.publisher.Flux;  
import reactor.core.publisher.Mono;  

@Controller  
@RequestMapping(path = "/pets")  
public class PetController {  
  
    private PetRepository petRepository;  
    
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
  
    @PostMapping()  
    public @ResponseBody  
    Mono<Pet> addPet(@RequestBody Pet pet) {
        return petRepository.save(pet);  
    }  
  
    @GetMapping()  
    public @ResponseBody  
    Flux<Pet> getAllPets() {  
        return petRepository.findAll();  
    }
}
