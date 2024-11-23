package aomine.login;

import javax.swing.JComponent;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import java.awt.EventQueue;


public class FormsManager {
  private App app;
  private static FormsManager instance;

  private FormsManager() {}

  public static FormsManager getInstance() {
    if (instance == null) instance = new FormsManager();

    return instance;
  }

  public void initApp(App app) {
    this.app = app;
  }

  public void showForm(JComponent form) {
    EventQueue.invokeLater(() -> {
      FlatAnimatedLafChange.showSnapshot();
      app.setContentPane(form);
      app.revalidate();
      app.repaint();
      FlatAnimatedLafChange.hideSnapshotWithAnimation();
    });
  }
}
