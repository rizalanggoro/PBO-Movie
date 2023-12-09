package core;

import javax.swing.*;
import java.awt.*;

public class AppFont {
  public static void setNormal(JLabel label, int size) {
    label.setFont(new Font(
        label.getFont().getName(), Font.PLAIN, size
    ));
  }

  public static void setBold(JLabel label, int size) {
    label.setFont(new Font(
        label.getFont().getName(), Font.BOLD, size
    ));
  }

  public static void setColor(JLabel label, Color color) {
    label.setForeground(color);
  }
}
