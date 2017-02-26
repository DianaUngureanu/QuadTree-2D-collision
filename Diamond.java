/**
 * Clasa Diamond construieste un nou obiect si apeleaza
 * constructorul clasei GeometricObject(acesta instantiind dreptunghiul
 * incadrator pentru romb).
 * @author Diana
 */
public class Diamond extends GeometricObject {
	double x1;
	double x2;
	double x3;
	double x4;
	double y1;
	double y2;
	double y3;
	double y4;

	/**
    * Construieste un nou obiect de tip romb.
    * @param ID care va fi mereu 4, ca sa stiu ca este cerc
    * @param x1 coordonata x de jos
    * @param y1 coordonata y de jos
    * @param x2 coordonata x din stanga
    * @param y2 coordonata y din stanga
    * @param x3 coordonata x de sus
    * @param y3 coordonata y de sus
    * @param x4 coordonata x din dreapta
    * @param y4 coordonata y din dreapta
    */
	public Diamond(int ID, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		super(ID, 4, Math.min(Math.min(x1, x2), Math.min(x3, x4))
		, Math.min(Math.min(y1, y2), Math.min(y3, y4))
		, Math.max(Math.max(x1, x2), Math.max(x3, x4)) 
		, Math.max(Math.max(y1, y2), Math.max(y3, y4)));
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.y4 = y4;
	}
}