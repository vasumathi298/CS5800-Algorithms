package HashTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HistogramPlot extends JPanel {
	private int[] values;
    private int[] bins = new int[20];
    private int maxValue;

    public HistogramPlot(int[] values) {
    	this.values=values;
        // Calculate the histogram
        for (double value : values) {
            int binIndex = (int) Math.floor(value / 20.0);
            if (binIndex >= 0 && binIndex < bins.length) {
                bins[binIndex]++;
            }
        }
        maxValue = Arrays.stream(bins).max().orElse(0);
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the histogram bars
        int barWidth = getWidth() / bins.length;
        int x = 0;
        for (int bin : bins) {
            int barHeight = (int) ((double) bin / maxValue * getHeight());
            g.setColor(Color.BLUE);
            g.fillRect(x, getHeight() - barHeight, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, getHeight() - barHeight, barWidth, barHeight);
            x += barWidth;
        }
    }

   public void plotGraph(HistogramPlot plot) {
	   JFrame frame = new JFrame("Histogram Example");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(plot);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

}
