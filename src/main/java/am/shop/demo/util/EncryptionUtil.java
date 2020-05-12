package am.shop.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtil {
  private EncryptionUtil() {
  }
  public static String encrypt(String text) {
    return DigestUtils.sha256Hex(text);
  }
}
