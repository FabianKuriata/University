package zad26;

 
public class Main {
 
    public static void main(String[] args) {
        Matrix matA = new Matrix(4,5,"A");
        matA.fillRand(3);
        matA.printNodes();
        matA.printValues();
        Matrix matB = new Matrix(4,5,"B");
        matB.fillRand(5);
        matB.printNodes();
        matB.printValues();
        Matrix matC = matB.addMatrix(matA, matB);
        matC.printNodes();
        matC.printValues();
    }
 
}
 
 
 
 
 
 
 
 
 

