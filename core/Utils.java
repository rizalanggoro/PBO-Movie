package core;

import java.text.DecimalFormat;

public class Utils {
  public static class Format {
    public static String decimal(int num) {
      return new DecimalFormat("#,###").format(num);
    }
  }
}
