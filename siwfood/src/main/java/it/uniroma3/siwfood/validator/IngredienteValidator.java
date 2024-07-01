package it.uniroma3.siwfood.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.repository.IngredienteRepository;
/* 
@Component 
public class IngredienteValidator implements Validator{
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
           
    @Override
    public void validate(Object o, Errors errors) {
        Ingrediente ingrediente = (Ingrediente)o;
        if (ingrediente.getNome()!=null && ingrediente.getQuantita()!=null
            && ingredienteRepository.existsByNomeAndQuantita(ingrediente.getNome(), ingrediente.getQuantita())) {
            errors.reject("ingrediente.duplicate");
        }
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Ingrediente.class.equals(aClass);
    }
}
*/