package juego;

import java.awt.Image;
import javax.swing.ImageIcon;
import entorno.Entorno;

public class Pep {
    private double x, y;
    private double velocidadX; // Mantiene la velocidad
    private double velocidadY;
    private boolean saltando;
    private static final double GRAVEDAD = 0.2;
    private static final double VELOCIDAD_SALTO = -90;

    // Imágenes de Pep
    private Image imagenQuietoDerecha;
    private Image imagenQuietoIzquierda;
    private Image imagenIzquierda;
    private Image imagenDerecha;
    private Image imagenSaltoIzquierda;
    private Image imagenSaltoDerecha;

    // Para recordar la última dirección en la que se movió
    private boolean mirandoDerecha;
    private boolean mirandoIzquierda;

    public Pep(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidadX = 7;  // Velocidad constante al moverse
        this.velocidadY = 1;   // Inicializar la velocidad vertical
        this.saltando = false;

        // Cargar imágenes
        this.imagenQuietoDerecha = new ImageIcon("imagenes/pepnormalder.png").getImage(); // Nueva imagen para quieto mirando a la derecha
        this.imagenQuietoIzquierda = new ImageIcon("imagenes/pepnormal.png").getImage(); // Nueva imagen para quieto mirando a la izquierda
        this.imagenIzquierda = new ImageIcon("imagenes/pepizq.png").getImage();
        this.imagenDerecha = new ImageIcon("imagenes/pepder.png").getImage();
        this.imagenSaltoIzquierda = new ImageIcon("imagenes/pepsaltandoder.png").getImage();
        this.imagenSaltoDerecha = new ImageIcon("imagenes/pepsaltandoizq.png").getImage();

        // Inicializar dirección mirando
        this.mirandoDerecha = true;
        this.mirandoIzquierda = false;
    }

    public void moverse(Entorno entorno, Isla[] islas) {
        // Resetear velocidadX para controlar el movimiento
        this.velocidadX = 0; // Por defecto, no se mueve

        // Actualizar velocidadX según la dirección de movimiento
        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            this.velocidadX = -7; // Mover a la izquierda
            this.mirandoIzquierda = true; // Recordar dirección
            this.mirandoDerecha = false;
        }
        if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            this.velocidadX = 7; // Mover a la derecha
            this.mirandoDerecha = true; // Recordar dirección
            this.mirandoIzquierda = false;
        }

        // Actualizar la posición de Pep según la velocidad
        this.x += this.velocidadX;

        // Verificar si está en alguna isla
        boolean enIsla = false;
        for (Isla isla : islas) {
            if (isla != null && this.estaSobreIsla(isla)) {
                enIsla = true;
                break;
            }
        }

        // Salto
        if (entorno.sePresiono(entorno.TECLA_ARRIBA) && enIsla) {
            this.saltando = true;
            this.velocidadY = VELOCIDAD_SALTO;
        }

        // Aplicar gravedad
        this.y += this.velocidadY;
        if (!enIsla || this.saltando) {
            this.velocidadY += GRAVEDAD;
        } else {
            this.velocidadY = 0; // Resetear velocidadY si está en una isla
        }

        if (enIsla && this.saltando) {
            this.saltando = false; // Cambiar el estado de salto
            this.velocidadY = 0; // Resetear velocidadY al caer en la isla
        }
    }

    public void dibujarse(Entorno entorno) {
        Image imagenActual;

        // Seleccionar imagen según estado
        if (saltando) {
            if (this.velocidadX > 0) {
                imagenActual = imagenSaltoDerecha;
            } else {
                imagenActual = imagenSaltoIzquierda;
            }
        } else if (this.velocidadX > 0) {
            imagenActual = imagenDerecha;
        } else if (this.velocidadX < 0) {
            imagenActual = imagenIzquierda;
        } else {
            // Aquí se elige la imagen de quieto según la última dirección
            if (mirandoDerecha) {
                imagenActual = imagenQuietoDerecha;
            } else if (mirandoIzquierda) {
                imagenActual = imagenQuietoIzquierda;
            } else {
                imagenActual = imagenQuietoDerecha; // Valor por defecto, si no se ha movido
            }
        }

        // Dibujar la imagen en vez de un rectángulo
        entorno.dibujarImagen(imagenActual, this.x, this.y, 0);
    }

    private boolean estaSobreIsla(Isla isla) {
        return this.x >= isla.getX() - isla.ancho / 2 && this.x <= isla.getX() + isla.ancho / 2 &&
               this.y + 25 >= isla.getY() - isla.alto / 2 && this.y + 25 <= isla.getY() + isla.alto / 2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
