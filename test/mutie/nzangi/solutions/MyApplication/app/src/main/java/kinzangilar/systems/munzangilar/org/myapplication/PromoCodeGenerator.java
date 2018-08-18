package kinzangilar.systems.munzangilar.org.myapplication;

import java.security.SecureRandom;
import java.util.Locale;

/**
 * Created by nzang on 8/16/2018.
 */

public class PromoCodeGenerator {

    public PromoCodeGenerator() {
    }

    public String generatePromoCode() {
        int promoCodeSize=6;
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;
        StringBuilder sb = new StringBuilder(promoCodeSize);
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < promoCodeSize; i++) {
            sb.append(alphanum.charAt(rnd.nextInt(alphanum.length())));
        }

        return sb.toString();
    }
}
