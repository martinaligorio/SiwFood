package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.service.CuocoService;


@Controller
public class CuocoController {
	
	@Autowired CuocoService cuocoService;
	
	
	@GetMapping("/cuoco/{id}")//senza s
	public String getCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		return "cuoco.html";
	}
	
	
	@GetMapping("/cuochi")
	public String showCuochi(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html"; 
		
	}

	@GetMapping("/formNewCuoco")
	public String formNewCuoco(Model model) {
		model.addAttribute("cuoco", new Cuoco());
		return "formNewCuoco.html";
	}
	
	
	@PostMapping("/cuoco")
	public String newCuoco(@ModelAttribute Cuoco cuoco) {
		this.cuocoService.save(cuoco);
		return "redirect:/cuoco/"+cuoco.getId();
	}
	
	
	@PostMapping("/searchCuoco")
	public String searchCuoco(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.cuocoService.findByNome(nome)); 
        return "cuochi.html"; 
	}

	@PostMapping("/cuoco/delete/{id}")
    public String deleteCuoco(@PathVariable Long id) {
        cuocoService.deleteCuocoById(id);
        return "redirect:/cuochi"; // Redirect alla lista delle ricette dopo la cancellazione
    }
}
