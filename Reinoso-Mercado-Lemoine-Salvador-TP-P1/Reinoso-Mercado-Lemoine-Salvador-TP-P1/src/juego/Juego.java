package juego;

import java.awt.Image;
import javax.swing.ImageIcon;
import entorno.Entorno;
import entorno.InterfaceJuego;
import java.awt.Image;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Image fondo;
    private Image imagenIslaNivel1;  // Imagen para islas de nivel 1
    private Image imagenIslaNivel2;  // Imagen para islas de nivel 2
    private Image imagenIslaNivel3;  // Imagen para islas de nivel 3
    private Image imagenIslaNivel4;  // Imagen para islas de nivel 4
    private Image imagenIslaNivel5;  // Imagen para islas de nivel 5
    
    private Isla[] islas;
    private Casa casa;
    private Pep pep;
    private Tortuga tortuga;
    private Gnomo gnomo;

    Juego() {
        this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);

        // Cargar las imágenes de fondo y de las islas
        this.fondo = new ImageIcon("imagenes/Fondo.jpg").getImage();
        this.imagenIslaNivel1 = new ImageIcon("imagenes/IslaNivel1.png").getImage(); // Cargar imagen para nivel 1
        this.imagenIslaNivel2 = new ImageIcon("imagenes/IslaNivel2.png").getImage(); // Cargar imagen para nivel 1
        this.imagenIslaNivel3 = new ImageIcon("imagenes/IslaNivel34.png").getImage(); // Cargar imagen para nivel 1
        this.imagenIslaNivel4 = new ImageIcon("imagenes/IslaNivel34.png").getImage(); // Cargar imagen para nivel 1
        this.imagenIslaNivel5 = new ImageIcon("imagenes/IslaNivel5.png").getImage(); // Cargar imagen para nivel 1

        // Inicializar las islas del nivel 1 con la imagen
        this.islas = new Isla[20];
        int anchoEntorno = 800;
        int altoEntorno = 600;

        // Nivel 1 (más bajo) - 5 islas
        double alturaNivel1 = altoEntorno - 100;
        for (int i = 0; i < 5; i++) {
            double x = (anchoEntorno / 6) * (i + 1);
            int anchoIsla = 90;
            islas[i] = new Isla(x, alturaNivel1, anchoIsla, 20, imagenIslaNivel1); // Pasa la imagen al constructor
        }

        // Nivel 2 - 4 islas
        double alturaNivel2 = altoEntorno - 200;
        for (int i = 0; i < 4; i++) {
            double x = (anchoEntorno / 5) * (i + 1);
            int anchoIsla = 120;
            islas[i + 5] = new Isla(x, alturaNivel2, anchoIsla, 20, imagenIslaNivel2);  // Usa null para islas sin imagen
        }

        // Nivel 3 - 3 islas
        double alturaNivel3 = altoEntorno - 300;
        for (int i = 0; i < 3; i++) {
            double x = (anchoEntorno / 4) * (i + 1);
            int anchoIsla = 150;
            islas[i + 9] = new Isla(x, alturaNivel3, anchoIsla, 20, imagenIslaNivel3);  // Usa null si no hay imagen específica
        }

        // Nivel 4 - 2 islas
        double alturaNivel4 = altoEntorno - 400;
        for (int i = 0; i < 2; i++) {
            double x = (anchoEntorno / 3) * (i + 1);
            int anchoIsla = 150;
            islas[i + 12] = new Isla(x, alturaNivel4, anchoIsla, 20, imagenIslaNivel4);
        }

        // Nivel 5 (superior) - 1 isla
        double alturaNivel5 = altoEntorno - 500;
        islas[14] = new Isla(anchoEntorno / 2, alturaNivel5, 100, 20, imagenIslaNivel5);  // Centrar y sin imagen

        // Crear la casa en la isla más alta
        Isla islaAlta = islas[14];
        this.casa = new Casa(islaAlta.getX(), islaAlta.getY() - 30, 40, 30);

        this.pep = new Pep(anchoEntorno / 2, alturaNivel1 - 30);
        this.tortuga = new Tortuga(20, 0);
        this.gnomo = new Gnomo(400, 55);
        
        this.entorno.iniciar();
    }

    public void tick() {
        entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

        // Dibujar las islas
        for (Isla isla : islas) {
            if (isla != null) {
                isla.dibujarse(entorno);
            }
        }

        // Dibujar otros elementos
        casa.dibujarse(entorno);
        pep.moverse(entorno, islas);
        pep.dibujarse(entorno);
        tortuga.dibujarse(entorno);
        tortuga.moverse(islas);
        gnomo.dibujarse(entorno);
        gnomo.moverse(islas);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
