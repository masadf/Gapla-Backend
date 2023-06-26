package md.gapla.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FormattingUtils {

    private FormattingUtils() {
    }

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    public static final ThreadLocal<SimpleDateFormat> ISO_DATE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    public static final ThreadLocal<SimpleDateFormat> INVOICE_NAME_DATE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS"));
    public static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    static {
        DECIMAL_FORMAT.setGroupingUsed(true);
        DECIMAL_FORMAT.setGroupingSize(3);
        DECIMAL_FORMAT.setMinimumFractionDigits(2);
    }
}
