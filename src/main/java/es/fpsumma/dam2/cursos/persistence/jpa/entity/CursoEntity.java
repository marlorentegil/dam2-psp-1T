package es.fpsumma.dam2.cursos.persistence.jpa.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "cursos")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String categoria;
    @Column(name = "duracion")
    private Integer duracionEnMinutos;

    private Double precio;

    public CursoEntity() {
    }

    public CursoEntity(Long id, String titulo, String ciudad, Integer duracionEnMinutos, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = ciudad;
        this.duracionEnMinutos = duracionEnMinutos;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String ciudad) {
        this.categoria = ciudad;
    }

    public Integer getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(Integer duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
