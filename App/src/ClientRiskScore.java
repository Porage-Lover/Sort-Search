import java.util.Arrays;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRiskScore {
    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Bubble (asc): " + Arrays.toString(clients) + " // Swaps: " + swaps);
    }

    public static void insertionSort(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (clients[j].riskScore < key.riskScore || (clients[j].riskScore == key.riskScore && clients[j].accountBalance > key.accountBalance))) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(clients));
    }

    public static void printTopRisks(Client[] clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.print(clients[i].name + "(" + clients[i].riskScore + ")");
            if (i < Math.min(topN, clients.length) - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients1 = {
            new Client("C", 80, 1000),
            new Client("A", 20, 5000),
            new Client("B", 50, 2000)
        };
        Client[] clients2 = Arrays.copyOf(clients1, clients1.length);
        
        bubbleSort(clients1);
        insertionSort(clients2);
        printTopRisks(clients2, 3);
    }
}