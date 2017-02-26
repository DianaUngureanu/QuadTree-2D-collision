import java.util.ArrayList;

/**
 * Arborele va contine elemente de tip Node, care sunt caracterizate si ele
 * la randul lor de alte variabile
 * @author Diana
 */
public class Node { 
    private double xMin, xMax, yMin, yMax; 
    private Node parinte, C1, C2, C3, C4; 
    private ArrayList<Object> obiecte = new ArrayList<Object>();
    private NodeType tipNod = NodeType.EMPTY;
    private int numberOfObjects;
    
    /**
     * Construieste un nou obiect de tip Nod.
     * @param xMin Limita inferioara pentru x
     * @param yMin Limita inferioara pentru y
     * @param xMax Limita maxima pentru x
     * @param yMax Limita maxima pentru y
     * @param parinte Nodul parinte(in caz ca are)
     */
    public Node(double xMin, double yMin, double xMax, double yMax, Node parinte) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        this.parinte = parinte;
        this.C1 = null;
        this.C2 = null;
        this.C3 = null;
        this.C4 = null; 
        this.numberOfObjects = 0;
    }
    
    /**
     * Getter xMin
     * @return xMin
     */
    public double getxMin() {
    	return this.xMin;
    }
    
    /**
     * Getter xMax
     * @return xMax
     */
    public double getxMax() {
    	return this.xMax;
    }
    
    /**
     * Getter yMin
     * @return yMin
     */
    public double getyMin() {
    	return this.yMin;
    }
    
    /**
     * Getter yMax
     * @return yMax
     */
    public double getyMax() {
    	return this.yMax;
    }
    
    /**
     * Setter xMin
     * @param xMin Noul xMin pe care vreau sa il setez
     */
    public void setxMin(double xMin) {
    	this.xMin = xMin;
    }
    
     /**
     * Setter xMax
     * @param xMax Noul xMax pe care vreau sa il setez
     */
    public void setxMax(double xMax) {
    	this.xMax = xMax;
    }
    
    /**
     * Setter yMin
     * @param yMin Noul yMin pe care vreau sa il setez
     */
    public void setyMin(double yMin) {
    	this.yMin = yMin;
    }
    
    /**
     * Setter yMax
     * @param yMax Noul yMax pe care vreau sa il setez
     */
    public void setyMax(double yMax) {
    	this.yMax = yMax;
    }
    
    /**
     * Getter nodParinte
     * @return Nodul parinte
     */
    public Node getParinte() {
    	return this.parinte;
    }
    
    /**
     * Setter pentru nodParinte
     * @param parinte Seteaza nodulParinte la cel specificat
     */
    public void setParinte(Node parinte) {
    	this.parinte = parinte;
    }
    
    /**
     * Getter pentru lista de obiecte din nod
     * @return Lista de obiecte din nod
     */
    public ArrayList getObjects() { 
    	return this.obiecte;
    }
    
    /**
     * Setter pentru un obiect in lista obiectelor deja existente in nod
     * @param obiect Obiectul de inserat
     */
    public void setObject(Object obiect) {
    	obiecte.add(obiect);
    	if(obiect != null)
    		this.numberOfObjects++;
    }
    
    /**
     * Getter pentru un obiect aflat la index-ul trimis ca parametru
     * @param index Index-ul elementului care doreste a fi returnat
     * @return Obiectul la index-ul primit ca parametru
     */
    public Object getObjAtIndex(int index) { 
    	return this.obiecte.get(index);
    }
    
    /**
     * Getter pentru tipul nodului
     * @return Tipul nodului
     */
    public NodeType getType() { 
    	return this.tipNod;
    }
    
    /**
     * Setter pentru tipul nodului
     * @param tipNod Tipul la care vrem sa setam nodul
     */
    public void setTipNod(NodeType tipNod) { 
    	this.tipNod = tipNod;
    }
    
    /**
     * Setter pentru numarul de obiecte din nod
     * @param x Noul numar de obiecte
     */
    public void setNrofObjects(int x) { 
    	this.numberOfObjects = x;
    }
    
    /**
     * Getter pentru Cadranul 1
     * @return Cadranul 1
     */
    public Node getC1() {
        return C1;
    }
    
    /**
     * Setter pentru Cadranul 1
     * @param C1 Noul cadran 1
     */
    public void setC1(Node C1) {
        this.C1 = C1;
    }
    
    /**
     * Getter pentru Cadranul 2
     * @return Cadranul 2
     */
    public Node getC2() {
        return C2;
    }

    /**
     * Setter pentru Cadranul 2
     * @param C2 Noul cadran 2
     */
    public void setC2(Node C2) {
        this.C2 = C2;
    }
    
    /**
     * Getter pentru Cadranul 3
     * @return Cadranul 3
     */
    public Node getC3() {
        return C3;
    }

    /**
     * Setter pentru Cadranul 3
     * @param C3 Noul cadran 3
     */
    public void setC3(Node C3) {
        this.C3 = C3;
    }
    
    /**
     * Getter pentru Cadranul 4
     * @return Cadranul 4
     */
    public Node getC4() {
        return C4;
    }

    /**
     * Setter pentru Cadranul 4
     * @param C4 Noul cadran 4
     */
    public void setC4(Node C4) {
        this.C4 = C4;
    }
    
    /**
     * Getter pentru numarul de obiecte din lista
     * @return Numarul de obiecte din lista
     */
    public int getNrOfObjects() { 
    	return numberOfObjects;
    }
}