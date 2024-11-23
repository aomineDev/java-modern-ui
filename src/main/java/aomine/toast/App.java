package aomine.toast;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class App extends JFrame {
  App() {
    init();
    Notifications.getInstance().setJFrame(this);
  }

  private void init() {
    setTitle("Toast Notifications");
    setSize(new Dimension(1200, 700));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    
    JPanel panel = new JPanel(new MigLayout("fill", "[center]", "[center]"));
    JButton btnShowToast = new JButton("Show Toast");
    btnShowToast.addActionListener(this::handleShowToaskClick);

    panel.add(btnShowToast);
    setContentPane(panel);
  }

  private void handleShowToaskClick(ActionEvent event) {
    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Hello");
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatMacDarkLaf.setup();
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    EventQueue.invokeLater(() -> new App().setVisible(true));
  }
}
