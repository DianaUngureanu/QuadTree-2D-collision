/**
 * Clasa Rectangle construieste un nou obiect si apeleaza
 * constructorul clasei GeometricObject(acesta instantiind dreptunghiul
 * incadrator pentru dreptunghi).
 * @author Diana
 */
public class Rectangle extends GeometricObject {
	double x1;
	double x2;
	double y1;
	double y2;
	
	/**
    * Construieste un nou obiect de tip dreptunghi.
    * @param ID care va fi mereu 4, ca sa stiu ca este cerc
	* @param x1 xMin
    * @param y1 yMin
    * @param x2 xMax
    * @param y2 yMax
    */
	public Rectangle(int ID, double x1, double y1, double x2, double y2) { 
		super(ID, 1, x1, y1, x2, y2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
}
