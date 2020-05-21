public class Block {
    private boolean dirty;
    Block next, prev;
    private final long tag;

    public Block(long tag) {
        this.tag = tag;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty() {
        this.dirty = true;
    }

    public long getTag() {
        return tag;
    }
}
