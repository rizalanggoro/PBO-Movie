package presentation.sections;

import core.AppFont;
import core.Constants;

import javax.swing.*;
import java.awt.*;

public class SectionMyTicket extends JPanel {
  public static final String name = "myTicket";

  private final int frameWidth = 1280;
  private final int rootWidth = 512;
  private final int leadingBound = (frameWidth - rootWidth) / 2;

  public SectionMyTicket() {
    setBackground(Constants.Colors.slate900);
    setLayout(null);

    // title
    JLabel labelTitle = new JLabel("Tiket Saya");
    AppFont.setBold(labelTitle, 24);
    labelTitle.setForeground(Constants.Colors.slate100);
    labelTitle.setBounds(
        this.leadingBound,
        32,
        this.rootWidth,
        labelTitle.getFont().getSize() + 8
    );
    this.add(labelTitle);

    // tickets
    JPanel panelTickets = new JPanel(new GridLayout(2, 1, 0, 8));
    panelTickets.setOpaque(false);
    for (int a = 0; a < 2; a++) {
      JPanel panelTicketItem = new JPanel();
      panelTicketItem.setLayout(new BoxLayout(panelTicketItem, BoxLayout.Y_AXIS));
      panelTicketItem.setBackground(Constants.Colors.slate800);
      panelTicketItem.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.Colors.slate700));
      panelTicketItem.setPreferredSize(new Dimension(this.rootWidth, 150));

      // id
      JLabel labelItemId = new JLabel("IDMV00000001");
      AppFont.setNormal(labelItemId, 14);
      labelItemId.setForeground(Constants.Colors.slate500);
      panelTicketItem.add(labelItemId);

      // title
      JLabel labelItemTitle = new JLabel("Ini adalah judul film");
      AppFont.setBold(labelItemTitle, 18);
      labelItemTitle.setForeground(Constants.Colors.slate100);
      panelTicketItem.add(labelItemTitle);

      panelTickets.add(panelTicketItem);
    }
    panelTickets.setBounds(
        this.leadingBound,
        labelTitle.getY() + labelTitle.getHeight() + 32,
        this.rootWidth,
        2 * 150
    );
    this.add(panelTickets);
  }
}
