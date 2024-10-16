package juego;
import java.awt.Color;

import entorno.Entorno;

public class Isla {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	public Isla(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 110;
		this.alto = 15;
	}
	public void dibujarse(Entorno entorno) 
	{
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.yellow);
		
	}
	
	public double getX() 
	{
		return this.x;
	}
	
	
	public double getY() 
	{
		return this.y;
	}


}