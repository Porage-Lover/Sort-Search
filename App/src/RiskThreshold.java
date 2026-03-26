public class RiskThreshold {
    public static void linearSearch(int[] bands, int target) {
        int comps = 0;
        boolean found = false;
        for (int i = 0; i < bands.length; i++) {
            comps++;
            if (bands[i] == target) {
                found = true;
                break;
            }
        }
        System.out.println("Linear: threshold=" + target + " -> " + (found ? "found" : "not found") + " (" + comps + " comps)");
    }

    public static void binarySearchFloorCeiling(int[] bands, int target) {
        int low = 0;
        int high = bands.length - 1;
        int comps = 0;
        
        int floor = -1;
        int ceiling = -1;
        
        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            if (bands[mid] == target) {
                floor = bands[mid];
                ceiling = bands[mid];
                break;
            } else if (bands[mid] < target) {
                floor = bands[mid];
                low = mid + 1;
            } else {
                ceiling = bands[mid];
                high = mid - 1;
            }
        }
        
        System.out.println("Binary floor(" + target + "): " + (floor != -1 ? floor : "none") + ", ceiling: " + (ceiling != -1 ? ceiling : "none") + " (" + comps + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        linearSearch(risks, 30);
        binarySearchFloorCeiling(risks, 30);
    }
}