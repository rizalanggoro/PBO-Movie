package presentation.sections;

import core.AppFont;
import core.Constants;
import core.Utils;
import domain.models.Movie;
import presentation.OnRequestNavigateListener;
import presentation.SectionParams;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SectionMovieOrder extends JPanel {
  public static final String name = "movieOrder";
  private final OnRequestNavigateListener requestNavigateListener;

  private final int panelLeftWidth = 304;
  private final int panelRightWidth = 304;
  private final int panelCenterWidth = 1280 - panelLeftWidth - panelRightWidth;

  // variables
  private final ArrayList<String> arrayListSelectedSeat = new ArrayList<>();

  // components
  // -> panel right
  JLabel labelDayRight;
  JLabel labelSeatRight;
  JLabel labelPriceRight;

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
    JPanel container = new JPanel(null);
    container.setBackground(new Color(0xff0f172a));

    // title
    JLabel label = new JLabel("Pesanan");
    label.setForeground(Constants.Colors.slate100);
    label.setFont(new Font(
        label.getFont().getName(), Font.BOLD, 18
    ));
    label.setBounds(32, 32, this.panelRightWidth, label.getFont().getSize() + 8);
    container.add(label);

    // day
    this.labelDayRight = new JLabel("Hari: -");
    this.labelDayRight.setForeground(Constants.Colors.slate500);
    this.labelDayRight.setFont(new Font(
        this.labelDayRight.getFont().getName(), Font.PLAIN, 16
    ));
    this.labelDayRight.setBounds(
        32,
        label.getY() + label.getHeight() + 16,
        this.panelRightWidth,
        this.labelDayRight.getFont().getSize() + 8
    );
    container.add(this.labelDayRight);

    // seat
    this.labelSeatRight = new JLabel("Jumlah kursi: " + this.arrayListSelectedSeat.size());
    this.labelSeatRight.setForeground(Constants.Colors.slate500);
    this.labelSeatRight.setFont(new Font(
        this.labelSeatRight.getFont().getName(), Font.PLAIN, 16
    ));
    this.labelSeatRight.setBounds(
        32,
        this.labelDayRight.getY() + this.labelDayRight.getHeight() + 4,
        this.panelRightWidth,
        this.labelSeatRight.getFont().getSize() + 8
    );
    container.add(this.labelSeatRight);

    // total price
    this.labelPriceRight = new JLabel("Total: -");
    this.labelPriceRight.setForeground(Constants.Colors.slate500);
    AppFont.setNormal(this.labelPriceRight, 16);
    this.labelPriceRight.setBounds(
        32,
        this.labelSeatRight.getY() + this.labelSeatRight.getHeight() + 4,
        this.panelRightWidth,
        this.labelPriceRight.getFont().getSize() + 8
    );
    container.add(this.labelPriceRight);

    // button pay
    JButton buttonPay = new JButton("Bayar");
    buttonPay.setBounds(
        32,
        this.labelPriceRight.getY() + this.labelPriceRight.getHeight() + 32,
        this.panelRightWidth - 64,
        32
    );
    container.add(buttonPay);

    container.setPreferredSize(new Dimension(this.panelRightWidth, -1));
    this.add(container, BorderLayout.EAST);
  }

  private void drawCenter() {
    Movie movie = SectionParams.selectedMovie;

    JPanel container = new JPanel(null);
    container.setBackground(new Color(0xff0f172a));

    // title
    JLabel labelTitle = new JLabel(movie.getTitle());
    labelTitle.setForeground(Constants.Colors.slate100);
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
    labelSynopsis.setForeground(Constants.Colors.slate500);
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
    labelSelectDay.setForeground(Constants.Colors.slate100);
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

    JPanel panelDays = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelDays.setBorder(null);
    panelDays.setOpaque(false);
    ButtonGroup buttonGroupDays = new ButtonGroup();
    for (int a = 0; a < Constants.days.length; a++) {
      String day = Constants.days[a];

      JRadioButton radioButtonDay = new JRadioButton(day);
      radioButtonDay.setForeground(Constants.Colors.slate500);
      radioButtonDay.setOpaque(true);
      radioButtonDay.setFont(new Font(
          radioButtonDay.getFont().getName(),
          Font.PLAIN,
          14
      ));
      radioButtonDay.addActionListener(e -> {
        this.labelDayRight.setText("Hari: " + day);
      });
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

    // select seat
    JLabel labelSelectSeat = new JLabel("Pilih kursi:");
    labelSelectSeat.setForeground(Constants.Colors.slate100);
    labelSelectSeat.setFont(
        new Font(labelSelectSeat.getFont().getName(), Font.PLAIN, 16)
    );
    labelSelectSeat.setBounds(
        32,
        panelDays.getY() + panelDays.getHeight() + 16,
        this.panelCenterWidth - 64 - 32,
        labelSelectSeat.getFont().getSize() + 8
    );
    container.add(labelSelectSeat);

    JPanel panelSeats = new JPanel(new GridLayout(8, 8, 2, 2));
    panelSeats.setOpaque(false);
    panelSeats.setBounds(
        32,
        labelSelectSeat.getY() + labelSelectSeat.getHeight() + 8,
        320, 320
    );
    String chair = "ABCDEFGH";
    for (int a = 0; a < 8; a++) {
      for (int b = 0; b < 8; b++) {
        final String text = chair.substring(a, a + 1) + (b + 1);

        JToggleButton toggleButton = new JToggleButton(text);
        toggleButton.setBorder(null);
        Color defaultForeground = toggleButton.getForeground();

        toggleButton.addItemListener(e -> {
          if (toggleButton.isSelected()) {
            toggleButton.setForeground(Color.RED);
            this.arrayListSelectedSeat.add(text);
          } else {
            toggleButton.setForeground(defaultForeground);
            this.arrayListSelectedSeat.remove(text);
          }

          this.labelSeatRight.setText("Jumlah kursi: " + this.arrayListSelectedSeat.size());
          this.labelPriceRight.setText(
              "Total: Rp " + Utils.Format.decimal(
                  movie.getPrice() * this.arrayListSelectedSeat.size()
              )
          );
        });
        panelSeats.add(toggleButton);
      }
    }
    container.add(panelSeats);

    container.setPreferredSize(
        new Dimension(
            -1,
            (int) (panelSeats.getBounds().getY() + panelSeats.getBounds().getHeight()) + 32
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
    panelLeft.setBackground(new Color(0xff020617));

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
