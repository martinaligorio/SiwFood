package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuoco")
public class Cuoco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;

    @Column(name="data_nascita")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate data_nascita;
    
    @ElementCollection
    private List<Immagine> immagini;
   
    @OneToMany(mappedBy="cuoco",cascade=CascadeType.ALL)
    private List<Ricetta> ricette;
    
    public Cuoco(){
    }

    public Cuoco(String nome, String cognome, List<Immagine> immagini, LocalDate data_nascita, List<Ricetta> ricette){
        this.nome = nome;
        this.cognome = cognome;
        this.immagini = immagini;
        this.data_nascita = data_nascita;
        this.ricette = ricette;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    
    public List<Ricetta> getRicette() {
        return ricette;
    }

    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }

    @Override
    public String toString() {
        return "Cuoco: " + nome+ " " + cognome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((data_nascita == null) ? 0 : data_nascita.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuoco other = (Cuoco) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        if (data_nascita == null) {
            if (other.data_nascita != null)
                return false;
        } else if (!data_nascita.equals(other.data_nascita))
            return false;
        return true;
    }

    /*METODI PER LE IMMAGINI*/
    public Immagine getFirstImmagine(){
        return this.immagini.get(0);
    } 

    public List<Immagine> getImmaginiDopoFirst(){
        try {
            return this.immagini.subList(1, this.immagini.size());
        } catch (Exception e) {
            return null;
        }
    }
    
     //immagine
     public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagine) {
        this.immagini = immagine;
    }
  

}