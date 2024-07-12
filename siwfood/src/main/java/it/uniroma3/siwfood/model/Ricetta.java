package it.uniroma3.siwfood.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "ricetta")
public class Ricetta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String descrizione;
    
    @ElementCollection
    private List<Immagine> immagini;

    @OneToMany(mappedBy="ricetta",cascade=CascadeType.ALL)
    private List<Ingrediente> ingredienti;

    @ManyToOne
    @JoinColumn(name = "cuoco_id")
    private Cuoco cuoco;
    
    public Ricetta(){
    }
    
    public Ricetta(String nome, String descr, List<Immagine> immagini, List<Ingrediente> ingr, Cuoco cuoco){
        this.nome = nome;
        this.descrizione = descr;
        this.immagini = immagini;
        this.ingredienti = ingr;
        this.cuoco = cuoco;
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
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
   
    @ManyToOne
    @JoinColumn(name = "cuoco_id", referencedColumnName = "id")
    public Cuoco getCuoco() {
        return cuoco;
    }
    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }

    @Override
    public String toString() {
        return "Ricetta [nome=" + nome + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((ingredienti == null) ? 0 : ingredienti.hashCode());
        result = prime * result + ((cuoco == null) ? 0 : cuoco.hashCode());
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
        Ricetta other = (Ricetta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descrizione == null) {
            if (other.descrizione != null)
                return false;
        } else if (!descrizione.equals(other.descrizione))
            return false;
        if (ingredienti == null) {
            if (other.ingredienti != null)
                return false;
        } else if (!ingredienti.equals(other.ingredienti))
            return false;
        if (cuoco == null) {
            if (other.cuoco != null)
                return false;
        } else if (!cuoco.equals(other.cuoco))
            return false;
        return true;
    }
    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }
    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagine> immagini) {
        this.immagini = immagini;
    }

    /*METODI PER LE IMMAGINI*/
    public Immagine getFirstImage(){
        if(this.immagini.size()==0)
            return null;
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
