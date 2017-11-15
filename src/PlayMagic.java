import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayMagic implements MouseListener, KeyListener {
	private Magic magic;
	private SimpleCanvas sc;
	private int clicks;
	private boolean reshuffled = false;

	public static void main(String[] args) {
		PlayMagic pm = new PlayMagic();
	}

	public PlayMagic() {
		sc = new SimpleCanvas("Magic", 700, 700, Color.BLACK);
		sc.addMouseListener(this);
		sc.addKeyListener(this);
		sc.drawRectangle(50, 50, 650, 650, Color.WHITE);
		magic = new Magic(6);
		for (int i = 1; i < magic.getSize(); i++) {
			sc.drawLine(50 + i * 100, 50, 50 + i * 100, 650, Color.black);
			sc.drawLine(50, 50 + i * 100, 650, 50 + i * 100, Color.black);
		}
		magic.reset();
		drawGame();
	}
	
	public void drawGame() {
		int[][] currentBoard = magic.getBoard();
		Font a = new Font("Arial", 1, 16);
		sc.setFont(a);
		sc.drawRectangle(0, 0, 750, 50, Color.black);
		sc.drawString("Score = " + magic.getScore(), 100, 30, Color.white);
		sc.drawString(clicks+"",500,30, Color.WHITE);
		sc.drawString("Reshuffles left = " + (reshuffled ? 0 : 1), 300, 675, Color.white);
		for (int i = 0; i < magic.getSize(); i++) {
			for (int j = 0; j < magic.getSize(); j++) {
				if (currentBoard[j][i] == 0) {
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.lightGray);
				} 
				else if (currentBoard[j][i] == 1) {
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.MAGENTA);
				} 
				else if (currentBoard[j][i] == 2) {
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.blue);
				} 
				else if (currentBoard[j][i] == 3) {
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.CYAN);
				}
				else if (currentBoard[j][i] == 4){
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.WHITE);
					
				}
				else if (currentBoard[j][i] == 5) {
					sc.drawRectangle(100 * (i) + 75, 100 * (j) + 75, 100 * (i) + 125, 100 * (j) + 125, Color.BLACK);
					sc.drawString("Bomb", 100*i + 77, 100 *j + 104, Color.WHITE);
				}
			}
		}
	}
	
	public void makeMove(int x, int y) {
		if(magic.getBoard()[x][y] != 4) {
			magic.dropDown(x, y);
			drawGame();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(clicks < 30) {
			makeMove(((e.getY() + 50)/100) - 1, ((e.getX() + 50)/100) -1);
			clicks++;
		}
		else{
			sc.drawRectangle(0, 0, 700, 700, Color.BLACK);
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sc.wait(500);
		magic.fallDown();
		drawGame();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(clicks < 30) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_R && !reshuffled) {
				magic.reset();
				reshuffled = true;
				sc.drawRectangle(0, 650, 700, 700, Color.black);
				drawGame();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}