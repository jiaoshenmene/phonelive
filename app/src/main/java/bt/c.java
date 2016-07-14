package bt;

import java.util.TimeZone;

public final class c {
    private static final long[] a = new long[]{300, 200, 300, 200};
    private static final TimeZone b = TimeZone.getTimeZone("GMT");
    private static final char[] c = new char[]{'<', '>', '\"', '\'', '&'};
    private static final String[] d = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;"};

    public static boolean a(String str) {
        return str == null || str.length() <= 0;
    }
}
