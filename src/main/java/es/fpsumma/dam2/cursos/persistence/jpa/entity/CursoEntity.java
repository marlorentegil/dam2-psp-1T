package es.fpsumma.dam2.cursos.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "cursos")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(name = "duracion_en_minutos",  nullable = true)
    private Integer duracionEnMinutos;

    @Column(nullable = true, length = 10)
    private Double precio;


    public CursoEntity() {
    }

    public CursoEntity(Long id, String titulo, String categoria, Integer duracionEnMinutos, Double precio, AlumnosEntity alumnosEntity) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
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
