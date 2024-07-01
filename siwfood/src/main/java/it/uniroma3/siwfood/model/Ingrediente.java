package it.uniroma3.siwfood.model;

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
    private String urlimage;

    @ManyToOne
    @JoinColumn(name = "ricetta_id")
    private Ricetta ricetta;

    public Ingrediente() {}


    public String geturlimage() {
        return urlimage;
    }

    public void seturlimage(String uRL) {
     urlimage = uRL;
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


    public String getUrlimage() {
        return urlimage;
    }


    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }


    public Ricetta getRicetta() {
        return ricetta;
    }


    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }
}