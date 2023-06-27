package utilities;

public enum ContentTypeEnums {
    JSON("application/json"),
    XML("application/xml"),
    TEXT("text/plain");

    private String value;

    ContentTypeEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}