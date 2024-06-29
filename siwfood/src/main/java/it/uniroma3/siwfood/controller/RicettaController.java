package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.IngredienteService;
import it.uniroma3.siwfood.service.RicettaService;


@Controller
public class RicettaController {
	
	@Autowired RicettaService ricettaService;
	@Autowired IngredienteService ingredienteService;
	
	@GetMapping("/ricetta/{id}")
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
	@GetMapping("/formNewRicetta")
	public String formNewRicetta(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		model.addAttribute("messaggioErrore", "");
		return "formNewRicetta.html";
	}
	
	//gestisce il salvataggio della ricetta e il reindirizzamento alla pagina della ricetta appena creata
	@PostMapping("/ricetta")
	public String newRicetta(@ModelAttribute Ricetta ricetta) {
		this.ricettaService.save(ricetta);
		return "redirect:/ricetta/"+ ricetta.getId();
	}
	
	@PostMapping("/searchRicetta")
	public String searchRicetta(Model model, @RequestParam String nome) {
		model.addAttribute("ricette", this.ricettaService.findByNome(nome)); 
        return "ricette.html"; 
	}

	@PostMapping("/searchByIngrediente")
    public String searchByIngrediente(Model model, @RequestParam String ingrediente) {
        model.addAttribute("ricette", this.ricettaService.findByIngredienteNome(ingrediente)); 
        return "ricette.html"; 
    }


	@GetMapping("/formSearch")
	public String getFormSearch() {
		return "formSearch.html";
	}
	
}