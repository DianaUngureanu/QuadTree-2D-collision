/**
 * Clasa Circle construieste un nou obiect si apeleaza
 * constructorul clasei GeometricObject(acesta instantiind dreptunghiul
 * incadrator pentru cerc).
 * @author Diana
 */
public class Circle extends GeometricObject {
	double R;
	double x;
	double y;

    /**
    * Construieste un nou obiect de tip cerc.
    * @param ID care va fi mereu 3, ca sa stiu ca este cerc
    * @param R raza cercului
    * @param x coordonata x a centrului cercului
    * @param y coordonata y a centrului cercului
    */
	
	public Circle (int ID, double R, double x, double y) {
        super(ID, 3, x - R, y - R, x + R, y + R);
        this.R = R;
        this.x = x;
        this.y = y;
    }
}