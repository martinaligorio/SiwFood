package it.uniroma3.siwfood.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.uniroma3.siwfood.model.Credentials;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Autowired  private UserService userService;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	public Credentials getCredentials(Long id) {
		return credentialsRepository.findById(id).get();}
	

	public Credentials getCredentials(String username) {
		return credentialsRepository.findByUsername(username).get();
	}
	
	
	public Credentials saveCredentials(Credentials credentials) {
		String oldPassword = credentials.getPassword();
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return credentialsRepository.save(credentials);
   }
   public void deleteCredentialsByUser(Long idU) {
	User user = userService.findById(idU);
        Credentials credentials = credentialsRepository.findByUser(user);
        credentialsRepository.delete(credentials);
    }
}
