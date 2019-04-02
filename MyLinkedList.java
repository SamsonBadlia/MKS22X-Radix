public class MyLinkedList<E>{

    private class Node{
     private E data;
     private Node next,prev;

     public Node(E data, Node next, Node prev){
       this.data = data;
       this.next = next;
       this.prev = prev;
     }

     public Node next(){
       return next;
     }

     public Node prev(){
       return prev;
     }

     public void setData(E data){
       this.data = data;
      }

     public void setNext(Node next){
       this.next = next;
      }

     public void setPrev(Node prev){
       this.prev = prev;
      }

     public E getData(){
       return data;
      }

    }

 private int length;
 private Node start,end;

 public MyLinkedList(){
   length = 0;
   start = null;
   end = null;
 }

 public void clear(){
   length = 0;
   start = null;
   end = null;
 }

 public int size(){
   return length;
 }

 public boolean add(E value){
   //checks if list is empty
   if (length == 0){
     //if so sets start node and length to 1
     start = new Node(value, null, null);
     end = start;
     length = 1;
     return true;
   }
   // adds new ndoe to end otherwise
   Node n = end;
   end = new Node(value, null , n);
   end.prev().setNext(end);
   //increase length
   length++;
   return true;
 }

 public String toString(){
   String s = "[ ";
   Node current = start;
   //loops through list until end is found
   while (current != null){
     // adds data from node to string
     if (current == end){
       s += current.getData();
     }
     else{
       s += current.getData()+ " , ";
    }
     //next node
     current = current.next();
   }
   //returns string of the data
   s += "]";
   return s;
 }

 private Node getNthNode(int n){
    Node current = start;
    int count = 0;
    //loops through Nodes until nth Node
    while (current != null && count != n){
        count++;
        //goes to next node if its not the nth node
        current = current.next;
    }
    //returns the node
    return current;
 }

    public E removeFront(){
    if( length == 1){
      E temp = start.getData();
      start = end = null;
      length = 0;
      return temp;
    }
    Node temp = start;
    Node newStart = start.next();
    start = newStart;
    newStart.setPrev(null);
    temp.setNext(null);
    length--;
    return temp.getData();
    }


 public boolean contains(Integer value){
   Node current = start;
   int count = 0;
   //loops through Nodes from start until Node with vlaue is found
   for (int i = 0; i < length; i++){
     if (getNthNode(i).getData() == value){
       return true;
     }
   }
   //if Node not found return false
   return false;
 }

 public int indexOf(Integer value){
   //check if it contains the value
   if (contains(value)){
     Node current = start;
     int count = 0;
     //loop through Nodes from start until Node with value is found
     for (int i = 0; i < length;i++){
       if (getNthNode(i).getData() == value){
         //returns index of Node with value
         return i;
       }
     }
   }
   //if it doesn't contain the value returns -1
   return -1;
 }

 public void add(int index, E value){
  if (index < 0 || index > length){
    throw new IndexOutOfBoundsException();
  }
  Node n = new Node(value, null, null);
  //checks to see if set at start
  if (index == 0) {
    //set refernces to n to null and the other node at 0
    n.setPrev(null);
    n.setNext(getNthNode(0));
    start = n;
    //increase length
    length++;
  }
  else {
    //change refernces of nodes
    n.setNext(getNthNode(index));
    n.setPrev(getNthNode(index - 1));
    getNthNode(index).setPrev(n);
    getNthNode(index - 1).setNext(n);
    //increase length
    length++;
  }
}

public E removeFirst(){
  E temp = start.getData();
  this.start = start.next();
  return temp;
}

 public void extend(MyLinkedList<E> other){
     this.end.setNext(other.start);
     this.end = other.end;
     this.length = this.length + other.length;
     other.length = 0;
     other.end = null;
     other.start = null;

 }

}
