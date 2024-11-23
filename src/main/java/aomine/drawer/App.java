package aomine.drawer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

public class App extends JFrame {
  App () {
    GlassPanePopup.install(this);
    MyDrawerBuilder drawerBuilder = new MyDrawerBuilder();
    Drawer.getInstance().setDrawerBuilder(drawerBuilder);
    init();
  }

  private void init() {
    setTitle("Drawer");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1200, 700));
    setLocationRelativeTo(null);

    
    JPanel panel = new JPanel(new MigLayout("insets 20"));
    JButton jBDrwer = new JButton("Drawer");
    jBDrwer.addActionListener(this::handleButtonClick);
    panel.add(jBDrwer);
    add(panel);
  }

  public void handleButtonClick(ActionEvent evt) {
    Drawer.getInstance().showDrawer();
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("aomine.themes.drawer");
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    FlatMacDarkLaf.setup();
    EventQueue.invokeLater(() -> new App().setVisible(true));
  }
}
