package DSA.Text_editor;

public class TextEditor {
    public static void main(String[] args) {
        // String[] commands = new String[]{
        //     "TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"
        // };
        String[] commands = new String[]{
            "TextEditor","addText","cursorLeft","deleteText","cursorLeft","addText","cursorRight"
        };
        // Object[] inputs = new Object[]{
        //     null, "leetcode", 4, "practice", 3, 8, 10, 2, 6
        // };
        Object[] inputs = new Object[]{
            null, "bxyackuncqzcqo",12,3,5,"osdhyvqxf",10
        };
        TextEditor editor = new TextEditor();
        int i=0;
        for(String command : commands){
            switch (command) {
                case "addText":
                    editor.addText(String.valueOf(inputs[i]));
                    break;
                case "deleteText":
                    System.out.println("deleteText= "+editor.deleteText((int) inputs[i]));
                    break;
                case "cursorLeft":
                    System.out.println("cursorLeft= "+editor.cursorLeft((int) inputs[i]));
                    break;
                case "cursorRight":
                    System.out.println("cursorRight= "+editor.cursorRight((int) inputs[i]));
                    break;
                default:
                    break;
            }
            i++;
        }
    }


    class Node {
        char c;
        Node prev, next;
        Node(char c){
            this.c=c;
        }
        public String toString(){
            return c+"->";
        }
    }

    Node head, current;
    int position=0, len = 0;

    public TextEditor() {
        // Init Custom doubly linkedlist
        head = new Node('|');
        current = head;
    }

    private void print(String tag){
        Node iter = head.next;
        System.out.println(tag);
        while(iter != null){
            System.out.print(iter);
            iter = iter.next;
        }
        System.out.println("\ncurPosition="+position+"{"+current+"}, len="+len);
    }
    
    public void addText(String text) {
        // append char to linkedlist
        for(char c : text.toCharArray()){
            Node newNode = new Node(c);
            if(current == head){
                if(head.next != null){
                    // append at begining
                    newNode.next = head.next;
                }
                current = newNode;
                newNode.prev = head;
                head.next = current;
                
            } else {
                if(current.next != null){
                    newNode.next = current.next;
                    current.next.prev = newNode;
                }
                newNode.prev = current;
                current.next = newNode;
                current = current.next;
            }
            position++;
            len++;
        }
    }
    
    public int deleteText(int k) {
        // swap prev next pointers of current to delete current node
        // do this for at max k times.
        int i=0;
        while(current != head && i < k){
            current.prev.next = current.next;
            if(current.next != null){
                current.next.prev = current.prev;
            }
            current = current.prev;
            position--;
            len--;
            i++;
        }
        return i;
    }
    
    public String cursorLeft(int k) {
        // iter cursor to prev at max k times
        // iter min(10, currentIndex) to prev
        int i=0;
        while(current != head && i < k){
            current = current.prev;
            position--;
            i++;
        }
        int numOfIter = Math.min(10, position);
        Node temp = current;
        StringBuilder sb = new StringBuilder(numOfIter);
        while(numOfIter-- > 0){
            sb.append(temp.c);
            temp = temp.prev;
        }
        return sb.reverse().toString();
    }
    
    public String cursorRight(int k) {
        // iter cursor to right at max k times
        // iter min(10, currentIndex) to prev
        int i=0;
        while(current.next != null && i < k){
            current = current.next;
            position++;
            i++;
        }
        int numOfIter = Math.min(10, position);
        Node temp = current;
        StringBuilder sb = new StringBuilder(numOfIter);
        while(numOfIter-- > 0){
            sb.append(temp.c);
            temp = temp.prev;
        }
        return sb.reverse().toString();
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
