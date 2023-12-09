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
    panel.setBackground(new Color(0xff1e293b));

    // title
    JLabel label = new JLabel("<html><h3>Movie Application</h3></html>");
    label.setForeground(new Color(0xfff8fafc));
    label.setFont(new Font(
        label.getFont().getName(), Font.BOLD, 16
    ));
    label.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
    panel.add(label, BorderLayout.WEST);

    // menu
    JPanel panelMenu = new JPanel();
    panelMenu.setOpaque(false);
    panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.X_AXIS));
    panelMenu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));

    // menu: home
    JButton buttonHome = new JButton("Utama");
    buttonHome.addActionListener(e -> navigateToName(SectionHome.name));
    panelMenu.add(buttonHome);

    // menu: my ticket
    JButton buttonMyTicket = new JButton("Tiket Saya");
    buttonMyTicket.addActionListener(e -> navigateToName(SectionMyTicket.name));
    panelMenu.add(buttonMyTicket);

    // menu: history
    JButton buttonHistory = new JButton("Riwayat");
    panelMenu.add(buttonHistory);

    panel.add(panelMenu, BorderLayout.EAST);
    this.add(panel, BorderLayout.NORTH);
  }
}