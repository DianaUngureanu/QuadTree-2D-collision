import java.io.*;
import java.util.Scanner;

/**
 * Clasa in care fac citirea din fisier si rezolvarea task-urilor aferente.
 * Citesc din fisier pana la ultima linie si in functie de ce operatie gasesc,
 * inserez, sterg sau verific coliziunile , urmate de scrierea in fisierul
 * de output.
 * @author Ungureanu Diana-Gabriela 322CB
 */
public class Test {
	public static void main(String args[]) throws IOException, NullPointerException {
		
		GeometricObject nr1 = new GeometricObject();
		
		int[] vectorPtIntersectie1 = new int[100000];
		int[] vectorPtIntersectie2 = new int[100000];
		
		File input = new File("quadtree.in");
		String outputFile = "quadtree.out";

        try (FileWriter fw = new FileWriter(outputFile,true);
             Scanner file = new Scanner(input)) {
                
            /*citesc coordonatele pentru quadtree*/
            double xMin = file.nextDouble();
            double yMin = file.nextDouble();
            double xMax = file.nextDouble();
            double yMax = file.nextDouble();
                
            Quadtree arbore = new Quadtree(xMin, yMin, xMax, yMax); 
                
            while(file.hasNext()) {
                int operatie = file.nextInt();
                if(operatie == 11) 
                {
                    int tipElement = file.nextInt();
                    int id = file.nextInt();

                    if(tipElement == 1) { 
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();

                        Rectangle dreptunghi = new Rectangle(id, x1, y1, x2, y2);

                        arbore.insert(arbore.radacina, dreptunghi);
                    }
                    else if(tipElement == 2) {
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();
                        double x3 = file.nextDouble();
                        double y3 = file.nextDouble();

                        Triangle triunghi = new Triangle(id, x1, y1, x2, y2, x3, y3);

                        arbore.insert(arbore.radacina,triunghi);
                    }
                    else if(tipElement == 3) {
                        double R = file.nextDouble();
                        double x = file.nextDouble();
                        double y = file.nextDouble();

                        Circle cerc = new Circle(id, R, x, y);

                        arbore.insert(arbore.radacina, cerc);
                    }
                    else if(tipElement == 4) {
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();
                        double x3 = file.nextDouble();
                        double y3 = file.nextDouble();
                        double x4 = file.nextDouble();
                        double y4 = file.nextDouble();

                        Diamond romb = new Diamond(id, x1, y1, x2, y2, x3, y3, x4, y4);

                        arbore.insert(arbore.radacina,romb);
                    }
                }
                else if(operatie == 22) {
                    int idDeSters = file.nextInt();
                    arbore.delete(arbore.radacina, idDeSters, nr1);
                }
                else if(operatie == 33) {
                    double x = file.nextDouble();
                    double y = file.nextDouble();

                    arbore.intersectionWithPoint(arbore.radacina, x, y, vectorPtIntersectie1);

                    if(Quadtree.nrDeElemDinVector1 == 0) {
                        fw.write("NIL");
                        fw.write(System.getProperty("line.separator"));
                    }
                    else {
                        quickSort(vectorPtIntersectie1, 0, Quadtree.nrDeElemDinVector1-1);

                        for(int i = 0; i < Quadtree.nrDeElemDinVector1; i++) {
                            if( i+1 != Quadtree.nrDeElemDinVector1) {
                                fw.write(Integer.toString(vectorPtIntersectie1[i]));
                                fw.write(" ");
                            }
                            else
                                fw.write(Integer.toString(vectorPtIntersectie1[i]));   
                        }

                        fw.write(System.getProperty("line.separator"));
                    }

                    Quadtree.nrDeElemDinVector1 = 0;

                }
                else if(operatie == 44) {
                    int tipElement = file.nextInt();

                    if(tipElement == 1) {
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();

                        Rectangle dreptunghi = new Rectangle(0, x1, y1, x2, y2);

                        arbore.intersectionWithFigure(arbore.radacina, dreptunghi , vectorPtIntersectie2);

                        if(Quadtree.nrDeElemDinVector2 == 0) {
                            fw.write("NIL");
                            fw.write(System.getProperty("line.separator"));
                        }
                        else {
                            quickSort(vectorPtIntersectie2, 0, Quadtree.nrDeElemDinVector2-1);

                            for(int i = 0; i < Quadtree.nrDeElemDinVector2; i++) {
                                if( i+1 != Quadtree.nrDeElemDinVector2)
                                        fw.write(Integer.toString(vectorPtIntersectie2[i]) + " ");
                                    else
                                        fw.write(Integer.toString(vectorPtIntersectie2[i]));
                            }

                            fw.write(System.getProperty("line.separator"));
                        }

                        Quadtree.nrDeElemDinVector2 = 0;
                    }
                    else if(tipElement == 2) {
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();
                        double x3 = file.nextDouble();
                        double y3 = file.nextDouble();

                        Triangle triunghi = new Triangle(0, x1, y1, x2, y2, x3, y3);

                        arbore.intersectionWithFigure(arbore.radacina, triunghi , vectorPtIntersectie2);

                        if(Quadtree.nrDeElemDinVector2 == 0) {
                            fw.write("NIL");
                            fw.write(System.getProperty("line.separator"));
                        }
                        else {
                            quickSort(vectorPtIntersectie2, 0, Quadtree.nrDeElemDinVector2-1);

                            for(int i = 0; i < Quadtree.nrDeElemDinVector2; i++) {
                                if( i+1 != Quadtree.nrDeElemDinVector2)
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]) + " ");
                                else
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]));
                            }

