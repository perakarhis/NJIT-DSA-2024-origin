package oy.tol.tira.books.gui;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.GridLayout;

import oy.tol.tira.books.Book;
import oy.tol.tira.books.BookFactory;

// TEACHERS: TODO:  WORK IN PROGRESS
public class BooksAndWordsGUI implements Book {

   private Book bookImplementation = null;
   private String currentBookFile = null;
   private String currentIgnoreFile = null;

   private ControlPanel controlPanel = null;
   private ResultsPanel resultsPanel = null;

   private ResizeListener eventListener = null;

   public static void main(String[] args) {
      BooksAndWordsGUI gui = new BooksAndWordsGUI();
      gui.run();
   }

   // Swing GUI
   private JFrame mainFrame = null;

   private void run() {
      bookImplementation = BookFactory.createBook();
      assert(bookImplementation != null);
      mainFrame = new JFrame("Books And Words");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      eventListener = new ResizeListener();

      JSplitPane rootPanel = new JSplitPane();
      mainFrame.getContentPane().add(rootPanel);
      rootPanel.setLayout(new GridLayout());
      rootPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
      controlPanel = new ControlPanel(this);
      controlPanel.setBackground(Color.GRAY);
      resultsPanel = new ResultsPanel(this);
      //resultsPanel.setPreferredSize(new Dimension(1600,2000-controlPanel.getHeight()));
      JScrollPane scrollFrame = new JScrollPane(resultsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      resultsPanel.setAutoscrolls(true);
      scrollFrame.getVerticalScrollBar().setUnitIncrement(100);

      rootPanel.setTopComponent(controlPanel);
      rootPanel.setBottomComponent(scrollFrame); //  resultsPanel
      controlPanel.setLayout(new GridLayout());
      //mainFrame.setPreferredSize(new Dimension(1600, 1200));
      mainFrame.addComponentListener(eventListener);
      mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   void start() {
      try {
         bookImplementation.setSource(currentBookFile, currentIgnoreFile);   
         countUniqueWords();
         report();
         // close(); 
         // TODO: Close button? close when new book selected? Since
         // data is needed for drawing the bar charts.
      } catch (Exception e) {
         JOptionPane.showMessageDialog(mainFrame, "Exception happened because: " + e.getMessage(), "Something went wrong",
                                       JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
      }
   }

   @Override
   public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
      bookImplementation.setSource(fileName, ignoreWordsFile);   
   }

   @Override
   public void countUniqueWords() throws IOException, OutOfMemoryError {
      bookImplementation.countUniqueWords();         
   }

   @Override
   public void report() {
      bookImplementation.report();
      try {
         resultsPanel.createGraphicRepresentation();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void close() {
      bookImplementation.close();      
   }

   @Override
   public int getUniqueWordCount() {
      return bookImplementation.getUniqueWordCount();
   }

   @Override
   public int getTotalWordCount() {
      return bookImplementation.getTotalWordCount();
   }

   @Override
   public String getWordInListAt(int position) {
      return bookImplementation.getWordInListAt(position);
   }

   @Override
   public int getWordCountInListAt(int position) {
      return bookImplementation.getWordCountInListAt(position);
   }
   
   public String getCurrentBookFile() {
      return currentBookFile;
   }

   public void setCurrentBookFile(String currentBookFile) {
      this.currentBookFile = currentBookFile;
   }

   public String getCurrentIgnoreFile() {
      return currentIgnoreFile;
   }

   public void setCurrentIgnoreFile(String currentIgnoreFile) {
      this.currentIgnoreFile = currentIgnoreFile;
   }
   
   private class ResizeListener implements ComponentListener {

      @Override
      public void componentResized(ComponentEvent e) {
         //resultsPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight()-controlPanel.getHeight());
         resultsPanel.setSize(mainFrame.getWidth(), mainFrame.getHeight()-controlPanel.getHeight());
         resultsPanel.invalidate();
         resultsPanel.repaint();         
      }

      @Override
      public void componentMoved(ComponentEvent e) {
      }

      @Override
      public void componentShown(ComponentEvent e) {
         resultsPanel.repaint();
      }

      @Override
      public void componentHidden(ComponentEvent e) {
      }
   }
}
