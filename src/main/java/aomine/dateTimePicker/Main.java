package aomine.dateTimePicker;

import javax.swing.*;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame {
  public Main() {
    setTitle("Date time picker");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1000, 500));
    setLocationRelativeTo(null);

    setDatePicker();
  }

  private void setDatePicker() {
    DatePicker datePicker = new DatePicker();

    datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
    datePicker.setSeparator("to date");
    datePicker.setUsePanelOption(true);
    datePicker.setDateSelectionAble(localDate -> !localDate.isAfter(LocalDate.now()));
    datePicker.addDateSelectionListener(new DateSelectionListener() {
      @Override
      public void dateSelected(DateEvent dateEvent) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // LocalDate[] dates = datePicker.getSelectedDateRange();
        // if (dates != null) System.out.println(df.format(dates[0]) + " - " +
        // df.format(dates[1]));

        LocalDate date = datePicker.getSelectedDate();
        if (datePicker.isDateSelected())
          System.out.println(df.format(date));
      }
    });

    // LocalDate date = LocalDate.of(2024, 6, 17);
    // datePicker.setSelectedDate(date);

    JFormattedTextField editor = new JFormattedTextField();
    datePicker.setEditor(editor);
    setLayout(new MigLayout());
    add(editor, "w 250");
  }

  public static void main(String[] args) {
    FlatMacDarkLaf.setup();
    EventQueue.invokeLater(() -> new Main().setVisible(true));
  }
}
