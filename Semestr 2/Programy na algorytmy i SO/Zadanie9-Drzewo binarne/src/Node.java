import java.util.Stack;

class Node {

    char name;
    Node leftChild;
    Node rightChild;

    Node(char name) {
        
        this.name = name;
    }
    
    
    
    public String toString() {
        return (rightChild == null && leftChild == null) ? Character.toString(name) : "(" + leftChild.toString()+ name + rightChild.toString() + ")";
    }

}
