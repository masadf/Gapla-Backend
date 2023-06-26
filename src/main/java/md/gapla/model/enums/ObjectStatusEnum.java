package md.gapla.model.enums;

public enum ObjectStatusEnum {
    ENABLE("Enabled"),
    DISABLE("Disabled");

    private final String ObjectStatus;

    ObjectStatusEnum(String commonInfoType) {
        this.ObjectStatus = commonInfoType;
    }

    public String getValue() {
        return ObjectStatus;
    }
}
