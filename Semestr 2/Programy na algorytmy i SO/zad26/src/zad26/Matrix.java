package zad26;

import java.util.Random;

public class Matrix {
	private String name;
	private Node head;
	private int width;
	private int height;
 
    public Matrix(int w, int h, String n){
        this.name=n;
        this.head = new Node(0,0,0);
        this.width=w;
        this.height=h;
        Node current = head;
        for(int i=0; i<=this.width;i++){
            current.setNextHor(new Node(0,i+1,0));
            current=current.getNextHor();
        }
        current = head;
        for(int i=0;i<=this.height;i++){
            current.setNextVer(new Node (0,0,i+1));
            current=current.getNextVer();
        }
    }
   
    public void fillRand(int a){
        for(int i=0; i<a; i++){
            Random generator = new Random();
            int x = generator.nextInt(9)+1;
            int y = generator.nextInt(this.width)+1;
            int z = generator.nextInt(this.height)+1;
            if(generator.nextBoolean()){
            this.insertNode(x, y, z);
            }
            else{
                this.insertNode(-x, y, z);
                
            }
        }
    }
   
    public void printHead(){
        System.out.println(this.head.toString());
    }
   
    public void printNodes(){
        System.out.println();
        System.out.println(name + " Nodes");
        int j=0;
        while(j<=this.height){
            String line = "";
            Node current = this.head;
            while(current.getVer()<j){
                current=current.getNextVer();
            }
            for(int i=0; i<=this.width;i++){
                if(current.getHor()==i){
                    
                    line+=current.toString();
                    if(current.hasNextHorizontal()){
                        current=current.getNextHor();
                    }
                }
                else{
                    String space = " ------------- ";
                    line+=space;
                }
            }
            System.out.println(line);
        j++;
        }
        System.out.println();
    }
   
    public void printValues(){
        System.out.println();
        System.out.println(name + " Values");
        int j=1;
        while(j<=this.height){
            String line = "";
            Node current = this.head;
            while(current.getVer()<j){
                current=current.getNextVer();
            }
       
            for(int i=0; i<=this.width;i++){
                if(current.getHor()==i ){
                    if(i>0){
                       
                    line+=current.getValueString();
                    }
                    if(current.hasNextHorizontal()){
                        current=current.getNextHor();
                    }
                }
                else{
                    String space = "   0 ";
                    line+=space;
                }
            }
            System.out.println(line);
        j++;
        }
        System.out.println();
    }
   
    public void insertNode(int val, int hor, int ver){
        Node requested = new Node(val,hor,ver);
        Node current = head;
        while(current.getVer()<ver){
            current=current.getNextVer();
        }
        while(current.getHor()<hor && current.hasNextHorizontal() && current.getNextHor().getHor()<hor){
            current=current.getNextHor();
        }
        if(current.hasNextHorizontal() && current.getNextHor().getHor()>hor){
            requested.setNextHor(current.getNextHor());
        }
        else if(current.hasNextHorizontal() && current.getNextHor().getHor()==hor && current.getNextHor().hasNextHorizontal() ){
            requested.setNextHor(current.getNextHor().getNextHor());
        }
        current.setNextHor(requested);
       
        current = head;
        while(current.getHor()<hor){
            current=current.getNextHor();
        }
        while(current.getVer()<ver && current.hasNextVertical() && current.getNextVer().getVer()<ver){
            current=current.getNextVer();
        }
        if(current.hasNextVertical() && current.getNextVer().getVer()>ver){
            requested.setNextVer(current.getNextVer());
            
        }
        else if(current.hasNextVertical() && current.getNextVer().getVer()>ver && current.getNextVer().hasNextVertical()){
            requested.setNextVer(current.getNextVer().getNextVer());
        }
        current.setNextVer(requested);
       
    }
   
    public Matrix addMatrix(Matrix a, Matrix b){
        System.out.println("\n" + "Adding Matrixes");
        Matrix C = new Matrix(a.width, a.height, "C");
        a.addMatrix(C);
        b.addMatrix(C);
        System.out.println("Input Matrix 1:");
        a.printValues();
        System.out.println("Input Matrix 2:");
        b.printValues();
        System.out.println("Result Matrix:");
        C.printValues();
        return C;
    }
   
    public void addMatrix(Matrix b){
        int j=1;
        while(j<=this.height){
            Node current = this.head;
            while(current.getVer()<j){
                current=current.getNextVer();
            }
            for(int i=0; i<=this.width;i++){
                if(current.getHor()==i ){
                    if(i>0){
                    b.addNode(current.getValue(), current.getHor(), current.getVer()); 
                    }
                    if(current.hasNextHorizontal()){
                        current=current.getNextHor();
                    }
                }
            }
        j++;
        }
    }
   
    public void addNode(int val, int hor, int ver){
        Node requested = new Node(val,hor,ver);
        Node current = head;
        while(current.getVer()<ver){
            current=current.getNextVer();
        }
        while(current.getHor()<hor && current.hasNextHorizontal() && current.getNextHor().getHor()<hor){
            current=current.getNextHor();
        }
        if(current.hasNextHorizontal() && current.getNextHor().getHor()>hor){
            requested.setNextHor(current.getNextHor());
            current.setNextHor(requested);
        }
        else if(current.hasNextHorizontal() && current.getNextHor().getHor()==hor){
            current=current.getNextHor();
            current.setValue(current.getValue()+requested.getValue());
        }
        else{
            current.setNextHor(requested);
        }
       
        current = head;
        while(current.getHor()<hor){
            current=current.getNextHor();
        }
        while(current.getVer()<ver && current.hasNextVertical() && current.getNextVer().getVer()<ver){
            current=current.getNextVer();
        }
        if(current.hasNextVertical() && current.getNextVer().getVer()>ver){
            requested.setNextVer(current.getNextVer());
            current.setNextVer(requested);
        }
        else if(current.hasNextVertical() && current.getNextVer().getVer()>ver){
            current=current.getNextVer();
            
            current.setValue(current.getValue()+requested.getValue());
        }
        else{
            current.setNextVer(requested);
        }
       
    }
}
 
