package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author zarac
 */
public class Snake implements ActionListener, KeyListener
{
	Color backgroundColor = Color.BLACK;
	Color wallColor = Color.WHITE;
	Color snakeColor = Color.RED;

	private JFrame frame;

	// Our graphics...
	private DrawingComponent scene;

	// Grid size, (amount of) "squares"
	private int gridSizeX = 35;
	private int gridSizeY = 25;
	// Grid square size
	private int gridSquareSizeX = 20;
	private int gridSquareSizeY = 20;

	// ToDo: should this be here? perhaps in DrawinComponent and Level?
	private Graphics graphics;

	private Level level;

	// Used for updating the screen
	private Timer timer;

	private SnakeDirection snakeDirection;


	public Snake()
	{
		frame = new JFrame();
		scene = new DrawingComponent();

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		scene.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.add(scene);
		frame.setTitle("Snake");
		createLevel(0);
		scene.repaint();
 		timer = new Timer(60, this);
		timer.start();
	}

	public static void main(String[] args)
	{
		Snake snake = new Snake();
	}


	// ToDo: Read levels from (perhaps) XML
	private void createLevel(int p_level)
	{
		level = new Level();

		boolean walls[][] = new boolean[gridSizeX][gridSizeY];

		switch(p_level)
		{
			case 0:
				for (int x = 0; x < gridSizeX; x++)
				{
					walls[x][0] = true;
					walls[x][gridSizeY-1] = true;
				}
				for (int y = 0; y < gridSizeY; y++)
				{
					walls[0][y] = true;
					walls[gridSizeX-1][y] = true;
				}
				break;
		}

		level.setGrid(walls);
		// ToDo: clean up! (should use Point, i think is nicer..)
		level.setStartPosition(10, 10);
		level.setSnakePosition(10, 10);
	}

	public void keyTyped(KeyEvent e) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyPressed(KeyEvent e)
	{
		int keyCode = (int)e.getKeyChar();

		// ToDo: Check so we don't go LEFT if RIGHT etc..
		switch (keyCode)
		{
			case 37:
				snakeDirection = SnakeDirection.LEFT;
				break;
			case 38:
				snakeDirection = SnakeDirection.UP;
				break;
			case 39:
				snakeDirection = SnakeDirection.RIGHT;
				break;
			case 40:
				snakeDirection = SnakeDirection.DOWN;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void actionPerformed(ActionEvent e)
	{
//		System.out.println(e);
		if (e.getSource() == timer)
		{
			level.snakePosition.x++;
			if (snakeDirection == SnakeDirection.LEFT)
				level.snakePosition.x--;
			else if(snakeDirection == SnakeDirection.UP)
				level.snakePosition.y--;
			else if(snakeDirection == SnakeDirection.RIGHT)
				level.snakePosition.x++;
			else if(snakeDirection == SnakeDirection.DOWN)
				level.snakePosition.y++;
		}

		scene.repaint();

//			switch(snakeDirection)
//			{
//				case LEFT:
//					level.snakePosition.x--;
//					break;
//				case UP:
//					level.snakePosition.y--;
//					break;
//				case RIGHT:
//					level.snakePosition.x++;
//					break;
//				case DOWN:
//					level.snakePosition.y++;
//					break;
//			}
	}


	private enum SnakeDirection
	{
		UP, DOWN, LEFT, RIGHT
	}


	private class Level
	{
		// ToDo, make enum so that we can combine walls + apple + snake...
		// atm. grid contains only walls
		private boolean grid[][] = new boolean[gridSizeX][gridSizeY];

		private Point startPosition;
		public Point snakePosition;

		// Offset...
		int gridStartX = 50;
		int gridStartY = 50;


		public Level()
		{
			startPosition = new Point();
			snakePosition = new Point();
		}


		public void setStartPosition(int x, int y)
		{
			startPosition.x = x;
			startPosition.y = y;
		}

		public void setSnakePosition(int x, int y)
		{
			snakePosition.x = x;
			snakePosition.y = y;
		}

		public void setGrid(boolean p_grid[][])
		{
			grid = p_grid;
		}

		public void drawLevel()
		{
			graphics.setColor(backgroundColor);
			graphics.fillRect(0, 0, frame.getWidth(), frame.getHeight());

			graphics.setColor(wallColor);

			for (int x = 0; x < gridSizeX; x++)
			{
				for (int y = 0; y < gridSizeY; y++)
				{
//					System.out.println(x + "," +y);
					if (grid[x][y])
					{
//						System.out.println("wall");
						graphics.fillRect(
								gridStartX + (x*gridSquareSizeX),
								gridStartY + (y*gridSquareSizeY),
								gridSquareSizeX,
								gridSquareSizeY);
					}
				}
			}
		}

		public void drawSnake()
		{
			graphics.setColor(snakeColor);
			graphics.fillRect(
					gridStartX + (snakePosition.x*gridSquareSizeX),
					gridStartY + (snakePosition.y*gridSquareSizeY),
					gridSquareSizeX,
					gridSquareSizeY);
		}
	}


	private class DrawingComponent extends JPanel
	{
//		private Graphics graphics;

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			graphics = g;
			level.drawLevel();
			level.drawSnake();
		}

//		public void drawLevel()
//		{
//			graphics.setColor(Color.BLACK);
//			graphics.fillRect(0, 0, frame.getWidth(), frame.getHeight());
//		}
	}
}
