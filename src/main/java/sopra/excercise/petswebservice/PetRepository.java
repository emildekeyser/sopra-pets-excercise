package sopra.excercise.petswebservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PetRepository extends ReactiveMongoRepository<Pet, String> {}
