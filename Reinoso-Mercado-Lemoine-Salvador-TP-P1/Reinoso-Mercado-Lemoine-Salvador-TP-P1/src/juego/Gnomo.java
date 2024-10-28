package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;

public class Gnomo {

    private double x, y;
    private double velocidadX = 1.2; // Velocidad horizontal del gnomo
    private double velocidadY = 4; // Velocidad de caída
    private boolean cayendo = true; // Comienza cayendo
    private Random random;
    private Isla islaActual; // Isla en la que el gnomo está actualmente
    private int direccion; // 1 para derecha, -1 para izquierda

    public Gnomo(double x, double y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        this.direccion = random.nextBoolean() ? 1 : -1; // Inicializa la dirección aleatoria
    }

    public void moverse(Isla[] islas) {
        if (cayendo) {
            // Caída del gnomo
            y += velocidadY; // Velocidad de caída

            // Verifica si el gnomo ha colisionado con alguna isla
            for (Isla isla : islas) {
                if (isla != null && isla.colisionaConGnomo(x, y, 20, 20)) { // Verifica si la isla no es null
                    cayendo = false; // El gnomo ya no cae
                    velocidadY = 0; // Detener caída
                    y = isla.getY() - isla.getAlto() / 2 - 10; // Ajusta la posición Y para que quede sobre la isla
                    islaActual = isla; // Guardar referencia a la isla actual
                    break;
                }
            }
        } else {
            // Movimiento horizontal en la isla
            x += velocidadX * direccion;

            // Verificar si el gnomo se sale del borde de la isla actual
            if (islaActual != null) {
                double limiteIzquierdo = islaActual.getX() - islaActual.getAncho() / 1.70;
                double limiteDerecho = islaActual.getX() + islaActual.getAncho() / 170;

                if (x < limiteIzquierdo || x > limiteDerecho) {
                    // Si el gnomo se sale del borde, hacer que caiga
                    cayendo = true;
                    velocidadY = 4; // Reiniciar velocidad de caída
                    islaActual = null; // Eliminar referencia de la isla actual
                }
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void dibujarse(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, 20, 20, 0, Color.RED); // Dibuja al gnomo
    }
}
