package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate dataDiNascita;
    private String urlimmagine;
   
    @OneToMany(mappedBy="cuoco")
    private List<Ricetta> ricette;
    
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
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    public String getUrlimmagine() {
        return urlimmagine;
    }
    public void setUrlimmagine(String urlimmagine) {
        this.urlimmagine = urlimmagine;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
        result = prime * result + ((urlimmagine == null) ? 0 : urlimmagine.hashCode());
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
        if (dataDiNascita == null) {
            if (other.dataDiNascita != null)
                return false;
        } else if (!dataDiNascita.equals(other.dataDiNascita))
            return false;
        if (urlimmagine == null) {
            if (other.urlimmagine != null)
                return false;
        } else if (!urlimmagine.equals(other.urlimmagine))
            return false;
        return true;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Ricetta> getRicette() {
        return ricette;
    }
    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }
    
}
