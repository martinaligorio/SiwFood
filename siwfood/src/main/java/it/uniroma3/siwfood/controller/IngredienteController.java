package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.service.IngredienteService;


@Controller
public class IngredienteController {
	
	@Autowired IngredienteService ingredienteService;
	
	
	
	//risponde a una GET HTTP che avrÃ  un URL del tipo /movie/1231
	@GetMapping("/ingrediente/{id}")//senza s
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingrediente.html";
	}//parametro id, viene convertito in Long e passato come parametro
	
	
	@GetMapping("/ingrediente")//senza s
	public String showIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "ingredienti.html"; //verificato: qua la s ce va
		
	}
	//ci da la lista di tutti i film
	//e la inserisce nel modello passato per parametro

	
	@GetMapping("/formNewIngrediente")
	public String formNewIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "formNewIngrediente.html";
	}
	
	
	@PostMapping("/ingrediente")
	public String newCuoco(@ModelAttribute("ingrediente") Ingrediente ingrediente) {
		this.ingredienteService.save(ingrediente);
		return "redirect:ingrediente/"+ingrediente.getId();
	}
	

}
