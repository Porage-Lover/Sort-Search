import java.util.Arrays;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolume {
    public static void mergeSort(Trade[] trades) {
        if (trades.length < 2) return;
        int mid = trades.length / 2;
        Trade[] left = Arrays.copyOfRange(trades, 0, mid);
        Trade[] right = Arrays.copyOfRange(trades, mid, trades.length);
        
        mergeSort(left);
        mergeSort(right);
        merge(left, right, trades);
    }
    
    private static void merge(Trade[] left, Trade[] right, Trade[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }
    
    private static int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }
    
    public static Trade[] mergeSessions(Trade[] session1, Trade[] session2) {
        Trade[] merged = new Trade[session1.length + session2.length];
        int i = 0, j = 0, k = 0;
        while (i < session1.length && j < session2.length) {
            if (session1[i].volume <= session2[j].volume) {
                merged[k++] = session1[i++];
            } else {
                merged[k++] = session2[j++];
            }
        }
        while (i < session1.length) merged[k++] = session1[i++];
        while (j < session2.length) merged[k++] = session2[j++];
        return merged;
    }
    
    public static int computeTotal(Trade[] trades) {
        int total = 0;
        for (Trade t : trades) {
            total += t.volume;
        }
        return total;
    }

    public static void main(String[] args) {
        Trade[] trades1 = {
            new Trade("3", 500),
            new Trade("1", 100),
            new Trade("2", 300)
        };
        Trade[] trades2 = Arrays.copyOf(trades1, trades1.length);
        
        mergeSort(trades1);
        System.out.println("MergeSort: " + Arrays.toString(trades1));
        
        quickSort(trades2, 0, trades2.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(trades2));
        
        Trade[] morning = { new Trade("m1", 200), new Trade("m2", 400) };
        Trade[] afternoon = { new Trade("a1", 300) };
        Trade[] merged = mergeSessions(morning, afternoon);
        int total = computeTotal(merged);
        System.out.println("Merged morning+afternoon total: " + total);
    }
}