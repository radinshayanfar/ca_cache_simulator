public class Set {
    Block head, tail;
    long size;
    private final Configuration config;
    private final Statistics stats;

    public Set(Configuration config, Statistics stats) {
        this.config = config;
        this.stats = stats;
    }

    public void request(boolean read, long tag) {
        if (read) {
            if (!readHit(tag))
                readMiss(tag);
        } else if (!writeHit(tag, true)) {
            writeMiss(tag);
        }
    }

    private boolean readHit(long tag) {
        Block current = head;
        while (current != null) {
            if (current.getTag() == tag) {
                makeHead(current);
                stats.incHit();
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void makeHead(Block b) {
        if (b != head) {
            b.prev.next = b.next;
            if (b.next != null)
                b.next.prev = b.prev;
            else
                tail = b.prev;
            addHead(b);
        }
    }

    private void addHead(Block b) {
        if (head == null) {
            tail = head = b;
            return;
        }
        b.prev = null;
        b.next = head;
        head.prev = b;
        head = b;
    }

    public void readMiss(long tag) {
        stats.incMiss();
        stats.incFetchedBlocks();
        if (size < config.associativity) {
            addHead(new Block(tag));
            size++;
            return;
        }

        // Evicting tail
        stats.incReplacement();
        if (config.writeHitPolicy == WritePolicy.WRITE_BACK && tail!= null && tail.isDirty()) {
            stats.incCopyBackedWords(config.blockSize / Statistics.WORD_SIZE);
        }
        if (tail == head) {
            head = tail = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        addHead(new Block(tag));
    }

    public boolean writeHit(long tag, boolean updateHitStats) {
        Block current = head;
        while (current != null) {
            if (current.getTag() == tag) {
                if (config.writeHitPolicy == WritePolicy.WRITE_BACK) {
                    current.setDirty();
                } else {
                    stats.incCopyBackedWords(1);
                }
                if (updateHitStats)
                    stats.incHit();
                makeHead(current);
                return true;
            }

            current = current.next;
        }
        return false;
    }

    private void writeMiss(long tag) {
        if (config.writeMissPolicy == WritePolicy.WRITE_AROUND) {
            stats.incCopyBackedWords(1);
            stats.incMiss();
        } else {
            readMiss(tag);
            writeHit(tag, false);
        }
    }

    public void copyBackDirties() {
        if (config.writeHitPolicy != WritePolicy.WRITE_BACK)
            return;

        Block current = head;
        while (current != null) {
            if (current.isDirty()) {
                stats.incCopyBackedWords(config.blockSize / Statistics.WORD_SIZE);
            }
            current = current.next;
        }
    }
}
