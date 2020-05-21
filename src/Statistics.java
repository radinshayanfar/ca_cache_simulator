public class Statistics {
    public static final int WORD_SIZE = 4;
    private final int blockSize;

    private int hits;
    private int misses;
    private int replacements;

    private int fetchedBlocks;
    private int copyBackedBlocks;

    public Statistics(int blockSize) {
        this.blockSize = blockSize;
    }

    public void incHit() {
        hits++;
    }

    public void incMiss() {
        misses++;
    }

    public void incReplacement() {
        replacements++;
    }

    public void incFetchedBlocks() {
        fetchedBlocks++;
    }

    public void incCopyBackedBlocks() {
        copyBackedBlocks++;
    }

    public int getAccesses() {
        return hits + misses;
    }

    public double getHitRate() {
        return getAccesses() == 0 ? 0 : (double) hits / getAccesses();
    }

    public double getMissRate() {
        return getAccesses() == 0 ? 0 : (double) misses / getAccesses();
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getReplacements() {
        return replacements;
    }

    public int getFetchedBlocks() {
        return fetchedBlocks;
    }

    public int getCopyBackedBlocks() {
        return copyBackedBlocks;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "accesses=" + getAccesses() +
                ", hits=" + hits +
                ", misses=" + misses +
                ", replacements=" + replacements +
                ", fetchedBlocks=" + fetchedBlocks +
                ", copyBackedBlocks=" + copyBackedBlocks +
                '}';
    }
}
