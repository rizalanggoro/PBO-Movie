package presentation;

import data.sources.SourceMovie;
import domain.models.Movie;

import javax.swing.*;
import java.awt.*;

public class SectionHome extends JPanel {
  public static final String name = "home";
  private final OnRequestNavigateListener requestNavigateListener;

  public SectionHome(
      OnRequestNavigateListener requestNavigateListener
  ) {
    this.requestNavigateListener = requestNavigateListener;

    setLayout(new BorderLayout());
    setBackground(new Color(0xff0f172a));

    double posterWidth = (double) (1280 - 64 - (3 * 8)) / 4;
    double posterHeight = posterWidth / 600 * 900;

    JPanel panelMovie = new JPanel(new GridLayout(3, 4, 8, 8));
    panelMovie.setPreferredSize(new Dimension(1280 - 64, (int) ((3 * (posterHeight + 56)) + (2 * 8))));
    panelMovie.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
    panelMovie.setBackground(new Color(0xff0f172a));

    for (Movie movie : SourceMovie.list) {
      JPanel panelMovieItem = new JPanel(new BorderLayout());
      panelMovieItem.setOpaque(false);
      panelMovieItem.setBorder(
          BorderFactory.createLineBorder(
              new Color(0xff334155), 2
          )
      );

      // poster
      ImageIcon imageIcon = new ImageIcon(movie.getPosterPath());
      imageIcon = new ImageIcon(
          imageIcon.getImage().getScaledInstance(
              (int) posterWidth, (int) posterHeight, Image.SCALE_SMOOTH
          )
      );
      JLabel labelPoster = new JLabel(imageIcon);
      panelMovieItem.add(labelPoster, BorderLayout.CENTER);

      // details
      JPanel panelDetails = new JPanel();
      panelDetails.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
      panelDetails.setBackground(new Color(0xff1e293b));
      // panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.Y_AXIS));
      panelDetails.setLayout(new GridBagLayout());

      // details: constrains
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.weightx = 1;

      // details: title
      JLabel labelTitle = new JLabel(movie.getTitle());
      labelTitle.setForeground(new Color(0xfff8fafc));
      labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 18));
      constraints.gridy = 0;
      panelDetails.add(labelTitle, constraints);

      // details: synopsis
      JLabel labelSynopsis = new JLabel(movie.getSynopsis());
      labelSynopsis.setForeground(new Color(0xff64748b));
      labelSynopsis.setFont(new Font(labelSynopsis.getFont().getName(), Font.PLAIN, 16));
      labelSynopsis.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
      constraints.gridy = 1;
      panelDetails.add(labelSynopsis, constraints);

      // details: button order
      JButton buttonOrder = new JButton("Pesan");
      buttonOrder.setSize(new Dimension((int) posterWidth, 24));
      buttonOrder.setOpaque(false);
      buttonOrder.setBackground(new Color(0xff334155));
      buttonOrder.setForeground(new Color(0xfff8fafc));
      buttonOrder.addActionListener(e -> {
        SectionParams.selectedMovie = movie;
        this.requestNavigateListener.request(SectionMovieOrder.name);
      });
      constraints.gridy = 3;
      panelDetails.add(buttonOrder, constraints);

      panelMovieItem.add(panelDetails, BorderLayout.SOUTH);
      panelMovie.add(panelMovieItem);
    }

    JScrollPane scrollPane = new JScrollPane(panelMovie);
    scrollPane.setBackground(new Color(0xff0f172a));
    this.add(scrollPane);
  }
}
