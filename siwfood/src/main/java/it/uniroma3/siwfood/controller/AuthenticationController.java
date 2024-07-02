package it.uniroma3.siwfood.controller;



import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Credentials;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Immagine;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.service.CredentialsService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.ImmagineService;
import it.uniroma3.siwfood.service.UserService;
import jakarta.validation.Valid;



@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CuocoService cuocoService;
    @Autowired
    private ImmagineService immagineService;

    @GetMapping(value = "/register")
    public String showRegisterForm (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("cuoco", new Cuoco());
        return "formRegisterUser.html";
    }

    @GetMapping(value = "/login")
    public String showLoginForm (Model model) {
        return "formLogin.html";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "index.html";
    }

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

        return "index.html";
    }

    @PostMapping(value = "/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @Valid
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               @ModelAttribute("cuoco") Cuoco cuoco,
                               @RequestParam("immagine") MultipartFile immagine,
                               Model model) throws IOException {
        
                                if(!immagine.isEmpty()){
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (cuoco.getImmagini() == null) {
                cuoco.setImmagini(new ArrayList<>());
            }
            cuoco.getImmagini().add(img);
            this.immagineService.save(img);
        }

         if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            //cuoco
            cuoco.setNome(user.getName());
            cuoco.setCognome(user.getSurname());
            cuoco.setData_nascita(user.getData_nascita());
            cuocoService.save(cuoco);

            user.setCuoco(cuoco);
            userService.saveUser(user);
        
            credentials.setUser(user);
            credentials.setRole(Credentials.CHEF_ROLE);
           
            credentialsService.saveCredentials(credentials);
           
            model.addAttribute("user", user);
 
            return "redirect:/";
          }
        return "formRegisterUser.html";
    }
}