package oy.tol.tira.books.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.placement.RectangleWordPlacer;

public class ResultsPanel extends JPanel {
   private BooksAndWordsGUI gui = null;
   private static final float MARGIN = 6.0f;
   private Dimension area = null;
   private BufferedImage image;
   private static final int WORD_CLOUD_SIZE = 600;

   private List<WordFrequency> wordFrequencies = null;
   private int maxValue = 0;


   ResultsPanel(BooksAndWordsGUI gui) {
      this.gui = gui;
      area = new Dimension(0,0);
   }

   void createGraphicRepresentation() throws IOException {
      maxValue = 0;
      int uniqueWordCount = Math.min(gui.getUniqueWordCount(), 100);
      wordFrequencies = new ArrayList<>();
      for (int wordItem = 0; wordItem < uniqueWordCount; wordItem++) {
         String word = gui.getWordInListAt(wordItem);
         int count = gui.getWordCountInListAt(wordItem);
         if (count > maxValue) {
            maxValue = count;
         }
         wordFrequencies.add(new WordFrequency(word, count));
      }   
      final Dimension dimension = new Dimension(1280, 800); // WORD_CLOUD_SIZE, WORD_CLOUD_SIZE
      final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
      wordCloud.setPadding(2);
      wordCloud.setAngleGenerator(new AngleGenerator(0));
      wordCloud.setBackground(new RectangleBackground(dimension));
      wordCloud.setColorPalette(new ColorPalette(new Color(0xFF5511), new Color(0x408DFF), new Color(0x101AF1), new Color(0x40D5F1), new Color(0x70E311), new Color(0xFFFFFF)));
      wordCloud.setFontScalar(new LinearFontScalar(8, 120));
      wordCloud.build(wordFrequencies);
      wordCloud.writeToFile("WordCloud.png");
      image = ImageIO.read(new File("WordCloud.png"));
      repaint();
   }

   @Override
   public void paintComponent(Graphics g) {

      if (wordFrequencies == null) {
         return;
      }
      super.paintComponent(g);      
      g.drawImage(image, 0, 0, this);

      int uniqueWordCount = wordFrequencies.size();
      if (uniqueWordCount >= 0) {
         Graphics2D graphics = (Graphics2D)g;

         Rectangle clientArea = getBounds();
         System.out.format("area: x: %d y: %d width: %d height: %d%n", clientArea.x, clientArea.y, clientArea.width, clientArea.height);
         Dimension dim = getSize();
         System.out.format("dim: width: %d height: %d%n", dim.width, dim.height);
         FontMetrics fontMetrics = graphics.getFontMetrics();
         int fontHeight = fontMetrics.getHeight();
         int fontBaseLine = fontMetrics.getDescent();
         float originX = clientArea.x + MARGIN + WORD_CLOUD_SIZE;
         float originY = clientArea.y + MARGIN + fontHeight;
         float barOriginX = clientArea.x + MARGIN + WORD_CLOUD_SIZE + fontMetrics.stringWidth("TheExpectedMaxLengthOfAWord");
         float barMaxWidth = clientArea.width - barOriginX - MARGIN;

         Locale loc = getLocale();
         for (int wordItem = 0; wordItem < uniqueWordCount; wordItem++) {
            String word = wordFrequencies.get(wordItem).getWord();
            String wordStuff = String.format("%4d. %-20s", wordItem + 1, word);
            graphics.drawString(wordStuff, originX, originY);
            int count = wordFrequencies.get(wordItem).getFrequency();
            Paint oldPaint = graphics.getPaint();
            graphics.setPaint(Color.DARK_GRAY);
            graphics.draw(new Line2D.Float(originX, originY + (MARGIN/2.0f), barOriginX, originY + (MARGIN/2.0f)));
            // graphics.drawLine(originX, originY + (MARGIN/2), barOriginX, originY + (MARGIN/2));
            float barWidth = ((float)count / (float)maxValue) * barMaxWidth;
            graphics.fill(new Rectangle2D.Float(barOriginX, originY - fontHeight, barWidth, fontHeight));
            graphics.setPaint(Color.WHITE);
            String countString = String.format(loc, "%d", count);
            float stringWidth = fontMetrics.stringWidth(countString) / 2.0f;
            graphics.drawString(countString, (barOriginX + (barWidth/2.0f)) - stringWidth , (float)originY - fontBaseLine);
            graphics.setPaint(oldPaint);
            originY += fontHeight + MARGIN;
         }   
         area.width = clientArea.width;
         area.height = (int)originY + fontHeight;
         setPreferredSize(area);
         revalidate();
      }
   }
}
