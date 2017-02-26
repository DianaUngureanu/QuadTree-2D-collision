/**
 * Clasa GeometricObject constrieste dreptunghiurile incadratoare pentru fiecare 
 * obiect insantiat si retine ce tip de figura era initial, ID-ul ei si 
 * coordonatele. De asemenea, adauga elementul respectiv la un vector de obiecte
 * instantiate.
 * @author Diana
 */
public class GeometricObject extends Object {
	int ID; 
	int tipFigura;
	double xMin;
	double xMax;
	double yMin;
	double yMax;
	static double[][] vectorObiecteAdaugate = new double[100000][6];
	static int obiecteInstantiate = 0;
	
	/**
	* Constructor fara parametrii
    */
	public GeometricObject() {
	}
	
	/**
 	* Construieste un nou obiect de tipul GeometricObject
    * @param ID ID-ul figurii instantiate
    * @param tipFigura Tip-ul figurii instantiate
    * @param xMin Valoarea minima pentru x
    * @param yMin Valoarea minima pentru y
    * @param xMax Valoarea maxima pentru x
    * @param yMax Valoarea maxima pentru y
    */
	public GeometricObject(int ID, int tipFigura, double xMin, double yMin, double xMax, double yMax) {
		this.ID = ID;
		this.tipFigura = tipFigura;
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
		vectorObiecteAdaugate[obiecteInstantiate][0] = ID;
		vectorObiecteAdaugate[obiecteInstantiate][1] = tipFigura;
		vectorObiecteAdaugate[obiecteInstantiate][2] = xMin;
		vectorObiecteAdaugate[obiecteInstantiate][3] = yMin;
		vectorObiecteAdaugate[obiecteInstantiate][4] = xMax;
		vectorObiecteAdaugate[obiecteInstantiate][5] = yMax;
		obiecteInstantiate++;
	}
	
	/**
    * Getter pentru xMin
    * @return xMin
    */
	public double getxMin() {
		return this.xMin;
	}
	
	/**
    * Getter pentru xMax
    * @return xMax
    */
	public double getxMax() {
		return this.xMax;
	}
	
	/**
    * Getter pentru yMin
    * @return yMin
    */
	public double getyMin() {
		return this.yMin;
	}
	
	/**
    * Getter pentru yMax
    * @return yMax
    */
	public double getyMax() {
		return this.yMax;
	}
	
	/**
    * Getter pentru tipul de figura
    * @return Tipul figurii(1-dreptunghi, 2-triunghi, 3-cerc, 4-romb)
    */
	public double getTipFigura() {
		return this.tipFigura;
	}
	
	/**
	* Metoda ce calculeaza aria unui trunghi si primeste ca parametrii
    * coordonatele colturilor triunghiului.
    * @param x1 Coordonata x de sus
    * @param y1 Coordonata y de sus
    * @param x2 Coordonata x din stanga jos
    * @param y2 Coordonata y din stanga jos
    * @param x3 Coordonata x din dreapta jos
    * @param y3 Coordonata y din dreapta jos
    * @return Aria triunghiului
    */
	public double getAreaTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		   return Math.abs( (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) ) / 2.0);
	}	
}