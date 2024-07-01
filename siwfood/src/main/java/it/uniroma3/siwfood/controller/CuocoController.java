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

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.service.CuocoService;


@Controller
@RequestMapping("/cuochi")
public class CuocoController {
	
	@Autowired CuocoService cuocoService;
	
	
	@GetMapping("/{id}")//senza s
	public String getCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		return "cuoco.html";
	}
	
	
	@GetMapping
	public String showCuochi(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html"; 
		
	}

	@GetMapping("/formNewCuoco")
	public String formNewCuoco(Model model) {
		model.addAttribute("cuoco", new Cuoco());
		return "formNewCuoco.html";
	}
	
	
	@PostMapping("/savenewcuoco")
	public String newCuoco(@ModelAttribute Cuoco cuoco) {
		cuocoService.save(cuoco);
		return "redirect:/cuochi/"+cuoco.getId();
	}
	
	
	@PostMapping("/searchCuoco")
	public String searchCuoco(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.cuocoService.findByNome(nome)); 
        return "cuochi.html"; 
	}

	@PostMapping("/delete/{id}")
    public String deleteCuoco(@PathVariable Long id) {
        cuocoService.deleteCuocoById(id);
        return "redirect:/cuochi"; // Redirect alla lista delle ricette dopo la cancellazione
    }

	@GetMapping("/edit/{id}")
	public String getUpdateForm(@PathVariable Long id, Model model) {
    Cuoco cuoco = cuocoService.findById(id);
    model.addAttribute("cuoco", cuoco); // Aggiunge l'oggetto 'cuoco' al modello
    return "formUpdateCuoco.html"; // Ritorna il nome del template da renderizzare
	}

	@PostMapping("/update/{id}")
	public String updateCuoco(@PathVariable("id") Long id, @ModelAttribute Cuoco cuoco) {
    	cuoco.setId(id); // Imposta l'ID sulla cuoco per l'aggiornamento
    	this.cuocoService.save(cuoco); // Salva il cuoco aggiornato
    	return "redirect:/cuochi/" + cuoco.getId(); // Redirect alla pagina del cuoco aggiornato
}

}
