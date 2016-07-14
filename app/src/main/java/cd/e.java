package cd;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class e {
    private static final String a = "RSA";
    private static final String b = "SHA1WithRSA";
    private static final String c = "UTF-8";

    public static String a(String str, String str2) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance(a).generatePrivate(new PKCS8EncodedKeySpec(cd.b.a(str2)));
            Signature instance = Signature.getInstance(b);
            instance.initSign(generatePrivate);
            instance.update(str.getBytes(c));
            return cd.b.a(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
