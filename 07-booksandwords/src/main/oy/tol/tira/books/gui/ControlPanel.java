package oy.tol.tira.books.gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import java.io.File;

public class ControlPanel extends JPanel implements ActionListener {

   private BooksAndWordsGUI gui = null;
   private JButton selectBookFileButton = null;
   private JButton selectIgnoreFileButton = null;
   private JButton startCountingButton = null;
   private File currentPath = null;

   ControlPanel(BooksAndWordsGUI gui) {
      assert(null != gui);
      this.gui = gui;
      selectBookFileButton = new JButton("Select Book File...");
      selectBookFileButton.setActionCommand("select_book");
      selectBookFileButton.addActionListener(this);
      add(selectBookFileButton);
      selectIgnoreFileButton = new JButton("Select Ignore File...");
      selectIgnoreFileButton.setActionCommand("select_ignore");
      selectIgnoreFileButton.addActionListener(this);
      add(selectIgnoreFileButton);
      startCountingButton = new JButton("Start!");
      startCountingButton.setActionCommand("start");
      startCountingButton.addActionListener(this);
      startCountingButton.setEnabled(false);
      add(startCountingButton);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      if ("select_book".equals(command)) {
         File file = selectFile();
         if (null != file) {
            selectBookFileButton.setText(file.getName());
            gui.setCurrentBookFile(file.getAbsolutePath());
         } else {
            JOptionPane.showMessageDialog(this, "Something went wrong.", "Could not select file",
                  JOptionPane.ERROR_MESSAGE);
         }
      } else if ("select_ignore".equals(command)) {
         File file = selectFile();
         if (null != file) {
            selectIgnoreFileButton.setText(file.getName());
            gui.setCurrentIgnoreFile(file.getAbsolutePath());
         } else {
            JOptionPane.showMessageDialog(this, "Something went wrong.", "Could not select file",
                  JOptionPane.ERROR_MESSAGE);
         }
      }else if ("start".equals(command)) {
         gui.start();
      }
      if (gui.getCurrentBookFile() != null && gui.getCurrentIgnoreFile() != null) {
         startCountingButton.setEnabled(true);
      }
   }

   private File selectFile() {
      File selected = null;
      JFileChooser fileChooser = new JFileChooser();
      if (gui.getCurrentBookFile() == null) {
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      } else {
         fileChooser.setCurrentDirectory(currentPath);
      }
      int result = fileChooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
         selected = fileChooser.getSelectedFile();
         currentPath = new File(selected.getParent());
         System.out.println("Selected file: " + selected.getName());
      }
      return selected;
   }

   @Override
   public Dimension getPreferredSize() {
       return new Dimension(Integer.MAX_VALUE, 20);
   }
}
