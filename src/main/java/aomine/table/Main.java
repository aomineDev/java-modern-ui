package aomine.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {
  Main() {
    init();
    applyTableStyle(jTList);
    testData(jTList);
  }

  private void init() {
    setTitle("Tables");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1000, 700));
    setLocationRelativeTo(null);

    jBAdd = new JButton("Add new");
    jBEdit = new JButton("Edit");
    jBDelete = new JButton("Delete");
    jTFSearch = new JTextField();
    jTList = new JTable();
    JButton jBChangeTheme = new JButton("change theme");
    jBChangeTheme.addActionListener(this::handleThemeClick);

    JPanel body = new JPanel(new MigLayout("wrap, fill, insets 20", "[]", "[grow 0][fill]"));

    JPanel panel = new JPanel(new MigLayout("wrap,fill,insets 15", "[fill]", "[grow 0][fill]"));

    JPanel btnContainer = new JPanel(new MigLayout("", "[]push[][][]"));

    panel.putClientProperty(FlatClientProperties.STYLE, "background:$Table.background;" + 
    "[light]border:0,0,0,0,shade(@background,5%),,20;" +
    "[dark]border:0,0,0,0,tint(@background,5%),,20");

    btnContainer.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

    applyTableProperties();

    setModel();

    btnContainer.add(jTFSearch, "w 200");
    btnContainer.add(jBAdd);
    btnContainer.add(jBEdit);
    btnContainer.add(jBDelete);
    panel.add(btnContainer);
    panel.add(new JScrollPane(jTList));
    body.add(jBChangeTheme, "right");
    body.add(panel, "w 100%, gapy 10");
    add(body);
  }

  private void testData(JTable table) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    String[] names = {"Beer", "Shirt", "Laptop", "Book", "Coffee", "Phone", "Chair", "Watch", "Sunglases", "Bag", "Headphones", "Camera", "Necklace", "Perfume", "Wallet", "Jacket"};

    for(String n: names) {
      model.addRow(getRandomRow(n));
    }
  }

  private void setModel() {
    String[] columns = {
      "",
      "Name",
      "Balance",
      "Other",
      "Status"
    };

    DefaultTableModel model = new DefaultTableModel(null, columns) {
      Class[] types = new Class[] {Boolean.class, Object.class, Object.class, Object.class, Object.class};

      boolean[] canEdit = {true, false, false, false, false};

      @Override
      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    };
    
    jTList.setModel(model);
    jTList.getColumn(0).setPreferredWidth(30);
    jTList.getColumn(1).setPreferredWidth(200);
  }

  private Object[] getRandomRow(String name) {
    DecimalFormat df = new DecimalFormat("#,##0.##");
    return new Object[] {false, name, "$" + df.format(getAmount(999, 999999)), "$" + df.format(getAmount(999, 999999)), df.format(getAmount(-100, 100))};
  }

  private double getAmount(int from, int to) {
    Random ran = new Random();
    return (ran.nextInt(to - from) + from) * ran.nextDouble();
  }

  private void applyTableStyle(JTable table) {
    // Add icons
    jBAdd.setIcon(new FlatSVGIcon("aomine/icons/table/add.svg", 0.35f));
    jBEdit.setIcon(new FlatSVGIcon("aomine/icons/table/edit.svg", 0.35f));
    jBDelete.setIcon(new FlatSVGIcon("aomine/icons/table/delete.svg", 0.35f));
    jTFSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("aomine/icons/table/search.svg", 0.35f));

    // Change scroll style
    JScrollPane scroll = (JScrollPane) table.getParent().getParent();
    scroll.setBorder(BorderFactory.createEmptyBorder());
    scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, 
      "background: $Table.background;" + 
      "track: $Table.background;" +
      "trackArc: 999"
    );

    // Add table style class
    JTableHeader tableHeader = table.getTableHeader();

    tableHeader.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

    // To Create Table alignment
    tableHeader.setDefaultRenderer(getAlignmentCellRenderer(tableHeader.getDefaultRenderer(), true));
    table.setDefaultRenderer(Object.class, getAlignmentCellRenderer(table.getDefaultRenderer(Object.class), false));
  }

  private void applyTableProperties() {
    jTFSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");

    jTFSearch.putClientProperty(FlatClientProperties.STYLE, "background:@background");
    
    jBAdd.putClientProperty(FlatClientProperties.STYLE, "" +
      "background:lighten(@background,8%);" + 
      "borderWidth:1"
    );
    jBEdit.putClientProperty(FlatClientProperties.STYLE, "" +
      "background:lighten(@background,8%);" + 
      "borderWidth:1"
    );
    jBDelete.putClientProperty(FlatClientProperties.STYLE, "" +
      "background:lighten(@background,8%);" + 
      "borderWidth:1"
    );
  }

  private TableCellRenderer getAlignmentCellRenderer(TableCellRenderer oldRender, boolean header) {
    return new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (com instanceof JLabel) {
          JLabel label = (JLabel) com;
          if (column == 0 || column == 4) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
          } else if (column == 2 || column == 3) {
            label.setHorizontalAlignment(SwingConstants.TRAILING);
          } else {
            label.setHorizontalAlignment(SwingConstants.LEADING);
          }
          
          if (!header) {
            if (column == 4) {
              if (Double.parseDouble(value.toString()) > 0) {
                com.setForeground(new Color(17, 182, 60));
                label.setText("+" + value);
              } else {
                com.setForeground(new Color(202, 48, 48));
              }
            } else {
              if (isSelected) {
                com.setForeground(table.getSelectionForeground());
              } else {
                com.setForeground(table.getForeground());
              }
            }
          }
        }

        return com;
      }
    };
  }

  public void handleThemeClick (ActionEvent evt) {
    if (!FlatLaf.isLafDark()) {
      EventQueue.invokeLater(() -> {
        FlatAnimatedLafChange.showSnapshot();
        FlatMacDarkLaf.setup();
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
      });
    } else {
      EventQueue.invokeLater(() -> {
        FlatAnimatedLafChange.showSnapshot();
        FlatMacLightLaf.setup();
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
      });
    }
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("aomine.themes.table");
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

    FlatMacDarkLaf.setup();

    EventQueue.invokeLater(() -> new Main().setVisible(true));
  }

  private JButton jBAdd;
  private JButton jBEdit;
  private JButton jBDelete;
  private JTextField jTFSearch;
  private JTable jTList;
}
