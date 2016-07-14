package cd;

public class d {
    String a;
    String b;
    String c;

    public d(String str) {
        try {
            for (String str2 : str.split(";")) {
                if (str2.startsWith("resultStatus")) {
                    this.a = a(str2, "resultStatus");
                }
                if (str2.startsWith("result")) {
                    this.b = a(str2, "result");
                }
                if (str2.startsWith("memo")) {
                    this.c = a(str2, "memo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String a() {
        return this.a;
    }

    public String toString() {
        return "resultStatus={" + this.a + "};memo={" + this.c + "};result={" + this.b + "}";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf("}"));
    }
}
