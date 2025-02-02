
public class Uczelnia implements Iterator {
	 final Student[] _array;
	 final int _first;
	 final int _last;
	 int _current = -1;
	 
	 public Uczelnia(Student[] array){
		 _array = array;
		 _first = 0;
		 _last = array.length -1;
	 }
	 
	 public void first(){
		 _current = _first;
	 }
	 
	 public void last(){
		 _current = _last;
	 }
	 
	 public void next(){
		 ++_current;
	 }
	 
	 public void previous(){
		 --_current;
	 }
	 
	 public boolean isDone(){
		 return _current < _first || _current>_last;
	 }
	 
	 public Student current(){
		 return _array[_current];
	 }
}
