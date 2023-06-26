package md.gapla.model.enums;

public enum CommonInfoTypeEnum {

    ABOUT_US("About us"),
    INDEX("Index"),
    CONTACTS("Contacts");

    private final String commonInfoType;

    CommonInfoTypeEnum(String commonInfoType) {
        this.commonInfoType = commonInfoType;
    }

    public String getValue() {
        return commonInfoType;
    }
}
