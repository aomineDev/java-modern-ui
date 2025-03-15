package aomine.glassPane;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import net.miginfocom.swing.MigLayout;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.popup.component.SimplePopupBorderOption;

public class App extends JFrame {
  public String testingAtribute;

  App() {
    init();
    GlassPanePopup.install(this);
  }

  public void init() {
    setTitle("glass pane");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1200, 700));
    setLocationRelativeTo(null);

    JPanel body = new JPanel(new MigLayout("fill", "[center]", "[center]"));

    JButton btnShowGlassPane = new JButton("Show Glass Pane");

    btnShowGlassPane.addActionListener(this::handleShowGlassPaeClick);

    body.add(btnShowGlassPane);

    setContentPane(body);
  }

  private void handleShowGlassPaeClick(ActionEvent evt) {
    // GlassPanePopup.showPopup(new Message(), new DefaultOption() {
    // @Override
    // public boolean closeWhenClickOutside() {
    // return true;
    // }
    // });

    DefaultOption option = new DefaultOption() {
      @Override
      public boolean closeWhenClickOutside() {
        return true;
      }
    };

    // JPanel panel = new JPanel();
    // panel.setPreferredSize(new Dimension(500, 300));
    String actions[] = { "cancel", "ok" };
    GlassPanePopup.showPopup(new SimplePopupBorder(new Message(), "Sample message",
        new SimplePopupBorderOption().useScroll(), actions, (controller, action) -> {
          if (action == 0) {
            System.out.println("cancel");

            GlassPanePopup.closePopup("message");
          } else if (action == 1) {
            System.out.println("ok");
          }
        }), option, "message");
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatMacDarkLaf.setup();
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    EventQueue.invokeLater(() -> new App().setVisible(true));
  }
}
