package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.service.RicettaService;
@Controller
public class RicettaController {
	
	@Autowired RicettaService ricettaService;
	
	//risponde a una GET HTTP che avrÃ  un URL del tipo /movie/1231
	@GetMapping("/ricetta/{id}")//senza s
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaService.findById(id));
		return "ricetta.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
	
	@GetMapping("/ricette")//senza s
	public String showRicette(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "ricette.html"; //verificato: qua la s ce va
		
	}
	//ci da la lista di tutti i film
	//e la inserisce nel modello passato per parametro

	
	@GetMapping("/formNewRicetta")
	public String formNewRicetta(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		return "formNewRicetta.html";
	}
	
	
	@PostMapping("/ricetta")
	public String newRicetta(@ModelAttribute("ricetta") Ricetta ricetta) {
		this.ricettaService.save(ricetta);
		return "redirect:ricetta/"+ricetta.getId();
	}
	
	 

}
