package sopra.excercise.petswebservice;
  
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

    @GetMapping(value = "/{id}")
    public @ResponseBody  
    Mono<Pet> getPetById(@PathVariable String id) {  
        return petRepository.findById(id);  
    }

    @PatchMapping(value = "/{id}")
    public @ResponseBody
    Mono<Pet> updatePet(@PathVariable String id, @RequestParam(required=false) String name, @RequestParam(required=false) Number age) {

        /// XXX: This could be improved by iterating a RequestParam Map<Str,Str>.
        // Also obviously in a real app with would have much more input validation, and appropriate error messages to return.

        if (name == null && age == null) {
            return Mono.empty();
        }

        return petRepository
            .findById(id)
            .flatMap(p -> {
                if (name != null) {
                    p.setName(name);
                }
                if (age != null) {
                    p.setAge(age);
                }
                return petRepository.save(p);
            });
    }
}
