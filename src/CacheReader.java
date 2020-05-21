import java.io.InputStream;
import java.util.Scanner;

public class CacheReader {
    private Scanner sc;

    public CacheReader(InputStream is) {
        sc = new Scanner(is);
    }

    public CacheSimulator readCache() {
        String[] cacheParts = sc.nextLine().split(" - ");
        int blockSize = Integer.parseInt(cacheParts[0]);
        boolean isHarvard = cacheParts[1].equals("1");
        int associativity = Integer.parseInt(cacheParts[2]);
        WritePolicy writeHit = cacheParts[3].equals("wb") ? WritePolicy.WRITE_BACK : WritePolicy.WRITE_THROUGH;
        WritePolicy writeMiss = cacheParts[4].equals("wa") ? WritePolicy.WRITE_ALLOCATE : WritePolicy.WRITE_AROUND;

        String[] sizeParts = sc.nextLine().split(" - ");
        int[] sizes;
        if (sizeParts.length == 1) {
            sizes = new int[1];
            sizes[0] = Integer.parseInt(sizeParts[0]);
        } else {
            sizes = new int[2];
            sizes[0] = Integer.parseInt(sizeParts[0]);
            sizes[1] = Integer.parseInt(sizeParts[1]);
        }

        CacheSimulator cs = new CacheSimulator(blockSize, isHarvard, associativity, writeHit, writeMiss, sizes);
        return cs;
    }

    public void readRequest() {

    }
}
