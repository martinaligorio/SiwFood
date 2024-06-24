package it.uniroma3.siwfood.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "ricetta")
public class Ricetta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descrizione;
    private String urlimage;

    @ManyToMany
    @JoinTable(
        name = "ricetta_ingrediente",
        joinColumns = @JoinColumn(name = "ricetta_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id", referencedColumnName = "id")
    )
    private List<Ingrediente> ingredienti;

    @ManyToOne
    private Cuoco cuoco;
    
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
    public String getUrlimage() {
        return urlimage;
    }
    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
        result = prime * result + ((urlimage == null) ? 0 : urlimage.hashCode());
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
        if (urlimage == null) {
            if (other.urlimage != null)
                return false;
        } else if (!urlimage.equals(other.urlimage))
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

    
}
