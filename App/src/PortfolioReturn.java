import java.util.Arrays;

class Asset {
    String symbol;
    double returnRate;
    double volatility;

    public Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return symbol + ":" + (int)(returnRate * 100) + "%";
    }
}

public class PortfolioReturn {
    public static void mergeSort(Asset[] assets) {
        if (assets.length < 2) return;
        int mid = assets.length / 2;
        Asset[] left = Arrays.copyOfRange(assets, 0, mid);
        Asset[] right = Arrays.copyOfRange(assets, mid, assets.length);
        
        mergeSort(left);
        mergeSort(right);
        merge(left, right, assets);
    }
    
    private static void merge(Asset[] left, Asset[] right, Asset[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].returnRate <= right[j].returnRate) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    public static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }
    
    private static int partition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate || (assets[j].returnRate == pivot.returnRate && assets[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] assets1 = {
            new Asset("AAPL", 0.12, 0.05),
            new Asset("TSLA", 0.08, 0.15),
            new Asset("GOOG", 0.15, 0.08)
        };
        Asset[] assets2 = Arrays.copyOf(assets1, assets1.length);
        
        mergeSort(assets1);
        System.out.println("Merge: " + Arrays.toString(assets1));
        
        quickSort(assets2, 0, assets2.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(assets2));
    }
}