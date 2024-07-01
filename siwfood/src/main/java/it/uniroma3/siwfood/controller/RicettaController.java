package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;
;



@Controller
@RequestMapping("/ricette")
public class RicettaController {
	
	@Autowired RicettaService ricettaService;
	@Autowired IngredienteService ingredienteService;
	@Autowired private CuocoService cuocoService;

	@GetMapping("/{id}")
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaService.findById(id));
		return "ricetta.html";
	}
	
	
	@GetMapping
	public String showRicette(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "ricette.html";
		
	}
	
	//gestisce la visualizzazione del form
	@GetMapping("/formNewRicetta/{cuoco_id}")
	public String formNewRicetta(@PathVariable("cuoco_id") Long id,Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		model.addAttribute("ricetta", new Ricetta());
		return "formNewRicetta.html";
	}
	
	//gestisce il salvataggio della ricetta e il reindirizzamento alla pagina della ricetta appena creata
	@PostMapping("/save/{cuoco_id}")
	public String newRicetta(@PathVariable("cuoco_id") Long id,@ModelAttribute Ricetta ricetta) {
		ricetta.setCuoco(this.cuocoService.findById(id));	
		this.ricettaService.save(ricetta);
			return "redirect:/ricette/"+ ricetta.getId();
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
	@PostMapping("/delete/{id}")
    public String deleteRicetta(@PathVariable Long id) {
        ricettaService.deleteRicettaById(id);
        return "redirect:/ricette"; // Redirect alla lista delle ricette dopo la cancellazione
    }
	
	@GetMapping("/edit/{id}")  //Mappa le richieste GET all'URL /ricetta/update/{id}.
    public String getUpdateForm(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("ricetta", this.ricettaService.findById(id)); 
        return "formUpdateRicetta.html"; //Indica il nome del template che Spring deve utilizzare per generare la risposta HTML.
	}

    //@ModelAttribute: Associa i dati del modulo ai parametri del metodo.
    @PostMapping("/update/{id}")
    public String updateRicetta(@PathVariable("id") Long id, @ModelAttribute Ricetta ricetta) {
        ricetta.setId(id);
		this.ricettaService.save(ricetta);
        return "redirect:/ricette/" + ricetta.getId(); // Redirect alla pagina della ricetta aggiornata
    }
}