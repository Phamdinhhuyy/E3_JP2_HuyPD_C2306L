package Entity;

public enum RoomType {
    SI("Single"), DO("Double"), QE("Queen"), QU("Quad"), TR("Triple");
    private String type;
    RoomType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
