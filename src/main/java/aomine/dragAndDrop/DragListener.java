package aomine.dragAndDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.util.List;

public class DragListener implements DropTargetListener {
  private JLabel lblImage;
  private JLabel lblImageName;

  public DragListener(JLabel lblImage, JLabel lblImageName) {
    this.lblImage = lblImage;
    this.lblImageName = lblImageName;
  }

  @Override
  public void dragEnter(DropTargetDragEvent dtde) {
  }

  @Override
  public void dragOver(DropTargetDragEvent dtde) {
  }

  @Override
  public void dropActionChanged(DropTargetDragEvent dtde) {
  }

  @Override
  public void dragExit(DropTargetEvent dte) {
  }

  @Override
  public void drop(DropTargetDropEvent evt) {
    evt.acceptDrop(DnDConstants.ACTION_COPY);

    Transferable t = evt.getTransferable();

    DataFlavor[] df = t.getTransferDataFlavors();

    for (DataFlavor f: df) {
      try {
        if (f.isFlavorJavaFileListType()) {
          List<File> files = (List<File>) t.getTransferData(f);

          for (File file: files) {
            displayImage(file.getPath());
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void displayImage(String path) {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new File(path)); 
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    ImageIcon icon = new ImageIcon(img);
    lblImage.setIcon(icon);
    lblImageName.setText(path);
  }
}
