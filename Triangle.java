/**
 * Clasa Triangle construieste un nou obiect si apeleaza
 * constructorul clasei GeometricObject(acesta instantiind dreptunghiul
 * incadrator pentru triunghi).
 * @author Diana
 */
public class Triangle extends GeometricObject {
	double x1;
	double x2;
	double x3;
	double y1;
	double y2;
	double y3;
	
	/**
    * Construieste un nou obiect de tip triunghi.
    * @param ID care va fi mereu 2, ca sa stiu ca este cerc
    * @param x1 coordonata x de sus
    * @param y1 coordonata y de sus
    * @param x2 coordonata x din stanga
    * @param y2 coordonata y din stanga
    * @param x3 coordonata x din dreapta
    * @param y3 coordonata y din dreapta
    */
	public Triangle(int ID, double x1, double y1, double x2, double y2, double x3, double y3) {
		super(ID,2,Math.min(x1, Math.min(x2, x3)), y2, Math.max(x1, Math.max(x2, x3)), y1);
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
	}
	
	/**
    * Metoda getArea primeste ca parametrii coordonatele unui triunghi
    * si calculeaza aria acelui dreptunghi
    * @param x1 coordonata x de sus
    * @param y1 coordonata y de sus
    * @param x2 coordonata x din stanga
    * @param y2 coordonata y din stanga
    * @param x3 coordonata x din dreapta
    * @param y3 coordonata y din dreapta
    * @return Aria triunghiului
    */
	public double getArea(double x1, double y1, double x2, double y2, double x3, double y3) {
		   return Math.abs( (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) ) / 2.0);
	}
	
}
