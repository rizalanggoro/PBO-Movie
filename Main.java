import presentation.SectionHome;
import presentation.SectionMovieOrder;
import presentation.SectionParams;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
  private final JPanel panelContent = new JPanel();
  private final CardLayout cardLayoutContent = new CardLayout();
  private final SectionMovieOrder sectionMovieOrder = new SectionMovieOrder((name) -> {
  });
  // sections
  private final SectionHome sectionHome = new SectionHome(this::navigateToName);

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
//      this.sectionMovieOrder.draw();
      this.panelContent.remove(this.sectionMovieOrder);
      this.panelContent.add(SectionMovieOrder.name, new SectionMovieOrder(this::navigateToName));
    }

    this.cardLayoutContent.show(this.panelContent, name);
  }

  private void drawContent() {
    this.panelContent.setLayout(this.cardLayoutContent);
    this.panelContent.setBackground(new Color(0xff0f172a));

    this.panelContent.add(SectionHome.name, this.sectionHome);
    this.panelContent.add(SectionMovieOrder.name, this.sectionMovieOrder);

    navigateToName(SectionMovieOrder.name);

    this.add(this.panelContent, BorderLayout.CENTER);
  }

  private void drawNavbar() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 32));
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(1280, 64));
    panel.setBackground(Color.RED);

    // title
    JLabel label = new JLabel("<html><h3>Movie Application</h3></html>");
    panel.add(label, BorderLayout.WEST);

    // menu
    JPanel panelMenu = new JPanel();
    panelMenu.setOpaque(false);
    panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.X_AXIS));

    // menu: home
    JButton buttonHome = new JButton("Utama");
    buttonHome.addActionListener(e -> navigateToName(SectionHome.name));
    panelMenu.add(buttonHome);

    // menu: my ticket
    JButton buttonMyTicket = new JButton("Tiket Saya");
    panelMenu.add(buttonMyTicket);

    // menu: history
    JButton buttonHistory = new JButton("Riwayat");
    panelMenu.add(buttonHistory);

    panel.add(panelMenu, BorderLayout.EAST);
    this.add(panel, BorderLayout.NORTH);
  }
}