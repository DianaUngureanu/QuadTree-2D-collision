import java.util.ArrayList;

/**
 * Clasa Quadtree contine campul radacina, aceasta reprezentand radacina 
 * arborelui si 2 int-uri care ma vor ajuta la intersectie(cu punct, respectiv
 * alta figura).
 * @author Diana
 */
public class Quadtree {
	
	public Node radacina; 
	public static int nrDeElemDinVector1 = 0;
	public static int nrDeElemDinVector2 = 0;
	
    /**
    * Construieste un nou arbore, setandu-i radacina, avand coordonatele
    * transmise prin parametrii
    * @param xMin Valoarea minima a lui x a "cadranului" radacinii
    * @param yMin Valoarea minima a lui y a "cadranului" radacinii
    * @param xMax Valoarea maxima a lui x a "cadranului" radacinii
    * @param yMax Valoarea maxima a lui y a "cadranului" radacinii
    */	
	public Quadtree(double xMin, double yMin, double xMax, double yMax) {
		this.radacina = new Node(xMin, yMin, xMax, yMax, null); 
	}
	
	/**
	* Getter pentru radacina
    * @return Radacina
    */
	public Node getRadacina() { 
        return this.radacina;
	}
	
	/**
    * Metoda insert insereaza in nodul primit drept parametru, obiectul
    * pe care il primeste tot ca parametru. Metoda testeaza tipul nodului
    * folosindu-se de metoda getType. Daca nodul este gol, insereaza in 
    * el folosind metoda setObjectForNode. Daca nodul este frunza, verific 
    * atat timp cat inca mai exista elemente in nod, daca figura pe care 
    * vreau sa o inserez se intersecteaza cu alta deja existenta. Daca gasesc
    * un element cu care se intersecteaza, adaug obiectul in lista de obiecte
    * existente in nodul respectiv. Daca nu se intersecteaza cu nimic, fac split
    * si inserez elementul.
    * Daca nodul este de tip pointer(are copii), iau coordonatele nodului
    * in care ma aflu si vad in ce cadran ar trebui inserat obiectul meu.
    * Pentru fiecare cadran, mai intai verific daca nu exista deja obiectul
    * pe care vreau sa il inserez. In caz ca nu exista, atunci fac inserarea.
    * @param parinte Nodul in care se doreste a se face inserarea
    * @param obiectDeInserat Obiectul de inserat
    * @throws NullPointerException In caz ca e null
    */
	public void insert(Node parinte, Object obiectDeInserat) throws NullPointerException { 
		switch(parinte.getType()) { 
			case EMPTY: 
				this.setObjectForNode(parinte, obiectDeInserat);
				break;
			case LEAF:
				int i = 0; 
				boolean check = false; 
				
				while(i < parinte.getNrOfObjects()) { 
					Object figuraExistenta = parinte.getObjAtIndex(i);
					check = checkIfTheyIntersect(figuraExistenta, obiectDeInserat);

					if(check == true) { 
						this.setObjectForNode(parinte, obiectDeInserat);
						break;
					}
					i++;	
				}

				if(check == false) {
					this.split(parinte);
					this.insert(parinte, obiectDeInserat);
				}

			break;
			case POINTER:
				double xmin = ((GeometricObject)obiectDeInserat).getxMin(); 
				double ymin = ((GeometricObject)obiectDeInserat).getyMin();
				double xmax = ((GeometricObject)obiectDeInserat).getxMax();
				double ymax = ((GeometricObject)obiectDeInserat).getyMax();
				
				double mijx = (parinte.getxMax()+parinte.getxMin())/2; 
				double mijy = (parinte.getyMax() + parinte.getyMin())/2; 
				int c1 = 0;
				int c2 = 0; 
				int c3 = 0;
				int c4 = 0;
				
				if(xmin < mijx) { 		
					if(ymin < mijy || ymax < mijy) { 
						if(c3 != 1) {
							int ok = 1;
							if(parinte.getC3().getNrOfObjects() != 0) 
								for(i = 0; i < parinte.getC3().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC3().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC3(), obiectDeInserat);
								c3++;
							}
						}
					}
					
					if(ymin > mijy || ymax > mijy) {
						if(c2 != 1) {
							int ok = 1;
							if(parinte.getC2().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC2().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC2().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC2(), obiectDeInserat);
								c2++;
							}
						}
					}	
				}
				else { 
					if(ymin < mijy || ymax < mijy) { 
						if(c4 != 1) {
							int ok = 1;
							if(parinte.getC4().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC4().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC4().getObjAtIndex(i)); 
							if(ok == 1) {
								this.insert(parinte.getC4(), obiectDeInserat);
								c4++;
							}
						}
					}
					if(ymin > mijy || ymax > mijy) {
						if(c1 != 1) {
							int ok = 1;
							if(parinte.getC1().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC1().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC1().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC1(), obiectDeInserat);
								c1++;
							}
						}
					}
				}
				
				if(xmax < mijx) { 
					if(ymin < mijy || ymin < mijy) { 
						if(c3 != 1) {
							int ok = 1;
							if(parinte.getC3().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC3().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC3().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC3(), obiectDeInserat);
								c3++;
							}
						}
					}
					
					if(ymin > mijy || ymax > mijy) {
						if(c2 != 1) {
							int ok = 1;
							if(parinte.getC2().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC2().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC2().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC2(), obiectDeInserat);
								c2++;
							}
						}
					}
				}	
				else {
					if(ymin < mijy || ymax < mijy) { 
						if(c4 != 1) {
							int ok = 1;
							if(parinte.getC4().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC4().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC4().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC4(), obiectDeInserat);
								c4++;
							}
						}
					}
					if(ymin > mijy || ymax > mijy) {
						if(c1 != 1) {
							int ok = 1;
							if(parinte.getC1().getNrOfObjects() != 0)
								for(i = 0; i < parinte.getC1().getNrOfObjects(); i++)
									ok = compareTwoObjs(obiectDeInserat, parinte.getC1().getObjAtIndex(i));
							if(ok == 1) {
								this.insert(parinte.getC1(), obiectDeInserat);
								c1++;
							}
						}
					}
				}
				break;		
			}
		}

	/**
    * Metoda care compara daca 2 dreptunghiuri sunt echivalente
    * @param nr1 Dreptunghiul nr 1
    * @param nr2 Dreptunghiul nr 2
    * @return 1 daca sunt diferite si 0 altfel
    * @throws NullPointerException 
    */
	public int compareTwoObjs(Object nr1, Object nr2) throws NullPointerException {
		
		if(nr1 == null || nr2 == null)
			return 1;

		double x1 = ((GeometricObject)nr1).getxMin();
		double x2 = ((GeometricObject)nr1).getxMax();
		double y1 = ((GeometricObject)nr1).getyMin();
		double y2 = ((GeometricObject)nr1).getyMax();
	
		double x11 = ((GeometricObject)nr2).getxMin();
		double x22 = ((GeometricObject)nr2).getxMax();
		double y11 = ((GeometricObject)nr2).getyMin();
		double y22 = ((GeometricObject)nr2).getyMax();
		
		if(x1 == x11 && x2 == x22 && y1 == y11 && y2 == y22) 
			return 0; 

		return 1;
	}
	
	/**
    * Metoda care desparte un nod trimis drept parametru in 4.
    * Metoda salveaza vechile obiecte de erau in nod, sterge tot ce era 
    * inainte in nod, sparge nodul in 4 si atribuie valorile corespunzatoare
    * celor 4 cadrane si apoi insereaza vechile obiecte la loc in arbore.
    * @param nodDeSpart Nodul de spart
    * @throws NullPointerException 
    */
	public void split(Node nodDeSpart) throws NullPointerException {
		int i;
		ArrayList<Object> oldObjects = new ArrayList<Object>();
		int vechiulNrDeObiecte = nodDeSpart.getNrOfObjects(); 

		for(i = 0; i < nodDeSpart.getNrOfObjects(); i++) 
			oldObjects.add(nodDeSpart.getObjAtIndex(i));
		
		i = 0;

		while( i < nodDeSpart.getNrOfObjects()) {
			if(nodDeSpart.getObjAtIndex(0) != null) {
				nodDeSpart.getObjects().remove(0);
				i++;
			}
			else
				break;
		}
		
		nodDeSpart.setTipNod(NodeType.POINTER); 
		
		double xMin = nodDeSpart.getxMin(); 
        double yMin = nodDeSpart.getyMin(); 
        double xMax = nodDeSpart.getxMax();
        double yMax = nodDeSpart.getyMax();
        
        nodDeSpart.setC1(new Node((xMax+xMin)/2, (yMax+yMin)/2, xMax, yMax, nodDeSpart));
        nodDeSpart.setC2(new Node(xMin, (yMax+yMin)/2, (xMax+xMin)/2, yMax, nodDeSpart));
        nodDeSpart.setC3(new Node(xMin, yMin, (xMax+xMin)/2, (yMax+yMin)/2, nodDeSpart));
        nodDeSpart.setC4(new Node((xMax+xMin)/2, yMin, xMax, (yMax+yMin)/2, nodDeSpart));
        
        for(i = 0; i < vechiulNrDeObiecte; i++) 
            this.insert(nodDeSpart, oldObjects.get(i)); 
        
        nodDeSpart.setNrofObjects(0); 
		
	}
	
	/**
    * Adauga un obiect in nodul trimis drept parametru.
    * @param node Nodul in care se doreste sa se faca inserarea
    * @param obiectDeInserat Obiectul de inserat
    * @throws NullPointerException 
    */
	public void setObjectForNode(Node node, Object obiectDeInserat) throws NullPointerException { 
		if(node.getType() == NodeType.POINTER) {
			System.out.println("N-ar trebuie sa fie POINTER, ai gresit ceva undeva");
		}

		node.setTipNod(NodeType.LEAF); 
		node.setObject(obiectDeInserat);
	}
	
	/**
    * Functie care verifica in ce figuri din arbore se afla punctul trimis 
    * drept parametru. In cazul in care nodul este de tip pointer, ma duc 
    * recursiv pe fiecare cadran pana ajung la un nod de tip leaf.
    * Cand ajung la un nod de tip leaf, iau toate elementele din nodul respectiv
    * si pentru fiecare, verific daca punctul meu se afla in interiorul 
    * figurii respective. Pentru fiecare figura am conditii de intersectie
    * diferite. Cand gasesc o figura, ii retin ID-ul in vector-ul trimis 
    * ca parametru, dar inainte de asta, verific ca ID-ul sa nu se afle deja
    * in vector.
    * @param parinte Nodul in care se face cautarea
    * @param x Coordonata x a punctului de cautat
    * @param y Coordonata y a punctului de cautat
    * @param aiciAFostGasit Vector in care voi retine ID-urile la care a
    * fost gasit punctul
    * @throws NullPointerException 
    */
	public void intersectionWithPoint(Node parinte, double x, double y, int[] aiciAFostGasit) throws NullPointerException { 
		switch(parinte.getType()) {
			case LEAF: 
				double nrElementeInFrunza = parinte.getNrOfObjects(); 
				int j;

				for(int i = 0; i < nrElementeInFrunza; i++) { 
					Object obiectDinNod = parinte.getObjAtIndex(i);
					if( ((GeometricObject)obiectDinNod).getTipFigura() == 1) {
						double x1 = ((Rectangle)obiectDinNod).x1; 
						double x2 = ((Rectangle)obiectDinNod).x2;
						double y1 = ((Rectangle)obiectDinNod).y1;
						double y2 = ((Rectangle)obiectDinNod).y2;

						if(x >= x1 && x <= x2 && y >= y1 && y <= y2) {
							int ok = 1; 
							for(j = 0; j < nrDeElemDinVector1; j++) {
								if(((GeometricObject)obiectDinNod).ID == aiciAFostGasit[j]) {
									ok = 0; 
								}
								if(ok == 0)
									break;
							}
							if(ok == 1 ) { 
								aiciAFostGasit[nrDeElemDinVector1] = ((GeometricObject)obiectDinNod).ID;
								nrDeElemDinVector1++;
							}
						}
					}
					else if( ((GeometricObject)obiectDinNod).getTipFigura() == 2) {
						double x1 = ((Triangle)obiectDinNod).x1;
						double x2 = ((Triangle)obiectDinNod).x2;
						double x3 = ((Triangle)obiectDinNod).x3;
						double y1 = ((Triangle)obiectDinNod).y1;
						double y2 = ((Triangle)obiectDinNod).y2;
						double y3 = ((Triangle)obiectDinNod).y3;
						double areaOfTriangle = ((Triangle)obiectDinNod).getArea(x1, y1, x2, y2, x3, y3);
						double tri2 = ((Triangle)obiectDinNod).getArea(x, y, x2, y2, x3, y3); 
						double tri3 = ((Triangle)obiectDinNod).getArea(x1, y1, x, y, x3, y3); 
						double tri4 = ((Triangle)obiectDinNod).getArea(x1, y1, x2, y2, x, y);

						if(areaOfTriangle == tri2 + tri3 + tri4) {
							int ok = 1; 
							for(j = 0; j < nrDeElemDinVector1; j++) {
								if(((GeometricObject)obiectDinNod).ID == aiciAFostGasit[j]) {
									ok = 0; 
								}
								if(ok == 0) 
									break;
							}
							if(ok == 1 ) { 
								aiciAFostGasit[nrDeElemDinVector1] = ((GeometricObject)obiectDinNod).ID;
								nrDeElemDinVector1++;
							}
						}
					}
					else if( ((GeometricObject)obiectDinNod).getTipFigura() == 3) {
						double xCerc = ((Circle)obiectDinNod).x;
						double yCerc = ((Circle)obiectDinNod).y;
						double rCerc = ((Circle)obiectDinNod).R;

						if( Math.pow((x - xCerc),2) + Math.pow((y - yCerc),2) <= Math.pow(rCerc, 2) ) {
							int ok = 1;
							for(j = 0; j < nrDeElemDinVector1; j++) {
								if(((GeometricObject)obiectDinNod).ID == aiciAFostGasit[j]) {
									ok = 0; 
								}
								if(ok == 0)
									break;
							}
							if(ok == 1 ) {
								aiciAFostGasit[nrDeElemDinVector1] = ((GeometricObject)obiectDinNod).ID;
								nrDeElemDinVector1++;
							}
						}
					}
					else if( ((GeometricObject)obiectDinNod).getTipFigura() == 4) { 
						boolean rezultat1 = false;
						boolean rezultat2 = false;
						double x1 = ((Diamond)obiectDinNod).x1;
						double x2 = ((Diamond)obiectDinNod).x2;
						double x3 = ((Diamond)obiectDinNod).x3;
						double x4 = ((Diamond)obiectDinNod).x4;
						double y1 = ((Diamond)obiectDinNod).y1;
						double y2 = ((Diamond)obiectDinNod).y2;
						double y3 = ((Diamond)obiectDinNod).y3;
						double y4 = ((Diamond)obiectDinNod).y4;
						
						double arieTriunghiSus = ((GeometricObject)obiectDinNod).getAreaTriangle(x1, y1, x2, y2, x4, y4);
						double tri2 = ((GeometricObject)obiectDinNod).getAreaTriangle(x, y, x2, y2, x4, y4); 
						double tri3 = ((GeometricObject)obiectDinNod).getAreaTriangle(x1, y1, x, y, x4, y4);  
						double tri4 = ((GeometricObject)obiectDinNod).getAreaTriangle(x1, y1, x2, y2, x, y); 
						
						if(arieTriunghiSus == tri2 + tri3 + tri4)
							rezultat1 = true; 
						
						double arieTriunghiJos = ((GeometricObject)obiectDinNod).getAreaTriangle(x3, y3, x4, y4, x2, y2); 
						double tri5 = ((GeometricObject)obiectDinNod).getAreaTriangle(x, y, x4, y4, x2, y2); 
						double tri6 = ((GeometricObject)obiectDinNod).getAreaTriangle(x3, y3, x, y, x2, y2); 
						double tri7 = ((GeometricObject)obiectDinNod).getAreaTriangle(x3, y3, x4, y4, x, y); 
						
						if(arieTriunghiJos == tri5 + tri6 + tri7)
							rezultat2 = true;
						
						if(rezultat1 == true || rezultat2 == true) {
							int ok = 1; 
							for(j = 0; j < nrDeElemDinVector1; j++) {
								if(((GeometricObject)obiectDinNod).ID == aiciAFostGasit[j]) {
									ok = 0; 
								}
								if(ok == 0)
									break;
							}
							if(ok == 1 ) {
								aiciAFostGasit[nrDeElemDinVector1] = ((GeometricObject)obiectDinNod).ID;
								nrDeElemDinVector1++;
							}
						}							
					}
				}
				break;
			case POINTER: 
				try {
					this.intersectionWithPoint(parinte.getC1(), x, y,aiciAFostGasit);
				}
				catch(NullPointerException ex) {	
				}
				try {
					this.intersectionWithPoint(parinte.getC2(), x, y, aiciAFostGasit);
				}
				catch(NullPointerException ex) {
				}
				try {
					this.intersectionWithPoint(parinte.getC3(), x, y, aiciAFostGasit);
				}
				catch(NullPointerException ex) {
				}
				try {
					this.intersectionWithPoint(parinte.getC4(), x, y, aiciAFostGasit);
				}
				catch(NullPointerException ex) {	
				}
				break;
		}
	}
	
	/**Functie care verifica ce figuri din arbore se intersecteaza cu 
    * figura trimisa drept parametru. In cazul in care nodul este de tip 
	* pointer, ma duc recursiv pe fiecare cadran pana ajung la un nod de tip leaf.
    * Cand ajung la un nod de tip leaf, iau toate elementele din nodul respectiv
    * si pentru fiecare, verific daca figura mea se intersecteaza cu 
    * figura respectiva. Cand gasesc o figura, ii retin ID-ul in vector-ul trimis 
    * ca parametru, dar inainte de asta, verific ca ID-ul sa nu se afle deja
    * in vector.
    * @param parinte Nodul in care se face cautarea
    * @param obiectDeVerificat Obiectul pe care trebuie sa-l verific
    * @param cuAsteaSeIntersecteaza Vector in care voi retine ID-urile la care a
    * fost gasit punctul
    * @throws NullPointerException 
    */
	public void intersectionWithFigure(Node parinte, Object obiectDeVerificat, int[] cuAsteaSeIntersecteaza) throws NullPointerException {
		switch(parinte.getType()) {
			case LEAF:
				double nrElementeInFrunza = parinte.getNrOfObjects(); 
				int j;
				for(int i = 0; i < nrElementeInFrunza; i++) { 
					Object obiectDinNod = parinte.getObjAtIndex(i);
					double x1 = ((GeometricObject)obiectDinNod).getxMin();
					double x2 = ((GeometricObject)obiectDinNod).getxMax(); 
					double y1 = ((GeometricObject)obiectDinNod).getyMin();
					double y2 = ((GeometricObject)obiectDinNod).getyMax();
					
					double xMinVerifica = ((GeometricObject)obiectDeVerificat).getxMin();
					double xMaxVerifica = ((GeometricObject)obiectDeVerificat).getxMax();
					double yMinVerifica = ((GeometricObject)obiectDeVerificat).getyMin();
					double yMaxVerifica = ((GeometricObject)obiectDeVerificat).getyMax();
					
					boolean check = true; 

					if(xMinVerifica > x2 || xMaxVerifica < x1 || yMinVerifica > y2 || yMaxVerifica < y1)
						check = false;
						
					if(check == true) { 
						int ok = 1;
						for(j = 0; j < nrDeElemDinVector2; j++) {
							if(((GeometricObject)obiectDinNod).ID == cuAsteaSeIntersecteaza[j]) {
								ok = 0; 
							}
							if(ok == 0)
								break;
						}
						if(ok == 1 ) { 
							cuAsteaSeIntersecteaza[nrDeElemDinVector2] = ((GeometricObject)obiectDinNod).ID;
							nrDeElemDinVector2++;
						}
					}
				}
				break;
				
			case POINTER:
				try {
					this.intersectionWithFigure(parinte.getC1(), obiectDeVerificat, cuAsteaSeIntersecteaza);
				}
				catch(NullPointerException ex) {	
				}
				try {
					this.intersectionWithFigure(parinte.getC2(), obiectDeVerificat, cuAsteaSeIntersecteaza);
				}
				catch(NullPointerException ex) {
				}
				try {
					this.intersectionWithFigure(parinte.getC3(), obiectDeVerificat, cuAsteaSeIntersecteaza);
				}
				catch(NullPointerException ex) {
				}
				try {
					this.intersectionWithFigure(parinte.getC4(), obiectDeVerificat, cuAsteaSeIntersecteaza);
				}
				catch(NullPointerException ex) {	
				}
				break;
		}
	}
	
	/**
    * Metoda care verifica daca 2 dreptunghiuri se intersecteaza sau nu.
    * Am facut pentru cazul in care nu se interesecteaza, ca sa nu mai 
    * consider cele multe cazuri de intersectie.
    * @param nr1 Dreptunghiul nr1
    * @param nr2 Dreptunghiul nr2
    * @return False daca nu se intersecteaza, altfel true
    * @throws NullPointerException 
	*/
	public boolean checkIfTheyIntersect(Object nr1, Object nr2) throws NullPointerException{ 
		double x1 = ((GeometricObject)nr1).getxMin();
		double y1 = ((GeometricObject)nr1).getyMin();
		double x2 = ((GeometricObject)nr1).getxMax();
		double y2 = ((GeometricObject)nr1).getyMax();
		
		double x3 = ((GeometricObject)nr2).getxMin();
		double y3 = ((GeometricObject)nr2).getyMin();
		double x4 = ((GeometricObject)nr2).getxMax();
		double y4 = ((GeometricObject)nr2).getyMax();
		
		if(x1 > x4 || x2 < x3 || y1 > y4 || y2 < y3) 
			return false;
		return true; 
	}
	
	/**
    * Metoda care primeste ca parametru un nod din care trebuie sters un 
    * obiect, id-ul obiectului de sters si un obiect din care o sa imi iau
    * niste caracteristici pentru obiect.
    * Daca nodul este de tip pointer, ma duc in functie de cadran-ul unde 
    * ar fi intrat figura si ma duc recursiv pana la o frunza.
    * Daca nodul este de tip frunza, caut obiectul cu ID-ul cautat, il sterg
    * din lista de obiecte din nod, scad nr de obiecte din nod si opresc 
    * cautarea(ID-ul apare o singura data in vector).
    * @param parinte Nodul din care se face stergerea
    * @param idDeSters ID-ul figurii care trebuie stearsa
    * @param obiectDinCareIauVectorul Obiectul din care iau niste
    * caracteristici
    * @throws NullPointerException 
    */
	public void delete(Node parinte, int idDeSters, GeometricObject obiectDinCareIauVectorul) throws NullPointerException {
		int tipObiect;
		double x11 = 0;
		double y11 = 0;
		double x22 = 0;
		double y22 = 0;
		
		for(int i = 0; i < GeometricObject.obiecteInstantiate; i++) { 
			if(GeometricObject.vectorObiecteAdaugate[i][0] == idDeSters) {
				tipObiect = (int)GeometricObject.vectorObiecteAdaugate[i][1];
				x11 = GeometricObject.vectorObiecteAdaugate[i][2];
				y11 = GeometricObject.vectorObiecteAdaugate[i][3];
				x22 = GeometricObject.vectorObiecteAdaugate[i][4];
				y22 = GeometricObject.vectorObiecteAdaugate[i][5];
				break;
			}
		}
		
		switch(parinte.getType()) {
		case LEAF:
			int nrElementeInFrunza = parinte.getNrOfObjects();
			int i;
			int ok = 0; 

			for(i = 0; i < nrElementeInFrunza; i++) { 
				Object obiectDinNod = parinte.getObjAtIndex(i); 
				if( ((GeometricObject)obiectDinNod).ID == idDeSters  ) {
					parinte.getObjects().remove(i);
					parinte.setNrofObjects(nrElementeInFrunza-1);
					ok = 1; 
				}
				if(ok == 1)
					break;
			}
			break;
		case POINTER:
			double mijx = (parinte.getxMax()+parinte.getxMin())/2; 
			double mijy = (parinte.getyMax() + parinte.getyMin())/2;
			int c1 = 0;
			int c2 = 0; 
			int c3 = 0;
			int c4 = 0;
			if(x11 < mijx) {		
				if(y11 < mijy || y22 < mijy) { 
					if(c3 != 1) {
						try {
							this.delete(parinte.getC3(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c3++;
					}
				}
				
				if(y11 > mijy || y22 > mijy) {
					if(c2 != 1) {
						try {
							this.delete(parinte.getC2(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c2++;
					}
				}
			}
			else {
				if(y11 < mijy || y22 < mijy) { 
					if(c4 != 1) {		
						try {
							this.delete(parinte.getC4(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c4++;
					}
				}
				if(y11 > mijy || y22 > mijy) {
					if(c1 != 1) {
						try {
							this.delete(parinte.getC1(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c1++;
					}
				}
			}
			
			if(x22 < mijx) {
				if(y11 < mijy || y11 < mijy) { 
					if(c3 != 1) {	
						try {
							this.delete(parinte.getC3(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c3++;
					}
				}
				
				if(y11 > mijy || y22 > mijy) {
					if(c2 != 1) {
						try {
							this.delete(parinte.getC2(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c2++;
					}
				}
			}
			
			else {
				if(y11 < mijy || y22 < mijy) { 
					if(c4 != 1) {
						try {
							this.delete(parinte.getC4(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c4++;
					}
				}
				
				if(y11 > mijy || y22 > mijy) {
					if(c1 != 1) {
						try {
							this.delete(parinte.getC1(), idDeSters, obiectDinCareIauVectorul);
						}
						catch(NullPointerException ex) {	
						}
						c1++;
					}
				}
			}
			break;
		}
	}
}