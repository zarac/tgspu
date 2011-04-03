package labB;
import java.awt.*;
import javax.swing.*;

public class TextViewer {
	private JFrame frame = new JFrame("Textvisare");
	private JTextArea ta = new JTextArea();
	
	public TextViewer() {
		this( new Text("Detta är en textvisare", Color.RED, Color.BLACK, new Font("SansSerif",Font.PLAIN,36)));
	}
	
	public TextViewer(Text text) {
		frame.setSize(400,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		setText(text);
		frame.getContentPane().add( new JScrollPane(ta));
		frame.setVisible(true);
	}
	
	public void setText( Text text ) {
		ta.setBackground(text.getBackground());
		ta.setForeground(text.getForeground());
		ta.setFont(text.getFont());
		ta.setText(text.getContent());
	}
}
