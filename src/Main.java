import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream("PubliclyAvailableTestCases/traces/r1.trace");
//        CacheReader cacheReader = new CacheReader(fis);
        CacheReader cacheReader = new CacheReader(System.in);
        CacheSimulator cacheSimulator = cacheReader.readCache();

        Request req = cacheReader.readRequest();
        while (req != null) {
            cacheSimulator.request(req.getType(), req.getAddress());
            req = cacheReader.readRequest();
        }
        cacheSimulator.copyBackDirties();

        System.out.println(cacheSimulator);
    }
}
