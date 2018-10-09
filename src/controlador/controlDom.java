/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.Bookstore;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.BookStore;
import modelo.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sportak
 */
public class controlDom {

    public controlDom() {
    }

    public Document recuperar(File f) throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;
        doc = this.instanciarDocumento(f, doc);
        return doc;
    }

    public Document instanciarDocumento(File f, Document doc) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(f);
        System.out.println("Instanciado corectamente");
        return doc;
    }

    public Document instanciarDocumento(Document doc) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.newDocument();
        return doc;
    }

    public BookStore leerDocumentoYimprimir(Document doc, BookStore bk) {
        Element raiz = doc.getDocumentElement();
        NodeList nl = raiz.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element analizado = (Element) nl.item(i); // este será el libro
                Libro libro = new Libro();
                libro.setCategoria(getAtributoEtiqueta(analizado, "category"));
                System.out.println(analizado.getNodeName());
                libro = procesarHijos(analizado, libro);
                bk.add(libro);
            }
        }

        return bk;
    }

    public Libro procesarHijos(Element elem, Libro libro) {
        NodeList nl = elem.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Constantes cons = new Constantes();
                Element analizado = (Element) nl.item(i);
                System.out.println("\t" + nl.item(i).getNodeName());
                if (analizado.getTagName().equalsIgnoreCase(cons.nombreAutor)) {
                    libro.getListaautor().add(analizado.getTextContent());
                }
                if (analizado.getTagName().equalsIgnoreCase(cons.nombreAño)) {
                    libro.setYear(analizado.getTextContent());
                }
                if (analizado.getTagName().equalsIgnoreCase(cons.nombrePrecio)) {
                    libro.setPrecio(Double.parseDouble(analizado.getTextContent().trim()));
                }
                if (analizado.getTagName().equalsIgnoreCase(cons.nombreTitulo)) {
                    libro.setTitulo(analizado.getTextContent());
                }
            }
        }
        System.out.println("Devolviendo libro con ... " + libro.toString());
        return libro;
    }

    public String getAtributoEtiqueta(Element elem, String nomAtributo) {
        return elem.getAttribute(nomAtributo);
    }

    public Document escribir(BookStore tiendalibros) throws ParserConfigurationException {
        Document docVacio = null;
        docVacio = instanciarDocumento(docVacio);
        // create the root element node
        Element element = docVacio.createElement("root");
        docVacio.appendChild(element);
        //elemento hijo de la raiz, el primer hijo el que engloba todos lso libros
        Element bookstore = docVacio.createElement("bookstore");
        element.appendChild(bookstore);

        for (int i = 0; i < tiendalibros.size(); i++) {
            //por cada libro en la tienda de libros añado un libro hijo a la etiqueta bookstore
            Element book = docVacio.createElement("book");
            bookstore.appendChild(book);
            añadirElementoADocumento(book, (Libro) tiendalibros.get(i), docVacio);

        }
        return docVacio;

    }

    public void añadirElementoADocumento(Element elementobook, Libro libro, Document doc) {
        elementobook.setAttribute("category", libro.getCategoria());

        Element titulo = doc.createElement("title");
        titulo.appendChild(doc.createTextNode(libro.getTitulo()));
        elementobook.appendChild(titulo);

        for (int i = 0; i < libro.getListaautor().size(); i++) {
            Element autor = doc.createElement("author");
            autor.appendChild(doc.createTextNode(libro.getListaautor().get(i)));
            elementobook.appendChild(autor);
        }

        Element año = doc.createElement("year");
        año.appendChild(doc.createTextNode(libro.getYear()));
        elementobook.appendChild(año);

        Element precio = doc.createElement("price");
        precio.appendChild(doc.createTextNode(libro.getPrecio().toString()));
        elementobook.appendChild(precio);

    }

    public void guardarXML(Document doc2,String str) throws TransformerException {
        TransformerFactory transfactory = TransformerFactory.newInstance();
        Transformer transformer = transfactory.newTransformer();
        DOMSource source = new DOMSource(doc2);
        StreamResult result = new StreamResult(new File(str));
        transformer.transform(source, result);
    }

    public BookStore librosDeEjemplo(BookStore tiendaLibros2) {
        Libro libroEjemplar1 = new Libro("Misterio", "Thunderhorse", "1996", 29.99);
        libroEjemplar1.getListaautor().add("El caballero del trueno");
        Libro libroEjemplar2 = new Libro("Terror", "El mal no germina en el infierno", "2016", 9.99);
        libroEjemplar2.getListaautor().add("Sandra Cabanes");
        Libro libroEjemplar3 = new Libro("Comedia", "Dethklok", "2996", 59.99);
        libroEjemplar3.getListaautor().add("El trueno del caballo");
        tiendaLibros2.add(libroEjemplar1);
        tiendaLibros2.add(libroEjemplar2);
        tiendaLibros2.add(libroEjemplar3);
        return tiendaLibros2;
    }

}
