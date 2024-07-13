package it.uniroma3.siwfood.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.service.CredentialsService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
//import it.uniroma3.siwfood.validator.CuocoValidator;
import it.uniroma3.siwfood.service.UserService;



@Controller
public class CuocoController extends GlobalController{
	@Autowired UserService userService;
	@Autowired CuocoService cuocoService;
	//@Autowired CuocoValidator cuocoValidator;
	@Autowired private ImmagineService immagineService;
	@Autowired CredentialsService credentialsService;

	@GetMapping("/cuochi/{id}")//senza s
	public String getCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		return "cuoco.html";
	}
	
	
	@GetMapping("/cuochi")
	public String showCuochi(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html"; 
		
	}

	@GetMapping("/admin/formNewCuoco")
	public String formNewCuoco(Model model) {
		if (!getCredentials().isAdmin()) {
            return "redirect:/error";
        }
		model.addAttribute("cuoco", new Cuoco());
		return "formNewCuoco.html";
	}
	
	
	@PostMapping("/admin/savenewcuoco")
	public String newCuoco(/*@Valid*/ @ModelAttribute Cuoco cuoco, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (cuoco.getImmagini() == null) {
                cuoco.setImmagini(new ArrayList<>());
            }
            cuoco.getImmagini().add(img);
            immagineService.save(img);
        }
        cuocoService.save(cuoco);
        return "redirect:/cuochi/" + cuoco.getId();
    }
	
	
	@PostMapping("/searchCuoco")
	public String searchCuoco(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.cuocoService.findByNome(nome)); 
        return "cuochi.html"; 
	}

	@PostMapping("/admin/deletecuoco/{id}")
    public String deleteCuoco(@PathVariable Long id) {
		if (!getCredentials().isAdmin()) {
            return "redirect:/error";
        }// Trova il cuoco da eliminare
        Cuoco cuoco = cuocoService.findById(id);
        // Trova l'utente associato al cuoco
        User user = userService.findByCuoco(cuoco);
        // Elimina le credenziali associate all'utente
        if (user != null) {
            credentialsService.deleteCredentialsByUser(user.getId());
        }
        // Elimina il cuoco
        cuocoService.deleteCuocoById(id);
        return "redirect:/cuochi"; // Redirect alla lista delle ricette dopo la cancellazione
    }

	@GetMapping("/admin/editcuoco/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    Cuoco cuoco = cuocoService.findById(id);
    User user = getCredentials().getUser();
	if (!getCredentials().isAdmin()) {
		return "redirect:/error";
	}
	model.addAttribute("cuoco", cuoco); // Aggiunge l'oggetto 'cuoco' al modello
    return "formUpdateCuoco.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/admin/updatecuoco/{id}")
	public String updateCuoco(@PathVariable("id") Long id, @ModelAttribute Cuoco cuoco, @RequestParam("immagine") MultipartFile immagine) throws IOException {
		if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (cuoco.getImmagini() == null) {
                cuoco.setImmagini(new ArrayList<>());
            }
            cuoco.getImmagini().add(img);
            immagineService.save(img);
        }
		cuoco.setId(id); // Imposta l'ID sulla cuoco per l'aggiornamento
    	this.cuocoService.save(cuoco); // Salva il cuoco aggiornato
    	return "redirect:/cuochi/" + cuoco.getId(); // Redirect alla pagina del cuoco aggiornato	
	}

}
