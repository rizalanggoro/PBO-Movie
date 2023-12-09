import core.AppFont;
import core.Constants;
import presentation.SectionParams;
import presentation.sections.SectionHome;
import presentation.sections.SectionMovieOrder;
import presentation.sections.SectionMyTicket;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
  private final JPanel panelContent = new JPanel();
  private final CardLayout cardLayoutContent = new CardLayout();

  // sections
  private SectionMovieOrder sectionMovieOrder;
  private SectionHome sectionHome;
  private SectionMyTicket sectionMyTicket;

  public Main() {
    setTitle("Movie Application");
    setSize(1280, 720);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setBackground(new Color(0xff0f172a));

    drawContent();
    drawNavbar();

    setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  private void navigateToName(String name) {
    if (name.equals(SectionMovieOrder.name)) {
      System.out.println(SectionParams.selectedMovie.getPosterPath());
      this.panelContent.remove(this.sectionMovieOrder);
      this.sectionMovieOrder = new SectionMovieOrder(this::navigateToName);
      this.panelContent.add(SectionMovieOrder.name, this.sectionMovieOrder);
    }

    this.cardLayoutContent.show(this.panelContent, name);
  }

  private void drawContent() {
    this.panelContent.setLayout(this.cardLayoutContent);
    this.panelContent.setBackground(new Color(0xff0f172a));

    this.sectionHome = new SectionHome(this::navigateToName);
    this.sectionMovieOrder = new SectionMovieOrder(this::navigateToName);
    this.sectionMyTicket = new SectionMyTicket();

    this.panelContent.add(SectionHome.name, this.sectionHome);
    this.panelContent.add(SectionMovieOrder.name, this.sectionMovieOrder);
    this.panelContent.add(SectionMyTicket.name, this.sectionMyTicket);

    navigateToName(SectionMyTicket.name);

    this.add(this.panelContent, BorderLayout.CENTER);
  }

  private void drawNavbar() {
    JPanel panel = new JPanel();
    panel.setBorder(
        BorderFactory.createMatteBorder(
            0, 0, 1, 0, new Color(0xff334155)
        )
    );
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(1280, 64));
    panel.setBackground(Constants.Colors.slate800);

    // title
    JLabel label = new JLabel("Movie");
    AppFont.setBold(label, 16);
    label.setForeground(Constants.Colors.slate100);
    label.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
    panel.add(label, BorderLayout.WEST);

    // menu
    JPanel panelMenu = new JPanel(new GridLayout(1, 3, 16, 0));
    panelMenu.setOpaque(false);
    panelMenu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));

    String[] titles = {"Daftar Film", "Tiket Saya"};
    String[] actions = {SectionHome.name, SectionMyTicket.name};

    for (int a = 0; a < titles.length; a++) {
      JButton buttonHome = new JButton(titles[a]);
      buttonHome.setBorder(null);
      buttonHome.setOpaque(false);
      buttonHome.setForeground(Constants.Colors.slate500);
      AppFont.setNormal(buttonHome, 16);
      buttonHome.setBackground(new Color(0, 0, 0, 0));
      final String actionName = actions[a];
      buttonHome.addActionListener(e -> navigateToName(actionName));
      panelMenu.add(buttonHome);
    }

    // menu: home


    // menu: my ticket
//    JButton buttonMyTicket = new JButton("Tiket Saya");
//    buttonMyTicket.addActionListener(e -> navigateToName(SectionMyTicket.name));
//    panelMenu.add(buttonMyTicket);

    // menu: history
//    JButton buttonHistory = new JButton("Riwayat");
//    panelMenu.add(buttonHistory);

    panel.add(panelMenu, BorderLayout.EAST);
    this.add(panel, BorderLayout.NORTH);
  }
}