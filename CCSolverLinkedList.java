public class CCSolverLinkedList{

	private Node head;


	public CCSolverLinkedList(LetterDT inputData){
		head = new Node(inputData, null);
	}

	public void addNodeHead(LetterDT inputData){
		Node temp = head;
		head = new Node(inputData, temp);
		head.setNext(temp);
	}

	public void addNodeTail(LetterDT inputData){
		Node temp = head;
		while(temp.getNext() != null){
			temp = temp.getNext();
		}

		temp.setNext(new Node(inputData, null));

	}

	public void findLetterAndUpdate(char inputLetter){
		Node temp = head;
		while(temp != null){
			if(temp.getData().getLetter() == inputLetter){
				temp.getData().updateNumber();
				temp = temp.getNext();
			}
			else{
				temp = temp.getNext();
			}
		}
	}

	public char findMostOccuring(){
		char returnVal;
		Node temp = head;
		Node highestNode = temp;
		returnVal = temp.getData().getLetter();
		while(temp.getNext() != null){
			temp = temp.getNext();
			if(highestNode.getData().getNumber() < temp.getData().getNumber()){
				returnVal = temp.getData().getLetter();
				highestNode = temp;
			}
		}
		return returnVal;
	}

	public void printList(){
		Node testingNode = head;
		while(testingNode != null){
			System.out.println(testingNode.getData().getLetter() + " = " + testingNode.getData().getNumber());
			testingNode = testingNode.getNext();
		}
	}

	class Node{
		private Node next;
		private LetterDT data;

		public Node(LetterDT inputData, Node nextNode){
			data = inputData;
			next = nextNode;
		}

		public void setNext(Node nextNode){
			next = nextNode;
		}

		public Node getNext(){
			return next;
		}

		public LetterDT getData(){
			return data;
		}
	}


}