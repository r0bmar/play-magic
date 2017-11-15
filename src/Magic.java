import java.util.ArrayList;

public class Magic { 
	
	private int size; //size of the board
	private int[][] board; //2D array representing the current state 
	private int score;
	private int tempScore;
	
	public static void main(String[] args) {
		Magic m = new Magic();
		m.printArray(m.getBoard());
		m.dropDown(2,1);
		m.printArray(m.getBoard());
	}
	
	public Magic(int s) { //constructor
		size = s;
		board = new int[size][size];	
		score = 0;
		reset();
	}
	
	public Magic() {//constructor for a specific position
		int[][] a = {{1,2,3,0,3,2},{1,2,3,0,2,1},{1,5,3,0,2,2},{1,0,3,0,2,2},{1,2,3,2,1,2},{2,3,2,2,3,3}};
		board = a;
		score = 0;
		size = 6;
	}
	
	public int getScore() {
		return score;
	}
	
	public int[][] getBoard() { //gets board
		return board;
	}
	
	public int getSize(){
		return size;
	}
	
	private void printArray(int[][] a) { //prints 2D array
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void reset() { //resets the board
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j] = (int)(Math.random()*4);
			}
		}
	}
	
	//CHECK THE BOUNDARY CONDITIONS
	
	public void clicked(int x, int y) {
		int value = board[x][y];
		if(value < 5) {
			board[x][y] = 4;
			tempScore++;
			if(x >= 0 && x < size && y >= 0 && y < size) {
				if(x-1 >= 0) {
					if(board[x-1][y] == value) clicked(x-1,y);
				}
				if(x+1 < size) {
					if(board[x+1][y] == value) clicked(x+1,y);	
				}
				if(y-1 >= 0) {
					if(board[x][y-1] == value) clicked(x, y-1);
				}
				if(y+1 < size) {
					if(board[x][y+1] == value) clicked(x, y+1);
				}
			}
		}
		else if(value == 5){
			for(int i = x-1; i < x+2; i++) {
				for(int j = y-1; j < y+2; j++) {
					if(i >= 0 && j >= 0 && j < size && i < size) {
						board[i][j] = 4;
						tempScore++;
					}
				}
			}
		}
		
	}
	
	public void dropDown(int x, int y) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int count = 0;
		clicked(x,y);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[j][i] != 4) {
					queue.add(board[j][i]);
				}
				else count++;
			}
			for(int k = 0; k < count; k++) {
				board[k][i] = 4;
			}
			for(int l = count; l < size; l++) {
				board[l][i] = queue.remove(0);
			}
			count = 0;
		}
		score = score + tempScore*tempScore;
		tempScore = 0;
	}
	
	public void fallDown() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] == 4) {
					if(Math.random() < 0.01) {
						board[i][j] = 5;
					}
					else board[i][j] = (int)(Math.random()*4);
				}
			}
		}
	}
	
}
