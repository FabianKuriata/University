package zad26;

	public class Node{
        private int value;
        private Node nextHor;
        private Node nextVer;
        private int hor;
        private int ver;
       
        public Node(int val, int hor, int ver){
           
            this.value=val;
            this.nextHor=null;
            this.nextVer=null;
            this.hor=hor;
            this.ver=ver;
        }
       
        public String getValueString(){
            String str = String.format(" %3d ", value);
            return str;
        }
       
        public String toString(){
            String str = String.format(" [%-3d|%-3d|%3d] ", value, hor, ver);
            return str;
        }
       
        public int getHor() {
            return hor;
        }
 
        public void setHor(int hor) {
            this.hor = hor;
        }
 
        public int getVer() {
            return ver;
        }
 
        public void setVer(int ver) {
            this.ver = ver;
        }
 
        public int getValue(){
            return value;
        }
       
        public void setValue(int o){
            value = o;
        }
       
        public boolean hasNextHorizontal(){
            boolean has=true;
            
            if(nextHor==null){
                has=false;
            }
            return has;
        }
       
        public boolean hasNextVertical(){
            boolean has=true;
            if(nextVer==null){
                has=false;
            }
            return has;
        }
       
        public Node getNextHor(){
            return nextHor;
        }
 
        public Node getNextVer(){
            return nextVer;
        }
       
        public void setNextHor(Node n){
            nextHor = n;
        }
       
        public void setNextVer(Node n){
            nextVer = n;
        }
 
    }
 
 

