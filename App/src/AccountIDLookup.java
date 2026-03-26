import java.util.Arrays;

public class AccountIDLookup {
    public static void linearSearch(String[] logs, String target) {
        int first = -1;
        int comps = 0;
        for (int i = 0; i < logs.length; i++) {
            comps++;
            if (logs[i].equals(target)) {
                if (first == -1) first = i;
            }
        }
        System.out.println("Linear first " + target + ": index " + first + " (" + comps + " comparisons)");
    }

    public static void binarySearch(String[] logs, String target) {
        int low = 0;
        int high = logs.length - 1;
        int comps = 0;
        int firstMatch = -1;
        
        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                firstMatch = mid;
                high = mid - 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        int count = 0;
        if (firstMatch != -1) {
            int idx = firstMatch;
            while (idx < logs.length && logs[idx].equals(target)) {
                count++;
                idx++;
            }
        }
        System.out.println("Binary " + target + ": index " + firstMatch + " (" + comps + " comparisons), count=" + count);
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        linearSearch(logs, "accB");
        
        Arrays.sort(logs);
        binarySearch(logs, "accB");
    }
}