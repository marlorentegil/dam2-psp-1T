package es.fpsumma.dam2.cursos.persistence.jpa.entity;

import jakarta.persistence.*;

public class AlumnosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String pais;

    @Column(nullable = false)
    private Integer edad;


    @ManyToOne
    @JoinColumn(name="curso_id", nullable = false)
    private CursoEntity cursosEntity;


    public AlumnosEntity() {
    }

    public AlumnosEntity(Long id, String nombre, String apellido, String pais, Integer edad, CursoEntity cursosEntity) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.edad = edad;
        this.cursosEntity = cursosEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public CursoEntity getCursosEntity() {
        return cursosEntity;
    }

    public void setCursosEntity(CursoEntity cursosEntity) {
        this.cursosEntity = cursosEntity;
    }
}
