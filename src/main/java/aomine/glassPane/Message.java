package aomine.glassPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import raven.popup.GlassPanePopup;
import raven.popup.component.GlassPaneChild;
import raven.popup.component.PopupController;
import raven.popup.component.SimplePopupBorder;

public class Message extends GlassPaneChild {
  public Message() {
    init();
  }

  private void init() {
    setLayout(new MigLayout("fill,wrap"));
    JLabel title = new JLabel("Title");
    JButton btn = new JButton("Close");

    btn.addActionListener(this::handleClosePopupClick);

    add(title);
    add(btn);
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
    add(new JButton("Close"));
  }

  private void handleClosePopupClick(ActionEvent evt) {
    GlassPanePopup.closePopup("message");
  }

}
