import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
  private final CardLayout cardLayout = new CardLayout();
  private final JPanel containerContent = new JPanel();

  public Main() {
    setTitle("Coba GUI");
    setSize(800, 600);
    setMinimumSize(new Dimension(800, 600));
    setResizable(true);
    setLayout(new BorderLayout());

    drawContent();
    drawNavbar();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  private void navigateSectionToName(String name) {
    System.out.println("navigate to section: " + name);
    this.cardLayout.show(this.containerContent, name);
  }

  private void drawNavbar() {
    JPanel navbarContainer = new JPanel();
    navbarContainer.setLayout(new BorderLayout());
    navbarContainer.setBackground(Color.RED);
    navbarContainer.setPreferredSize(new Dimension(0, 64));

    // title
    JLabel title = new JLabel("Movie Application");
    title.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
    navbarContainer.add(title, BorderLayout.WEST);

    // menu container
    JPanel navbarMenuContainer = new JPanel();
    navbarMenuContainer.setBackground(Color.RED);
    navbarMenuContainer.setLayout(new BoxLayout(navbarMenuContainer, BoxLayout.X_AXIS));

    // menu: home
    JButton menuItemHome = new JButton("Home");
    menuItemHome.addActionListener(e -> navigateSectionToName("home"));
    navbarMenuContainer.add(menuItemHome);

    // menu: my ticket
    JButton menuItemMyTicket = new JButton("My Ticket");
    menuItemMyTicket.addActionListener(e -> navigateSectionToName("myTicket"));
    navbarMenuContainer.add(menuItemMyTicket);

    // menu: history
    JButton menuItemHistory = new JButton("History");
    menuItemHistory.addActionListener(e -> navigateSectionToName("history"));
    navbarMenuContainer.add(menuItemHistory);

    navbarMenuContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));
    navbarContainer.add(navbarMenuContainer, BorderLayout.EAST);

    add(navbarContainer, BorderLayout.NORTH);
  }

  private void drawContent() {
    this.containerContent.setLayout(cardLayout);
    this.containerContent.setBackground(Color.BLUE);

    this.containerContent.add("home", new JScrollPane(sectionHome()));
    this.containerContent.add("myTicket", sectionMyTicket());
    this.containerContent.add("history", sectionHistory());

    add(this.containerContent, BorderLayout.CENTER);
  }

  private JPanel sectionHome() {
    JPanel section = new JPanel();
    section.setLayout(new GridLayout(-1, 4, 8, 8));
    section.setPreferredSize(new Dimension(-1, 1000));
    section.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));

    for (Movie movie : SourceMovie.list) {
      JPanel itemContainer = new JPanel(new BorderLayout());
      itemContainer.setBackground(Color.YELLOW);
      itemContainer.setPreferredSize(new Dimension(300, 400));

      // poster
      double frameWidth = this.getSize().getWidth();
      double posterWidth = (frameWidth - 64 - 24) / 4;
      double posterHeight = posterWidth / 600 * 900;

      BufferedImage imagePoster = null;
      try {
        imagePoster = ImageIO.read(new File(movie.getPosterPath()));
        Image image = imagePoster.getScaledInstance(
            (int) posterWidth, (int) posterHeight, Image.SCALE_SMOOTH
        );
        JLabel labelPoster = new JLabel(new ImageIcon(image));
        itemContainer.add(labelPoster, BorderLayout.CENTER);
      } catch (IOException e) {
        e.printStackTrace();
      }

      // details
      JPanel panelDetails = new JPanel();
      panelDetails.setBackground(Color.LIGHT_GRAY);
      panelDetails.setLayout(new GridBagLayout());

      // constrains
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.BOTH;

      // details: title
      JLabel title = new JLabel("<html><h3>" + movie.getTitle() + "</h3><html>");
      constraints.gridy = 0;
      constraints.weightx = 1;
      panelDetails.add(title, constraints);

      // details: synopsis
      JLabel synopsis = new JLabel(movie.getSynopsis());
      constraints.gridy = 1;
      constraints.weightx = 1;
      panelDetails.add(synopsis, constraints);

      // details: rating
      JLabel rating = new JLabel("4.5/5");
      constraints.gridy = 2;
      constraints.weightx = 1;
      panelDetails.add(rating, constraints);

      // details: button order
      JButton buttonOrder = new JButton("Order");
      constraints.gridy = 3;
      constraints.weightx = 1;
      panelDetails.add(buttonOrder, constraints);

      itemContainer.add(panelDetails, BorderLayout.SOUTH);

      section.add(itemContainer);
    }
    return section;
  }

  private JPanel sectionMyTicket() {
    JPanel section = new JPanel();

    JLabel label = new JLabel("Section My Ticket");
    section.add(label);

    return section;
  }

  private JPanel sectionHistory() {
    JPanel section = new JPanel();

    JLabel label = new JLabel("Section History");
    section.add(label);

    return section;
  }
}

