public class Request {
    private final long address;
    private final int type;

    public Request(long address, int type) {
        this.address = address;
        this.type = type;
    }

    public long getAddress() {
        return address;
    }

    public int getType() {
        return type;
    }
}
