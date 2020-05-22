public class Statistics {
    public static final int WORD_SIZE = 4;
    private final int blockSize;

    private int hits;
    private int misses;
    private int replacements;

    private static int fetchedBlocks;
    private static int copyBackedWords;

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

    public static void incFetchedBlocks() {
        fetchedBlocks++;
    }

    public static void incCopyBackedWords(int amount) {
        copyBackedWords += amount;
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

    public static int getFetchedBlocks() {
        return fetchedBlocks;
    }

    public static int getCopyBackedWords() {
        return copyBackedWords;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("accesses: ").append(getAccesses()).append("\n");
        ret.append("misses: ").append(getMisses()).append("\n");
        ret.append("miss rate: ").append(String.format("%.4f", getMissRate()));
        ret.append(" (hit rate ").append(String.format("%.4f", getHitRate())).append(")\n");
        ret.append("replace: ").append(getReplacements()).append("\n");
        return ret.toString();
    }
}
