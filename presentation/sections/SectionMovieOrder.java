package presentation.sections;

import domain.models.Movie;
import presentation.OnRequestNavigateListener;
import presentation.SectionParams;

import javax.swing.*;
import java.awt.*;

public class SectionMovieOrder extends JPanel {
  public static final String name = "movieOrder";
  private final OnRequestNavigateListener requestNavigateListener;

  private final int panelLeftWidth = 304;
  private final int panelRightWidth = 304;
  private final int panelCenterWidth = 1280 - panelLeftWidth - panelRightWidth;

  public SectionMovieOrder(
      OnRequestNavigateListener requestNavigateListener
  ) {
    this.requestNavigateListener = requestNavigateListener;
    setLayout(new BorderLayout());

    drawLeft();
    drawCenter();
    drawRight();
  }

  private void drawRight() {
    JPanel container = new JPanel();
    container.setBackground(Color.BLUE);

    container.setPreferredSize(new Dimension(this.panelRightWidth, -1));
    this.add(container, BorderLayout.EAST);
  }

  private void drawCenter() {
    Movie movie = SectionParams.selectedMovie;

    JPanel container = new JPanel(null);
    container.setBackground(Color.YELLOW);

    // title
    JLabel labelTitle = new JLabel(movie.getTitle());
    labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 24));
    labelTitle.setBounds(
        32,
        32,
        this.panelCenterWidth - 64 - 32,
        labelTitle.getFont().getSize() + 8
    );
    container.add(labelTitle);

    // synopsis
    JLabel labelSynopsis = new JLabel("<html>" + movie.getSynopsis() + "</html>");
    labelSynopsis.setFont(new Font(labelSynopsis.getFont().getName(), Font.PLAIN, 16));
    labelSynopsis.setBounds(
        32,
        (int) (labelTitle.getBounds().getY() + labelTitle.getBounds().getHeight()),
        this.panelCenterWidth - 64 - 32,
        (labelSynopsis.getFont().getSize() + 8) * 6
    );
    container.add(labelSynopsis);

    // select day
    JLabel labelSelectDay = new JLabel("Pilih hari:");
    labelSelectDay.setFont(
        new Font(labelSelectDay.getFont().getName(), Font.PLAIN, 16)
    );
    labelSelectDay.setBounds(
        32,
        (int) (labelSynopsis.getBounds().getY() + labelSynopsis.getBounds().getHeight()) + 16,
        this.panelCenterWidth - 64 - 32,
        labelSelectDay.getFont().getSize() + 8
    );
    container.add(labelSelectDay);

    String[] days = {
        "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
    };
    JPanel panelDays = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelDays.setOpaque(false);
    ButtonGroup buttonGroupDays = new ButtonGroup();
    for (String day : days) {
      JRadioButton radioButtonDay = new JRadioButton(day);
      radioButtonDay.setOpaque(false);
      radioButtonDay.setFont(new Font(
          radioButtonDay.getFont().getName(),
          Font.PLAIN,
          radioButtonDay.getFont().getSize()
      ));
      buttonGroupDays.add(radioButtonDay);
      panelDays.add(radioButtonDay);
    }
    panelDays.setBounds(
        32,
        (int) (labelSelectDay.getBounds().getY() + labelSelectDay.getBounds().getHeight()) + 8,
        this.panelCenterWidth - 64 - 32,
        32
    );
    container.add(panelDays);

    // select chair
    JLabel labelSelectChair = new JLabel("Pilih kursi:");
    labelSelectChair.setFont(
        new Font(labelSelectChair.getFont().getName(), Font.PLAIN, 16)
    );
    labelSelectChair.setBounds(
        32,
        panelDays.getY() + panelDays.getHeight() + 16,
        this.panelCenterWidth - 64 - 32,
        labelSelectChair.getFont().getSize() + 8
    );
    container.add(labelSelectChair);

    JPanel panelSelectChair = new JPanel(new GridLayout(8, 8, 2, 2));
    panelSelectChair.setOpaque(false);
    panelSelectChair.setBounds(
        32,
        labelSelectChair.getY() + labelSelectChair.getHeight() + 8,
        320, 320
    );
    String chair = "ABCDEFGH";
    for (int a = 0; a < 8; a++) {
      for (int b = 0; b < 8; b++) {
        final String text = chair.substring(a, a + 1) + (b + 1);
        JToggleButton toggleButton = new JToggleButton(text);
        toggleButton.setBorder(null);

        toggleButton.addItemListener(e -> {
          if (toggleButton.isSelected()) {
            toggleButton.setForeground(Color.RED);
          } else {
            toggleButton.setBackground(Color.BLUE);
          }
        });
        panelSelectChair.add(toggleButton);
      }
    }
    container.add(panelSelectChair);

    container.setPreferredSize(
        new Dimension(
            -1,
            (int) (panelSelectChair.getBounds().getY() + panelSelectChair.getBounds().getHeight()) + 32
        )
    );
    JScrollPane scrollPane = new JScrollPane(container);
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(16, 0));
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    this.add(scrollPane, BorderLayout.CENTER);
  }

  private void drawLeft() {
    Movie movie = SectionParams.selectedMovie;

    // poster and rate
    JPanel panelLeft = new JPanel(null);
    panelLeft.setPreferredSize(new Dimension(this.panelLeftWidth, 720 - 64));
    panelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
    panelLeft.setBackground(Color.RED);

    double posterWidth = this.panelLeftWidth - 64;
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
