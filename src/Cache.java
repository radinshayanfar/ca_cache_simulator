public class Cache {
    public final int size;
    private final Configuration config;

    private Set[] sets;

    public Cache(int size, Configuration config) {
        this.size = size;
        this.config = config;

        sets = new Set[size / config.blockSize / config.associativity];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Set(config);
        }
    }

    public void request(boolean read, long address, Statistics stats) {
        int block = (int) (address / config.blockSize);
        sets[block % sets.length].request(read, block / sets.length, stats);
    }

    public void copyBackDirties() {
        for (Set s :
                sets) {
            s.copyBackDirties();
        }
    }


}
