package aomine.login;

import javax.swing.*;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

import net.miginfocom.swing.MigLayout;

import javax.swing.event.DocumentEvent;
import java.awt.*;

public class PasswordStrengthStatus extends JPanel {
  private JPasswordField passwordField;
  private DocumentListener documetListener;
  private JLabel label;
  private int type;

  public PasswordStrengthStatus() {
    init();
  }

  private void init() {
    putClientProperty(FlatClientProperties.STYLE, "background:null");

    setLayout(new MigLayout("fill, insets 0", "3[100,fill,grow0][]", "[fill, grow 0]"));

    label = new JLabel("none");
    label.setVisible(false);
    add(new LabelStatus());
    add(label);
  }

  private Color getStrongColor(int type) {
    if (type == 1 ) return Color.decode("#ff4d4d");
    else if (type == 2) return Color.decode("#ff8040");
    else return Color.decode("#58c359");
  } 

  private void checkPassword(String password) {
    type = password.isEmpty() ? 0 : PasswordValidate.checkPasswordStrength(password);

    if (type == 0) {
      label.setText("none");
      label.setVisible(false);
    } else {
      label.setVisible(true);

      if (type == 1) label.setText("Too weak");
      else if (type == 2) label.setText("Medium");
      else label.setText("Strong");

      label.setForeground(getStrongColor(type));
    }

    repaint();
  }

  public void initPasswordField(JPasswordField txt) {
    if (documetListener == null) {
      documetListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
          checkPassword(String.valueOf(txt.getPassword()));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
          checkPassword(String.valueOf(txt.getPassword()));
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
          checkPassword(String.valueOf(txt.getPassword()));
        }
      };
    }

    if (passwordField != null) {
      passwordField.getDocument().removeDocumentListener(documetListener);
    }

    txt.getDocument().addDocumentListener(documetListener);

    passwordField = txt;
  }

  private class LabelStatus extends JLabel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int width = getWidth();
      int height = getHeight();
      int size = (int) (height* 0.3f);

      Graphics2D g2 = (Graphics2D) g.create();
      FlatUIUtils.setRenderingHints(g2);
      int gap = UIScale.scale(5);
      int w = (width - gap * 2) / 3;
      int h = (height - size) / 2;

      Color disableColor = Color.decode(FlatLaf.isLafDark() ? "#404040" : "#cecece");

      if (type >= 1) {
        g2.setColor(getStrongColor(type));
      } else {
        g2.setColor(disableColor);
      }

      FlatUIUtils.paintComponentBackground(g2, 0, h, w, size, 0, 999);

      if (type >= 2) {
        g2.setColor(getStrongColor(type));
      } else {
        g2.setColor(disableColor);
      }

      FlatUIUtils.paintComponentBackground(g2, (w + gap), h, w, size, 0, 999);

      if (type >= 3) {
        g2.setColor(getStrongColor(type));
      } else {
        g2.setColor(disableColor);
      }

      FlatUIUtils.paintComponentBackground(g2, (w + gap) * 2, h, w, size, 0, 999);

      g2.dispose();
    }
  }
}
