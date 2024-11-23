package aomine.dragAndDrop;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.dnd.DropTarget;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;

public class App extends JFrame {
  public App() {
    init();

    conectToDragDrop();
  }

  private void init() {
    setTitle("Drag and Drop");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1200, 700));
    setLocationRelativeTo(null);

    setLayout(new MigLayout());

    panel = new JPanel();
    lblImage = new JLabel();
    lblImageName = new JLabel("name");

    panel.setLayout(new MigLayout("debug, wrap", "[center]"));

    // lblImage.setPreferredSize(new Dimension(500, 500));

    panel.add(lblImage, "h 500, w 500");
    panel.add(lblImageName, "");

    setContentPane(panel);
  }

  private void conectToDragDrop() {
    DragListener d = new DragListener(lblImage, lblImageName);

    new DropTarget(this, d);
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    FlatRobotoFont.install();
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    EventQueue.invokeLater(() -> new App().setVisible(true));
  }

  private JPanel panel;
  private JLabel lblImage;
  private JLabel lblImageName;
}
