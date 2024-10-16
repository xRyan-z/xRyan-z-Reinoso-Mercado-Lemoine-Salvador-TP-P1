package juego;


import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Image fondo;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		 // Cargar la imagen de fondo
        this.fondo = new ImageIcon("imagenes/Fondo.jpg").getImage();  // Asegúrate de que la ruta sea correcta
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Dibujar la imagen de fondo
        entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		int niveles = 5;
        int anchoEntorno = 800;
        int altoEntorno = 600;

        // Altura disponible por nivel
        int alturaNivel = altoEntorno / niveles;

        for (int nivel = 1; nivel <= niveles; nivel++) {
            // Calcular el número de islas en este nivel
            int numIslas = nivel;

            // Espacio horizontal disponible entre las islas
            double espacioHorizontal = (anchoEntorno - (numIslas * 80)) / (numIslas + 1); 

            for (int i = 0; i < numIslas; i++) {
                // Calcular la posición X e Y de cada isla
                double x = espacioHorizontal +i * (100 + espacioHorizontal);
                double y = alturaNivel * nivel - (alturaNivel / 2);

                // Crear una nueva isla y dibujarla
                 Isla isla= new Isla(x, y);
                 isla.dibujarse(entorno);
            }
        }
		// Procesamiento de un instante de tiempo
		// ...
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
