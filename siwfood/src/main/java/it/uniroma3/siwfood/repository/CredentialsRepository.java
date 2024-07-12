package it.uniroma3.siwfood.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Credentials;
import it.uniroma3.siwfood.model.User;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findById(Long id);
	public Optional<Credentials> findByUsername(String username);

	public Credentials findByUser(User user);
}
