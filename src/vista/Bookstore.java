/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlDom;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.BookStore;
import modelo.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sportak
 */
public class Bookstore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        controlDom ctrlDom = new controlDom();
        int opcion = 999;
        String ruta = "", ruta2 = "";
        Document doc = null;
        Document doc2 = null;
        BookStore tiendaLibros = null;
        BookStore tiendaLibros2 = null;
        Scanner teclado = new Scanner(System.in);

        while (opcion != 0) {
            mostrarMenu();
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introduzca ruta o dejelo en blanco para seleccionar la predeterminada (bookstore.xml)");
                    teclado.nextLine();
                    ruta = teclado.nextLine();
                    if (ruta == "") {
                        doc = ctrlDom.recuperar(new File(ruta));
                    } else {
                        doc = ctrlDom.recuperar(new File("bookstore.xml"));
                    }
                    break;

                case 2:
                    tiendaLibros = new BookStore();
                    tiendaLibros = ctrlDom.leerDocumentoYimprimir(doc, tiendaLibros);
                    System.out.println("Tienda libros tiene " + tiendaLibros.size());
                    break;

                case 3:
                    tiendaLibros.autoImprimirme();
                    break;

                case 4:
                    tiendaLibros2 = new BookStore();
                    tiendaLibros2 = ctrlDom.librosDeEjemplo(tiendaLibros2);
                    System.out.println("Bookstore creado con exito y libros añadidos a este");
                    tiendaLibros2.autoImprimirme();
                    break;

                case 5:
                    try {
                        doc2 = ctrlDom.escribir(tiendaLibros2);
                        System.out.println("Arbol de nodos escrito con exito");
                    } catch (NullPointerException e) {
                        System.out.println("Debes crear el bookstore con los libros de ejemplo");
                    }
                    break;
                case 6:
                    System.out.println("Introduce nombre del fichero xml");
                    teclado.nextLine();
                    ruta2 = teclado.nextLine();
                case 7:
                    ctrlDom.guardarXML(doc2, ruta2);
                    break;

            }
        }

    }

    public static void mostrarMenu() {
        System.out.println("1.-Introducir ruta xml y instanciar documento");
        System.out.println("2.-Leer el documento y crear objeto Bookstore");
        System.out.println("3.-Imprimir el bookstore y todos sus datos");
        System.out.println("****************************************************************************");
        System.out.println("4.-Dar de alta la tienda de libros y un par de libros de ejemplo");
        System.out.println("5.-Escribir el objeto bookstore en un nuevo documento vacio");
        System.out.println("6.-Seleccionar nombre fichero destino");
        System.out.println("7.-Guardar el documento");
    }

}
