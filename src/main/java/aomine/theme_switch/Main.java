package aomine.theme_switch;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {
  Main() {
    init();
  }

  private void init() {
    setTitle("Theme switch");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(1200, 700));
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new MigLayout("fill", "[center]", "[center]"));

    darkLighSwitchIcon = new SwitchIcon();
    jTBSwitch = new JToggleButton();

    darkLighSwitchIcon.setCenterSpace(20);
    jTBSwitch.setIcon(darkLighSwitchIcon);
    jTBSwitch.setCursor(new Cursor(Cursor.HAND_CURSOR));

    jTBSwitch.putClientProperty(FlatClientProperties.STYLE, 
      "arc: 999;" +
      "borderWidth: 0;" +
      "focusWidth: 0;" +
      "innerFocusWidth: 0"
    );

    jTBSwitch.addActionListener(new ActionListener() {
      private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
      private ScheduledFuture<?> scheduledFuture;
      
      @Override
      public void actionPerformed(ActionEvent evt) {
        if (scheduledFuture != null) {
          scheduledFuture.cancel(true);
        }

        scheduledFuture = scheduled.schedule(() -> {
          changeThemes(jTBSwitch.isSelected());
        }, 500, TimeUnit.MILLISECONDS);
      }
    });

    panel.add(jTBSwitch);
    add(panel);
  }

  public void changeThemes(boolean toDark) {
    if (FlatLaf.isLafDark() != toDark) {
      if (toDark) {
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
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    EventQueue.invokeLater(() -> new Main().setVisible(true));
  }

  private SwitchIcon darkLighSwitchIcon;
  private JToggleButton jTBSwitch;
}
