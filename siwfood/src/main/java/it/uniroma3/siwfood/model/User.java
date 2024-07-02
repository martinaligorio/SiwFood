package it.uniroma3.siwfood.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(name="data_nascita")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate data_nascita;
    
    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    private String surname;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Cuoco cuoco;

    public Cuoco getCuoco() {
        return cuoco;
    }

    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }


}