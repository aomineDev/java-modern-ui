package aomine.alerts;

import javax.swing.*;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;

import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    Main() {
      setTitle("Alerts");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(new Dimension(800, 600));
      setLocationRelativeTo(null);

      setLayout(new MigLayout());

      JButton btnAlertOne = new JButton("Alert 1");
      btnAlertOne.addActionListener(this::handleAlertOneClick);
      add(btnAlertOne); 

      GlassPanePopup.install(this);
    }

    public void handleAlertOneClick(ActionEvent evt) {
      MessageAlerts.getInstance().showMessage("title", "description", MessageAlerts.MessageType.WARNING, MessageAlerts.DEFAULT_OPTION, (pc, i) -> {
        if (i == MessageAlerts.OK_OPTION) System.out.println("ok click");
      });
    }

    public static void main(String[] args) {
      FlatMacDarkLaf.setup();
      EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
