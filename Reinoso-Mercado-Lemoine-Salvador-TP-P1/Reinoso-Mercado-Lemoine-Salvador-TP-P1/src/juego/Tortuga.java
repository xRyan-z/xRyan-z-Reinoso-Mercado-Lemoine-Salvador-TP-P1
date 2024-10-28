package juego;

import java.awt.Color;
import entorno.Entorno;

public class Tortuga {
    private double x, y;
    private double velocidadX = 1;
    private boolean cayendo = true;

    public Tortuga(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moverse(Isla[] islas) {
        if (cayendo) {
            x = 250;
            // Simula la caída de la tortuga
            y += 3; // Velocidad de la caída de la tortuga

            // Verifica si la tortuga ha colisionado con alguna isla
            for (Isla isla : islas) {
                if (isla != null && isla.colisionaConTortuga(x, y, 30, 30)) { 
                    cayendo = false; // La tortuga ya no cae 
                    y = isla.getY() - isla.getAlto() / 2 - 15; // Ajusta la posición Y para que quede sobre la isla
                    break;
                }
            }
        } else {
            // Movimiento horizontal en la isla
            x += velocidadX;

            // Verificar colisiones con los extremos de la isla
            for (Isla isla : islas) {
                if (isla != null && isla.colisionaConTortuga(x, y, 30, 30)) {
                    if (x + 15 > isla.getX() + isla.getAncho() / 2 || x - 15 < isla.getX() - isla.getAncho() / 2) {
                        // Si llega al borde, cambiar la dirección
                        velocidadX = -velocidadX;
                    }
                }
            }
        }
    }

    public void dibujarse(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, 30, 30, 0, Color.green); // Dibuja la tortuga 
    }
}
