package it.uniroma3.siwfood.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
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

    @Column(name="datanascita")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dataDiNascita;
    private String urlimage;
   
    @OneToMany(mappedBy="cuoco")
    private List<Ricetta> ricette;
    
    @Override
    public String toString() {
        return "Cuoco: " + nome + " " + cognome;
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
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    public String getUrlimage() {
        return urlimage;
    }
    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
        result = prime * result + ((urlimage == null) ? 0 : urlimage.hashCode());
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
        if (urlimage == null) {
            if (other.urlimage != null)
                return false;
        } else if (!urlimage.equals(other.urlimage))
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
