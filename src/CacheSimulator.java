public class CacheSimulator {
    private final Cache iCache, dCache;
    private final Configuration config;
    private final Statistics dcStats, icStats;
    private final boolean isHarvard;

    public CacheSimulator(int blockSize, boolean isHarvard, int associativity, WritePolicy writeHit, WritePolicy writeMiss, int... cSizes) {
        this.isHarvard = isHarvard;
//        if (isSplit())
//            System.out.println(0 / 0);
        config = new Configuration(blockSize, writeHit, writeMiss, associativity);
        if (isSplit()) {
            iCache = new Cache(cSizes[0], config);
            dCache = new Cache(cSizes[1], config);
        } else {
            iCache = new Cache(0, config);
            dCache = new Cache(cSizes[0], config);
        }
        dcStats = new Statistics(blockSize);
        icStats = new Statistics(blockSize);

    }

    public void request(int type, long address) {
        switch (type) {
            case 0:
                dCache.request(true, address, dcStats);
                break;
            case 1:
                dCache.request(false, address, dcStats);
                break;
            case 2:
                if (isSplit())
                    iCache.request(true, address, icStats);
                else
                    dCache.request(true, address, icStats);
                break;
        }
    }

    public void copyBackDirties() {
        dCache.copyBackDirties();
    }

    private boolean isSplit() {
        return isHarvard;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("***CACHE SETTINGS***\n");
        ret.append(isSplit() ? "Split" : "Unified").append(" I- D-cache\n");
        if (isSplit()) {
            ret.append("I-cache size: ").append(iCache.size).append("\n");
            ret.append("D-cache size: ").append(dCache.size).append("\n");
        } else {
            ret.append("Size: ").append(dCache.size).append("\n");
        }
        ret.append("Associativity: ").append(config.associativity).append("\n");
        ret.append("Block size: ").append(config.blockSize).append("\n");
        ret.append("Write policy: ").append(config.writeHitPolicy == WritePolicy.WRITE_BACK ? "WRITE BACK" : "WRITE THROUGH").append("\n");
        ret.append("Allocation policy: ").append(config.writeMissPolicy == WritePolicy.WRITE_ALLOCATE ? "WRITE ALLOCATE" : "WRITE NO ALLOCATE").append("\n");
        ret.append("\n***CACHE STATISTICS***\n");
        ret.append("INSTRUCTIONS\n");
        ret.append(icStats.toString());
        ret.append("DATA\n");
        ret.append(dcStats.toString());
        ret.append("TRAFFIC (in words)\n");
        ret.append("demand fetch: ").append(Statistics.getFetchedBlocks() * config.blockSize / Statistics.WORD_SIZE).append("\n");
        ret.append("copies back: ").append(Statistics.getCopyBackedWords()).append("\n");
        return ret.toString();
    }
}
