/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author sportak
 */
public class Libro {
    
    private String categoria;
    private String titulo;
    private ArrayList<String> listaautor = new ArrayList<>();
    private String year;
    private Double precio;
    
    public Libro(String categoria, String titulo, String year, Double precio) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.year = year;
        this.precio = precio;
    }
    
    public Libro() {
        
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public ArrayList<String> getListaautor() {
        return listaautor;
    }
    
    public void setListaautor(ArrayList<String> listaautor) {
        this.listaautor = listaautor;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public Double getPrecio() {
        return precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public void imprimirActores() {
        if (this.listaautor.size() > 1) {
            System.out.println("***ACTORES EXTRA***");
            for (int i = 0; i < this.listaautor.size(); i++) {
                System.out.println(this.listaautor.get(i));
            }
        } else if (this.listaautor.size() == 1) {
            System.out.println(this.listaautor.get(0));
        }
    }
    
    @Override
    public String toString() {
        return "Libro{" + "categoria=" + categoria + ", titulo=" + titulo + ", listaautor=" + listaautor.size() + ", year=" + year + ", precio=" + precio + '}';
    }
    
}
