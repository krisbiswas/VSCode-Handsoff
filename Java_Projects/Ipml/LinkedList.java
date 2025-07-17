package Ipml;

class Node{
    int val;
    Node next;
    Node(int val, Node next){
        this.val = val;
        this.next = next;
    }
}

class LL{
    Node head;
    Node lastNode;
    int size;

    LL(){
        head = null;
        lastNode = null;
        size = 0;
    }

    public void add(int val){
        Node newNode = new Node(val, null);
        if (head == null){
            head = newNode;
            lastNode = newNode;
        }else{
            lastNode.next = newNode;
            lastNode = lastNode.next;
        }
        size++;
    }

    public int pop(){
        Node temp = head;
        while(temp.next != lastNode){
            temp = temp.next;
        }
        int retVal = lastNode.val;
        temp.next = null;
        lastNode = temp;
        size--;
        return retVal;
    }

    public void insert(int v, int index){
        if(index == 1){
            Node newNode = new Node(v, head);
            head = newNode;return;
        }else{
            int i=1;
            Node temp = head;
            while(i<index-1 && temp != null){
                temp = temp.next;
                i++;
            }
            if(temp == null){
                add(v);
            }else{
                Node newNode = new Node(v, temp.next);
                temp.next = newNode;
            }
        }
        size++;
    }

    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.val+"->");
            temp = temp.next;
        }
        System.out.println();
    }

    public void rotate(int k){
        k = k%size;

        // before  1->2->3->4->5   k = 3
        // after   3->4->5->1->2

        int x = 1;
        Node temp = head;
        while(x < size-k){
            temp = temp.next;
            x++;
        }
        lastNode.next = head;
        head = temp.next;
        temp.next = null;
        while(lastNode.next != null){
            lastNode = lastNode.next;
        }

        /* while(k>0){
            Node temp = head;
            while(temp.next != lastNode){
                temp = temp.next;
            }
            lastNode.next = head;
            head = lastNode;
            temp.next = null;
            lastNode = temp;
            k--;
        } */
    }
}

public class LinkedList{
    public static void main(String[] args) {
        LL list = new LL();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        list.rotate(18);
        list.print();
        
    }
}