import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFee {
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    public static void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).fee > key.fee || (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + list);
    }

    public static void printOutliers(List<Transaction> list) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                System.out.print(t.id + " ");
                found = true;
            }
        }
        if (!found) {
            System.out.print("none");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(new Transaction("id1", 10.5, "10:00"));
        transactions1.add(new Transaction("id2", 25.0, "09:30"));
        transactions1.add(new Transaction("id3", 5.0, "10:15"));
        
        List<Transaction> transactions2 = new ArrayList<>(transactions1);
        
        bubbleSort(transactions1);
        insertionSort(transactions2);
        printOutliers(transactions1);
    }
}