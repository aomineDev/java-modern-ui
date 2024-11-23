package aomine.fileChooser;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Image;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import jnafilechooser.api.JnaFileChooser;
import net.miginfocom.swing.MigLayout;

public class App extends JFrame {
  App() {
    init();
  }  

  public void init() {
    setTitle("File Chooser");
    setSize(new Dimension(1200, 700));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setLayout(new MigLayout("flowy,debug", "[grow 0]", "[]"));

    JButton btn = new JButton("Choose File");
    btn.addActionListener(this::handleChooseFileClick);
    
    img = new JLabel();
    img.setSize(new Dimension(200, 200));
    img.setPreferredSize(new Dimension(200, 200));
    
    img.setIcon(getImageIcon("cat.jpg"));
    add(btn);
    add(img);
  }

  private void handleChooseFileClick(ActionEvent evt) {
    // JFileChooser fc = new JFileChooser();
    // fc.showOpenDialog(this);

    JnaFileChooser fc = new JnaFileChooser();
    fc.setTitle("xdddddddd");
    fc.addFilter("All Files", "*");
    fc.addFilter("Pictures", "jpg", "jpeg", "png", "gif", "bmp");

    // boolean action = fc.showSaveDialog(this);
    boolean action = fc.showOpenDialog(this);

    if(action) {
      File selectedFile = fc.getSelectedFile();
      // preview
      // try {
      //   img.setIcon(new ImageIcon(ImageIO.read(selectedFile)));
      // } catch (Exception e) {
      //   e.printStackTrace();
      // }
      String fileName = selectedFile.getName().replace(" ", "_");

      Path destinationPath = Paths.get("uploads/" + fileName);

      try {
        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        img.setIcon(getImageIcon(fileName));
      } catch (IOException  e) {
        e.printStackTrace();
      }
    }
  }

  private ImageIcon getImageIcon(String name) {
    File imgPath = new File("uploads/" + name);
    ImageIcon imageIcon = new ImageIcon(imgPath.getAbsolutePath());
    Image image = imageIcon.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);

    return new ImageIcon(image);
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatMacLightLaf.setup();

    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    EventQueue.invokeLater(() -> new App().setVisible(true));
  }

  private JLabel img;
}
