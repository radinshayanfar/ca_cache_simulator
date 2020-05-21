public class Cache {
    public final int size;
    private final Configuration config;
    public final Statistics stats;

    private Set[] sets;

    public Cache(int size, Configuration config) {
        this.size = size;
        this.config = config;
        stats = new Statistics(config.blockSize);

        sets = new Set[size / config.blockSize / config.associativity];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Set(config, stats);
        }
    }

    public void request(boolean read, long address) {
        int block = (int) (address / config.blockSize);
        sets[block % sets.length].request(read, block / sets.length);
    }

    public void copyBackDirties() {
        for (Set s :
                sets) {
            s.copyBackDirties();
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("accesses: ").append(stats.getAccesses()).append("\n");
        ret.append("misses: ").append(stats.getMisses()).append("\n");
        ret.append("miss rate: ").append(String.format("%.4f", stats.getMissRate()));
        ret.append(" (hit rate ").append(String.format("%.4f", stats.getHitRate())).append(")\n");
        ret.append("replace: ").append(stats.getReplacements()).append("\n");
        return ret.toString();
    }
}
