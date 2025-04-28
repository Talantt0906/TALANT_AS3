import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int id1 = rand.nextInt(10000);
            int id2 = rand.nextInt(10000);
            MyTestingClass key = new MyTestingClass(id1, id2);
            Student value = new Student();
            table.put(key, value);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, Student> head = table.chainArray[i];
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}

