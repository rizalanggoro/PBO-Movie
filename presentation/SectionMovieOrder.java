package presentation;

import domain.models.Movie;

import javax.swing.*;
import java.awt.*;

public class SectionMovieOrder extends JPanel {
  public static final String name = "movieOrder";
  private final OnRequestNavigateListener requestNavigateListener;

  public SectionMovieOrder(
      OnRequestNavigateListener requestNavigateListener
  ) {
    this.requestNavigateListener = requestNavigateListener;
    setLayout(new BorderLayout());

    drawLeft();
    drawRight();
  }

  private void drawRight() {
    Movie movie = SectionParams.selectedMovie;
    JPanel panelRight = new JPanel(null);
    panelRight.setBackground(Color.YELLOW);

    // title
    JLabel labelTitle = new JLabel(movie.getTitle());
    labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 24));
    labelTitle.setBounds(32, 32, 1280 - 240 - 128, 32);
    panelRight.add(labelTitle);

    // synopsis
    JLabel labelSynopsis = new JLabel("<html>" + movie.getSynopsis() + "</html>");
    labelSynopsis.setFont(new Font(labelSynopsis.getFont().getName(), Font.PLAIN, 16));
    labelSynopsis.setBounds(32, 64, 1280 - 240 - 128, 24 * 4);
    panelRight.add(labelSynopsis);

    this.add(panelRight, BorderLayout.CENTER);
  }

  private void drawLeft() {
    Movie movie = SectionParams.selectedMovie;

    // poster and rate
    JPanel panelLeft = new JPanel(null);
    panelLeft.setPreferredSize(new Dimension(240 + 64, 720 - 64));
    panelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
    panelLeft.setBackground(Color.RED);

    double posterWidth = 240;
    double posterHeight = posterWidth / 600 * 900;

    ImageIcon imageIcon = new ImageIcon(movie.getPosterPath());
    imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(
        (int) posterWidth, (int) posterHeight, Image.SCALE_SMOOTH
    ));
    JLabel labelPoster = new JLabel(imageIcon);
    labelPoster.setBounds(32, 32, (int) posterWidth, (int) posterHeight);
    panelLeft.add(labelPoster);

    this.add(panelLeft, BorderLayout.WEST);
  }
}
