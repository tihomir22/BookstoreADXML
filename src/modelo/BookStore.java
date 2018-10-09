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
public class BookStore extends ArrayList {

    public BookStore() {
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public void autoImprimirme() {
        System.out.println("Datos de la bookstore");
        for (int i = 0; i < this.size(); i++) {
            Libro libro = (Libro) this.get(i);
            System.out.println(libro.toString());
            libro.imprimirActores();
        }
    }

}
