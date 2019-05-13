import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RBTree {
	private final int RED = 0;
	private final int BLACK = 1;
	public static final Node nil = new Node(new Word("", 0));
	private static final int V = 0;
	public Node root;
	List<UniqueWord> uniqueWords = new ArrayList<UniqueWord>();

	RBTree() {
		root = nil;
	}

	protected void insert(Node node) {
		Node temp = root;
		if (root == nil) {
			root = node;
			node.color = BLACK;
			node.parent = nil;
		} else {
			node.color = RED;
			while (true) {
				if (node.key.compareTo(temp.key) < 0) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.key.compareTo(temp.key) >= 0) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			fixTree(node);
		}
	}

	private void fixTree(Node node) {
		while (node.parent.color == RED) {
			Node uncle = nil;
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right;

				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.right) {
					// Double rotation needed
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				// if the "else if" code hasn't executed, this
				// is a case where we only need a single rotation
				rotateRight(node.parent.parent);
			} else {
				uncle = node.parent.parent.left;
				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					// Double rotation needed
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				// if the "else if" code hasn't executed, this
				// is a case where we only need a single rotation
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK;
	}

	void rotateLeft(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != nil) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else {// Need to rotate root
			Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	void rotateRight(Node node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {// Need to rotate root
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	public void inOrder(Node focus) {
		if (focus != nil) {

			inOrder(focus.left);
			isUnique(focus.key);
			// System.out.println(focus + " ");
			inOrder(focus.right);

		}
	}

	public void wszerz(Node w) {
		System.out.println(" PRZEGL¥DANIE WSZERZ:");
		Queue<Node> q = new LinkedList<Node>();
		q.add(w);
		while (!q.isEmpty()) {
			Node node = (Node) q.remove();
			System.out.println(node);
			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);

		}
		System.out.println();
		System.out.println();
	}


	Queue<Node> queue = new LinkedList<Node>() ;
	public void breadth(Node root) {
	    if (root == null)
	        return;
	    queue.clear();
	    queue.add(root);
	    while(!queue.isEmpty()){
	        Node node = queue.remove();
	        System.out.print(node.key + " ");
	        if(node.left != null) queue.add(node.left);
	        if(node.right != null) queue.add(node.right);
	    }

	}
	
	private boolean isUnique(Word key) {
		for(int i = 0; i < uniqueWords.size(); i++){
			if(key.word.equals(uniqueWords.get(i).uniqueWord)){
				uniqueWords.get(i).isNotUnique(key.nrRow);
				return false;
			}
		}
		uniqueWords.add(new UniqueWord(key.word));
		uniqueWords.get(uniqueWords.size()-1).isInRow.add(key.nrRow);
		
		return true;
	}

	public void printWords(Node root) {
		inOrder(root);
		
		for (int w = 0; w < uniqueWords.size(); w++) {
			System.out.print(uniqueWords.get(w) + "- ");
			for (int r = 0; r < uniqueWords.get(w).isInRow.size(); r++) {
				System.out.print(uniqueWords.get(w).isInRow.get(r));
				if (r + 1 != uniqueWords.get(w).isInRow.size()) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}

}
