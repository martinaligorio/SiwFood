package it.uniroma3.siwfood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired 
	private CuocoService cuocoService;
	/*recupero utente da database in base al suo id*/

	public User getUser(Long id) {
		return userRepository.findById(id).get();}
	
	/*salvo utente nel database*/

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	/*ritorna la lista di user memorizzati nel database*/
	public List<User> getAllUser() {
		List<User> result = new ArrayList<>();
		Iterable<User> iterable = this.userRepository.findAll();
		for(User user : iterable)
			result.add(user);
		return result;
	}

	public void deleteUserByCuoco(Long idC) {
		Cuoco cuoco=this.cuocoService.findById(idC);
		this.userRepository.delete(this.userRepository.findByCuoco(cuoco));
    }

	public User findById(Long id) {
        return userRepository.findById(id).get();
    }
  	public User findByCuoco(Cuoco cuoco) {
        return userRepository.findByCuoco(cuoco);
    }

    public void deleteUserById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}

