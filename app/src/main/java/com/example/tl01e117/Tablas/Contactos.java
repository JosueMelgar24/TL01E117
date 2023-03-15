package com.example.tl01e117.Tablas;

public class Contactos {
    private Integer id;
    private String nombre;
    private Integer numero;
    private String codigo;
    private String notas;


    public Contactos(Integer id, String nombre, String notas, String rub, Integer numero) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = rub;
        this.numero = numero;
        this.notas = notas;
    }

    public Contactos() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String rub) {
        this.codigo = rub ;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