                                fw.write(System.getProperty("line.separator"));
                        }

                        Quadtree.nrDeElemDinVector2 = 0;
                            
                    }
                    else if(tipElement == 3) {
                        double R = file.nextDouble();
                        double x = file.nextDouble();
                        double y = file.nextDouble();

                        Circle cerc = new Circle(0, R, x, y);

                        arbore.intersectionWithFigure(arbore.radacina, cerc , vectorPtIntersectie2);

                        if(Quadtree.nrDeElemDinVector2 == 0) {
                            fw.write("NIL");
                            fw.write(System.getProperty("line.separator"));
                        }
                        else {
                            quickSort(vectorPtIntersectie2, 0, Quadtree.nrDeElemDinVector2-1);

                            for(int i = 0; i < Quadtree.nrDeElemDinVector2; i++) {
                                if( i+1 != Quadtree.nrDeElemDinVector2)
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]) + " ");
                                else
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]));
                            }

                            fw.write(System.getProperty("line.separator"));
                        }

                        Quadtree.nrDeElemDinVector2 = 0;
                    }
                    else if(tipElement == 4) {
                        double x1 = file.nextDouble();
                        double y1 = file.nextDouble();
                        double x2 = file.nextDouble();
                        double y2 = file.nextDouble();
                        double x3 = file.nextDouble();
                        double y3 = file.nextDouble();
                        double x4 = file.nextDouble();
                        double y4 = file.nextDouble();

                        Diamond romb = new Diamond(0, x1, y1, x2, y2, x3, y3, x4, y4);

                        arbore.intersectionWithFigure(arbore.radacina, romb , vectorPtIntersectie2);

                        if(Quadtree.nrDeElemDinVector2 == 0) {
                            fw.write("NIL");
                            fw.write(System.getProperty("line.separator"));
                        }
                        else {
                            quickSort(vectorPtIntersectie2, 0, Quadtree.nrDeElemDinVector2-1);

                            for(int i = 0; i < Quadtree.nrDeElemDinVector2; i++) {
                                if( i+1 != Quadtree.nrDeElemDinVector2) 
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]) + " ");
                                else
                                    fw.write(Integer.toString(vectorPtIntersectie2[i]));
                            }

                            fw.write(System.getProperty("line.separator"));
                        }

                        Quadtree.nrDeElemDinVector2 = 0;
                    }
                }
            }

            file.close();
            fw.close();
        }
	}
	
    /**
    * Metoda care primeste un vector si indexul de inceput si cel de sfarsit
    * si il ordoneaza crescator(functie folosita pentru scrierea in ordine
    * a ID-urilor in fisierul de output.
    * @param arr Vectorul ce trebuie sortat
    * @param low Prima pozitie din vector
    * @param high Ultima pozitie din vector
    */
	public static void quickSort(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;

		int middle = low + (high - low) / 2;
		int pivot = arr[middle];
		int i = low, j = high;

		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
}