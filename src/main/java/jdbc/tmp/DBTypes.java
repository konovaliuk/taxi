package jdbc.tmp;

public enum DBTypes {

    MYSQL("MYSQL"),
    ORACLE("ORACLE");

    DBTypes(String id) {
        this.id = id;
    }
    private String id;

    public String getId() {
        return id;
    }
}
