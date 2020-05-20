public class Configuration {
    public final int blockSize;
    public final WritePolicy writeHitPolicy;
    public final WritePolicy writeMissPolicy;
    public final int associativity;

    public Configuration(int blockSize, WritePolicy writeHitPolicy, WritePolicy writeMissPolicy, int associativity) {
        this.blockSize = blockSize;
        this.writeHitPolicy = writeHitPolicy;
        this.writeMissPolicy = writeMissPolicy;
        this.associativity = associativity;
    }

}
