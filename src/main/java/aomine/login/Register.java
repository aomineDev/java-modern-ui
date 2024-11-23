package aomine.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class Register extends JPanel {
  public Register() {
    init();
  }

  private void init() {
    setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

    jTFFirstName = new JTextField();
    jTFLastame = new JTextField();
    jTFUsername = new JTextField();
    jPFPassword = new JPasswordField();
    jPFConfirmPassword = new JPasswordField();
    jBRegister = new JButton("Sign Up");
    passwordStrengthStatus = new PasswordStrengthStatus();

    JPanel panel = new JPanel(new MigLayout("wrap, fill, insets 35 45 30 45", "[fill, 360]"));
    panel.putClientProperty(FlatClientProperties.STYLE, 
      "arc:20;" +
      "[light]background:darken(@background,3%);" +
      "[dark]background:lighten(@background,3%)"
    );

    jTFFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");
    jTFLastame.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last name");
    jTFUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
    jPFPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
    jPFConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Re-enter your password");

    jPFPassword.putClientProperty(FlatClientProperties.STYLE,
      "showRevealButton: true"
    );
    jPFConfirmPassword.putClientProperty(FlatClientProperties.STYLE,
      "showRevealButton: true"
    );

    jBRegister.putClientProperty(FlatClientProperties.STYLE,
      "[light]background:darken(@background,10%);" +
      "[dark]background:lighten(@background,10%);" +
      "borderWidth:0;" +
      "focusWidth:0;" +
      "innerFocusWidth:0"
    );

    JLabel jLTitle = new JLabel("Welcome to our Chat Application");
    JLabel jLDescription = new JLabel("Join us to chat, connect, and make new friends, Sign up now ad start chattign!");
    jLTitle.putClientProperty(FlatClientProperties.STYLE_CLASS, "title");
    jLDescription.putClientProperty(FlatClientProperties.STYLE_CLASS, "description");

    passwordStrengthStatus.initPasswordField(jPFPassword);

    panel.add(jLTitle);
    panel.add(jLDescription);
    panel.add(new JLabel("Full Name"), "gapy 10");
    panel.add(jTFFirstName, "split 2");
    panel.add(jTFLastame);
    panel.add(new JLabel("Gender"), "gapy 8");
    panel.add(createGenderPanel());
    panel.add(new JSeparator(), "gapy 5 5");
    panel.add(new JLabel("Username or Email"));
    panel.add(jTFUsername);
    panel.add(new JLabel("Password"), "gapy 8");
    panel.add(jPFPassword);
    panel.add(passwordStrengthStatus, "gapy 8");
    panel.add(new JLabel("Confirm Password"), "gapy 8");
    panel.add(jPFConfirmPassword);
    panel.add(jBRegister, "gapy 20");
    panel.add(createLoginLabel(), "gapy 10");

    add(panel);
  }

  private Component createGenderPanel() {
    JPanel panel = new JPanel(new MigLayout("insets 0"));
    panel.putClientProperty(FlatClientProperties.STYLE, 
      "background: null"
    );

    jRBMale = new JRadioButton("Male");
    jRBFemale = new JRadioButton("Female");
    jBGGender = new ButtonGroup();

    jBGGender.add(jRBMale);
    jBGGender.add(jRBFemale);

    jRBMale.setSelected(true);

    panel.add(jRBMale);
    panel.add(jRBFemale);

    return panel;
  }

  private Component createLoginLabel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel.putClientProperty(FlatClientProperties.STYLE, "" +
      "background:null"
    );
        
    JButton jBLogin = new JButton("<html><a href='#'>Sign in here</a></html>");
    jBLogin.putClientProperty(FlatClientProperties.STYLE, "" +
      "border:3,3,3,3"
    );
    jBLogin.setContentAreaFilled(false);
    jBLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
    jBLogin.addActionListener(e -> {
      FormsManager.getInstance().showForm(new Login());
    });
        
    JLabel label = new JLabel("Already have an acoount? ");
    label.putClientProperty(FlatClientProperties.STYLE, "" +
      "[light]foreground:lighten(@foreground,30%);" +
      "[dark]foreground:darken(@foreground,30%)"
    );
    
    panel.add(label);
    panel.add(jBLogin);
    
    return panel;
  }

  private JTextField jTFFirstName;
  private JTextField jTFLastame;
  private JRadioButton jRBMale;
  private JRadioButton jRBFemale;
  private JTextField jTFUsername;
  private JPasswordField jPFPassword;
  private JPasswordField jPFConfirmPassword;
  private ButtonGroup jBGGender;
  private JButton jBRegister;
  private PasswordStrengthStatus passwordStrengthStatus;
}
