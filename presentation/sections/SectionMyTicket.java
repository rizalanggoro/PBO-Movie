package presentation.sections;

import javax.swing.*;
import java.awt.*;

public class SectionMyTicket extends JPanel {
  public static final String name = "myTicket";

  public SectionMyTicket() {
    setBackground(new Color(0xff0f172a));
    JLabel label = new JLabel("My Ticket");

    this.add(label);
  }
}
