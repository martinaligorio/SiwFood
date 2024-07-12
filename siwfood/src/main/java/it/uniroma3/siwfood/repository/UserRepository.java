package it.uniroma3.siwfood.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    public User findByCuoco(Cuoco cuoco);
}
