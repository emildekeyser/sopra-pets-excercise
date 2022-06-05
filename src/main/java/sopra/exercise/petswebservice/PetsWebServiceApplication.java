package sopra.exercise.petswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;  

@SpringBootApplication
public class PetsWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsWebServiceApplication.class, args);
	}

    @Bean
    ApplicationRunner init(PetRepository repository) {

        return args -> {
            repository
                .deleteAll()
                .thenMany(
                        Flux
                        .just(data)
                        .map(array -> {
                            return new Pet(null, (String) array[0], (Number) array[1]);
                        })
                        .flatMap(repository::save)
                        )
                .thenMany(repository.findAll())
                .subscribe(pet -> System.out.println("saving " + pet.toString()));
        };
    }

    private static Object[][] data = {
        { "Medor", 10 },
        { "Ed", 14 },
        { "Salmon", 5 },
        { "Mathew", 65 },
        { "Tom", 1 },
        { "Jerry", 56 },
        { "Tory", 9 },
        { "Ed", 13 },
        { "Rene", 4 },
        { "Sam", 54 },
        { "Mat", 77 },
        { "Sophie", 21 },
        { "Melissa", 6 },
        { "Vladimir", 98 },
        { "François", 2 },
        { "Paul", 76 },
        { "Adrien", 8 },
        { "Elodie", 14 },
        { "Amélie", 53 },
        { "Enzo", 26 }
    };
}
