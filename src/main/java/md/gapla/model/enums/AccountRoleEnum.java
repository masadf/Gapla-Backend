package md.gapla.model.enums;

public enum AccountRoleEnum {
    ADMIN("Admin"),
    TEACHER("Teacher"),
    STUDENT("Student");

    private final String role;

    AccountRoleEnum(String status) {
        this.role = status;
    }

    public String getValue() {
        return role;
    }
}
