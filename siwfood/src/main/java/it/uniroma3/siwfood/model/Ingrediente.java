package it.uniroma3.siwfood.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String quantita;
    
    @ElementCollection
    private List<Immagine> immagini;


    @ManyToOne
    @JoinColumn(name = "ricetta_id")
    private Ricetta ricetta;

    public Ingrediente() {
    }

    public Ingrediente(String nome,String quantita,List<Immagine> immagini,Ricetta ricetta){
        this.nome = nome;
        this.quantita = quantita;
        this.immagini = immagini;
        this.ricetta = ricetta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Ricetta getRicetta() {
        return ricetta;
    }


    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }
    
    /*METODI PER LE IMMAGINI*/
    public Immagine getFirstImage(){
        return this.immagini.get(0);
    } 

    public List<Immagine> getImmaginiDopoFirst(){
        try {
            return this.immagini.subList(1, this.immagini.size());
        } catch (Exception e) {
            return null;
        }
    }
}