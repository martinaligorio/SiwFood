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
import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;
//import it.uniroma3.siwfood.validator.IngredienteValidator;
//import it.uniroma3.siwfood.validator.RicettaValidator;
import jakarta.validation.Valid;
;



@Controller
public class RicettaController extends GlobalController{
	
	@Autowired RicettaService ricettaService;
	@Autowired IngredienteService ingredienteService;
	@Autowired private CuocoService cuocoService;
	@Autowired private ImmagineService immagineService;
	//@Autowired RicettaValidator ricettaValidator;
	////@Autowired IngredienteValidator ingredienteValidator;

	@GetMapping("/ricette/{id}")
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaService.findById(id));
		return "ricetta.html";
	}
	
	
	@GetMapping("/ricette")
	public String showRicette(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "ricette.html";
		
	}
	
	//gestisce la visualizzazione del form
	@GetMapping("/chef/formNewRicetta/{cuoco_id}")
	public String formNewRicetta(@PathVariable("cuoco_id") Long id,Model model) {
		User user = getCredentials().getUser();
        if ((!getCredentials().isAdmin()
                && cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId() != id)
                || cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId() != id) {
            return "redirect:/error";
        }
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		model.addAttribute("ricetta", new Ricetta());
		return "formNewRicetta.html";
	}
	
	//gestisce il salvataggio della ricetta e il reindirizzamento alla pagina della ricetta appena creata
	@PostMapping("/chef/savericetta/{cuoco_id}")
	public String newRicetta(/*@Valid*/ @PathVariable("cuoco_id") Long id, @ModelAttribute Ricetta ricetta, @RequestParam("immagine") MultipartFile immagine) throws IOException {
        
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (ricetta.getImmagini() == null) {
                ricetta.setImmagini(new ArrayList<>());
            }
            ricetta.getImmagini().add(img);
            immagineService.save(img);
        }
		Cuoco cuoco =this.cuocoService.findById(id);
        ricetta.setCuoco(cuoco);	
		this.ricettaService.save(ricetta);
		return "redirect:/ricette/"+ ricetta.getId();
    }
	
	@PostMapping("/chef/saveIngredienti/{ricetta_id}")
	public String newIngredienti(/*@Valid*/ @PathVariable("ricetta_id") Long id, @ModelAttribute Ingrediente ingrediente,@RequestParam("immagine") MultipartFile immagine) throws IOException {/* ,BindingResult bindingResult*/
		//this.ingredienteValidator.validate(ingrediente, bindingResult);
		//if (!bindingResult.hasErrors()) {
			if (!immagine.isEmpty()) {
				Immagine img = new Immagine();
				img.setFileName(immagine.getOriginalFilename());
				img.setImageData(immagine.getBytes());
				if (ingrediente.getImmagini() == null) {
					ingrediente.setImmagini(new ArrayList<>());
				}
				ingrediente.getImmagini().add(img);
				immagineService.save(img);
			}
			Ricetta ricetta = this.ricettaService.findById(id);
			ingrediente.setRicetta(ricetta);
			ricetta.getIngredienti().add(ingrediente);
			this.ingredienteService.save(ingrediente);
			return "redirect:/chef/editricetta/"+ ricetta.getId();
		//} else {
		//	return "formNewIngrediente.html";
		//}
	}

	@PostMapping("/searchRicetta")
	public String searchRicetta(Model model, @RequestParam String nome) {
		model.addAttribute("ricette", this.ricettaService.findByNome(nome)); 
        return "ricette.html"; 
	}
	//@RequestParam: Estrae parametri di query dalla richiesta.
	@PostMapping("/searchByIngrediente")
    public String searchByIngrediente(Model model, @RequestParam String ingrediente) {
        model.addAttribute("ricette", this.ricettaService.findByIngredienteNome(ingrediente)); 
        return "ricette.html"; 
    }

	@GetMapping("/formSearch")
	public String getFormSearch() {
		return "formSearch.html";
	}

	//@PathVariable Estrae variabili di percorso dall'URL della richiesta.
	@PostMapping("/chef/deletericetta/{id}")
	public String deleteRicetta(@PathVariable Long id) {
		// Recupera l'utente corrente
		User user = getCredentials().getUser();
		// Trova la ricetta da eliminare
		Ricetta ricetta = ricettaService.findById(id);
		// Verifica se la ricetta esiste
		if (ricetta == null) {
			return "redirect:/error?message=Ricetta non trovata";
		}
		// Controlla se l'utente ha i permessi per eliminare la ricetta
		Long cuocoId = cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId();
		if (!getCredentials().isAdmin() && !cuocoId.equals(ricetta.getCuoco().getId())) {
			return "redirect:/error?message=Permessi insufficienti";
		}
		// Elimina la ricetta
		ricettaService.deleteRicettaById(id);
		// Redirect alla lista delle ricette dopo la cancellazione
		return "redirect:/ricette";
	}

	
	@GetMapping("/chef/editricetta/{id}")  //Mappa le richieste GET all'URL /ricetta/update/{id}.
    public String getUpdateForm(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.findById(id);
        
		User user = getCredentials().getUser();
        if ((!getCredentials().isAdmin()
                && cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId() != ricetta.getCuoco()
                        .getId())
                || cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId() != ricetta.getCuoco()
                        .getId()) {
            return "redirect:/error";
        }
		
    	model.addAttribute("ricetta", this.ricettaService.findById(id)); 
        model.addAttribute("ingredienti", this.ricettaService.findById(id).getIngredienti());
		model.addAttribute("ingrediente", new Ingrediente());
		return "formUpdateRicetta.html"; //Indica il nome del template che Spring deve utilizzare per generare la risposta HTML.
	}

    //@ModelAttribute: Associa i dati del modulo ai parametri del metodo.
    @PostMapping("/chef/updatericetta/{id}/{cuoco_id}")
    public String updateRicetta(@PathVariable("id") Long id, @ModelAttribute Ricetta ricetta, @PathVariable("cuoco_id") Long cuoco_id,@RequestParam("immagine") MultipartFile immagine) throws IOException {
        if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (ricetta.getImmagini() == null) {
                ricetta.setImmagini(new ArrayList<>());
            }
            ricetta.getImmagini().add(img);
            immagineService.save(img);
        }
		ricetta.setId(id);
		this.ricettaService.addRicettaToCuoco(ricetta, cuoco_id);
		return "redirect:/ricette/" + ricetta.getId(); // Redirect alla pagina della ricetta aggiornata
    }

	@GetMapping("/ricette/{ricettaId}/ingrediente/{ingredienteId}")
    public String getIngredienteDetails(@PathVariable Long ricettaId, @PathVariable Long ingredienteId, Model model) {
        Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
        model.addAttribute("ingrediente", ingrediente);
        return "ingrediente.html";
    }

    @PostMapping("/chef/ricette/{ricettaId}/ingrediente/{ingredienteId}/delete")
	public String deleteIngrediente(@PathVariable Long ricettaId, @PathVariable Long ingredienteId) {
    // Recupera l'utente corrente
    User user = getCredentials().getUser();
    // Trova la ricetta da cui eliminare l'ingrediente
    Ricetta ricetta = ricettaService.findById(ricettaId);
    // Trova l'ingrediente da eliminare
    Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
    if (ingrediente == null) {
        return "redirect:/error?message=Ingrediente non trovato";
    }
    // Verifica se l'utente ha i permessi per eliminare l'ingrediente
    Long cuocoId = cuocoService.findbyNomeCognome(user.getName(), user.getSurname()).getId();
    if (!getCredentials().isAdmin() && !cuocoId.equals(ricetta.getCuoco().getId())) {
        return "redirect:/error?message=Permessi insufficienti";
    }
    ingredienteService.deleteIngredienteById(ingredienteId);
    // Redirect alla pagina della ricetta aggiornata
    return "redirect:/ricette/" + ricettaId;
}

}
