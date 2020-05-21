import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("PubliclyAvailableTestCases/traces/3.trace");

        CacheReader cacheReader = new CacheReader(fis);
        CacheSimulator cacheSimulator = cacheReader.readCache();
    }
}
