package labB;
import java.awt.*;

public class Text {
	private String content;
	private Color foreground;
	private Color background;
	private Font font;
	
	public Text( String content, Color foreground, Color background, Font font ) {
		this.content = content;
		this.foreground = foreground;
		this.background = background;
		this.font = font;
	}

	public String getContent() {
		return this.content;
	}

	public Color getForeground() {
		return this.foreground;
	}

	public Color getBackground() {
		return this.background;
	}

	public Font getFont() {
		return this.font;
	}
}
