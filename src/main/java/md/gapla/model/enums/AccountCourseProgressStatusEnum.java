package md.gapla.model.enums;

public enum AccountCourseProgressStatusEnum {

    PROGRESS("Progress"),
    NOT_START("Not start"),
    FINISHED("Finished");

    private final String status;

    AccountCourseProgressStatusEnum(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
