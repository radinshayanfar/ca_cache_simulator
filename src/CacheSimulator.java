import java.util.Arrays;

public class CacheSimulator {
    private Cache iCache, dCache;

    public CacheSimulator(int blockSize, boolean isHarvard, int associativity, WritePolicy writeHit, WritePolicy writeMiss, int... cSizes) {
        System.out.println("Block Size: " + blockSize);
        System.out.println("Harvard: " + isHarvard);
        System.out.println("Assoc: " + associativity);
        System.out.println(writeHit);
        System.out.println(writeMiss);
        System.out.println(Arrays.toString(cSizes));
    }

    private boolean isSplit() {
        return iCache != null;
    }

}
