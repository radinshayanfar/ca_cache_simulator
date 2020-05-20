public class Statistics {
    private static final int WORD_SIZE = 4;

    private int hits;
    private int misses;

    private int fetchedWords;
    private int copyBackedWords;

    public void incHit() {
        hits++;
    }

    public void incMiss() {
        misses++;
    }

    public void incFetchedWords() {
//        fetchedWords++;
    }

    public void incCopyBackedWords() {
//        copyBackedWords++;
    }

    public int getAccesses() {
        return hits + misses;
    }

    public double getHitRate() {
        return (double) hits / getAccesses();
    }

    public double getMissRate() {
        return (double) misses / getAccesses();
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getFetchedWords() {
        return fetchedWords;
    }

    public int getCopyBackedWords() {
        return copyBackedWords;
    }
}
