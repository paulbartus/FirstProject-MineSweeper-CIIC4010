
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Insets;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;

	public class MyMouseAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			switch (e.getButton()) {
			case 1:	//Left mouse button
				Component c = e.getComponent();
				while (!(c instanceof JFrame)) {
					c = c.getParent();
					if (c == null) {
						return;
					}
				}

				JFrame myFrame = (JFrame) c;
				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
				Insets myInsets = myFrame.getInsets();
				int x1 = myInsets.left;
				int y1 = myInsets.top;
				e.translatePoint(-x1, -y1);
				int x = e.getX();
				int y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				myPanel.mouseDownGridX = myPanel.getGridX(x, y);
				myPanel.mouseDownGridY = myPanel.getGridY(x, y);
				myPanel.repaint();
				break;
			case 3:	
				//Right Mouse Button
				Component h = e.getComponent();
				while (!(h instanceof JFrame)) {
					h = h.getParent();
					if (h == null) {
						return;
					}
				}

				JFrame mySecondFrame = (JFrame) h;
				MyPanel mySecondPanel = (MyPanel) mySecondFrame.getContentPane().getComponent(0);
				Insets mySecondInsets = mySecondFrame.getInsets();
				int x11 = mySecondInsets.left;
				int y11 = mySecondInsets.top;
				e.translatePoint(-x11, -y11);
				int xd = e.getX();
				int yd = e.getY();
				mySecondPanel.x = xd;
				mySecondPanel.y = yd;
				mySecondPanel.mouseDownGridX = mySecondPanel.getGridX(xd, yd);
				mySecondPanel.mouseDownGridY = mySecondPanel.getGridY(xd, yd);
				mySecondPanel.repaint();
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
			}
		}
		public void mouseReleased(MouseEvent e) {

			switch (e.getButton()) {
			case 1:	//Left mouse button
				Component c = e.getComponent();
				while (!(c instanceof JFrame)) {
					c = c.getParent();
					if (c == null) {
						return;
					}
				}

				JFrame myFrame = (JFrame)c;
				MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //May loop among components
				Insets myInsets = myFrame.getInsets();
				int x1 = myInsets.left;
				int y1 = myInsets.top;
				e.translatePoint(-x1, -y1);
				int x = e.getX();
				int y = e.getY();
				myPanel.x = x;
				myPanel.y = y;
				int gridX = myPanel.getGridX(x, y);
				int gridY = myPanel.getGridY(x, y);
				
				int mines = 0;
				int blankSpaces = 0;
				
				
				if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
					//Had pressed outside
					//Do nothing
				} else {
					if ((gridX == -1) || (gridY == -1)) {
						//Is releasing outside
						//Do nothing
					} else {
						if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
							//Released the mouse button on a different cell where it was pressed
							//Do nothing
						} else {
							//Released the mouse button on the same cell where it was pressed

							//If the cell clicked on was 0
							if (myPanel.numsArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 0){
								//If there was no mine or number, turn white
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;

								//Turns white all cells surrounding the cell clicked on first
								if (gridX == 0 && gridY == 0){
									//Clicking the top left corner
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
								}
								if (gridX == 8 && gridY == 0){
									//Clicking the top right corner
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
								}
								if (gridX == 0 && gridY == 8){
									//Clicking the bottom left corner
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								}
								if(gridX == 8 && gridY == 8){
									//Clicking the bottom right corner
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								}
								if(gridX == 0 && gridY > 0 && gridY < 8){
									//Ruling out corners, but clicking any cell on left border
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + 1] = Color.WHITE;
								}
								if(gridY == 0 && gridX > 0 && gridX < 8){
									//Ruling out corners, but clicking any cell on the top border
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								}
								if(gridX == 8 && gridY > 0 && gridY < 8){
									//Ruling out corners, but clicking any cell on right border
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + 1] = Color.WHITE;
								}
								if(gridY == 8 && gridX > 0 && gridX < 8){
									//Ruling out corners, but clicking any cell on bottom border
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								}
								if (gridX > 0 && gridX < 8 && gridY > 0 && gridY < 8){
									//Clicking cells in the rest of the Panel, no corners and no borders
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
									myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								}

								//From here every '0' cell will be colored white, clearing anything close to what was clicked on
								
								for (int p = 0; p < 9; p++){
									for (int i = 0; i < 9; i ++){
										for (int j = 0; j < 9; j ++){
											if (myPanel.colorArray[i][j] == Color.WHITE && myPanel.numsArray[i][j] == 0){
												if (i == 0 && j == 0){
													//Clicking the top left corner
													myPanel.colorArray[i + 1][j] = Color.WHITE;
													myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i ][j + 1] = Color.WHITE;
												}
												else if (i == 8 && j == 0){
													//Clicking the top right corner
													myPanel.colorArray[i - 1][j] = Color.WHITE;
													myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i ][j + 1] = Color.WHITE;
												}
												else if (i == 0 && j == 8){
													//Clicking the bottom left corner
													myPanel.colorArray[i][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j] = Color.WHITE;
												}
												else if(i == 8 && j == 8){
													//Clicking the bottom right corner
													myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i][j - 1] = Color.WHITE;
													myPanel.colorArray[i - 1][j] = Color.WHITE;
												}
												else if(i == 0 && j > 0 && j < 8){
													//Ruling out corners, but clicking any cell on left border
													myPanel.colorArray[i][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j] = Color.WHITE;
													myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i][j + 1] = Color.WHITE;
												}
												else if(j == 0 && i > 0 && i < 8){
													//Ruling out corners, but clicking any cell on top border
													myPanel.colorArray[i - 1][j] = Color.WHITE;
													myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i ][j + 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j] = Color.WHITE;
												}
												else if(i == 8 && j > 0 && j < 8){
													//Ruling out corners, but clicking any cell on right border
													myPanel.colorArray[i][j - 1] = Color.WHITE;
													myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i - 1][j] = Color.WHITE;
													myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i][j + 1] = Color.WHITE;
												}
												else if(j == 8 && i > 0 && i < 8){
													//Ruling out corners, but clicking any cell on bottom border
													myPanel.colorArray[i - 1][j] = Color.WHITE;
													myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i ][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j] = Color.WHITE;
												}
												else if (i > 0 && i < 8 && j > 0 && j < 8){
													//Clicking any cell in the rest of the Panel, ruling out corners and borders
													myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i ][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
													myPanel.colorArray[i + 1][j] = Color.WHITE;
													myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i ][j + 1] = Color.WHITE;
													myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
													myPanel.colorArray[i - 1][j] = Color.WHITE;
												}
											}
										}
									}
								}
								myPanel.repaint();
							}
							
							{
								//If user clicks on a bomb
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								for (int i = 0; i < 9; i ++){
									for (int j = 0; j < 9; j++){
										if (myPanel.numsArray[i][j] == 8){
											myPanel.colorArray[i][j] = Color.BLACK;
										}
									}
								}
								myPanel.repaint();
								JOptionPane.showMessageDialog(null, "YOU LOOSE :(", "Classic Minesweeper", JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}
							
							//If we clicked a number different from 0 and 9
							if (myPanel.numsArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] > 0 && myPanel.numsArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] < 8){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.repaint();
							}
							
							//Check if the user won
							for (int i = 0; i < 9; i ++){
								for (int j = 0; j < 9; j++){
									if (myPanel.numsArray[i][j] == 8){
										mines++;
									}
								}
							}
							for (int i = 0; i < 9; i ++){
								for (int j = 0; j < 9; j++){
									if (myPanel.colorArray[i][j] != Color.WHITE){
										blankSpaces++;
									}
								}
							}
							if (blankSpaces == mines){
								JOptionPane.showMessageDialog(null, "YOU WIN! :)", "Classic Minesweeper", JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}
						}
					}
				}
				myPanel.repaint();
				break;
			case 3:	

				Component d = e.getComponent();
				while (!(d instanceof JFrame)) {
					d = d.getParent();
					if (d == null) {
						return;
					}
				}

				JFrame myFramed = (JFrame)d;
				MyPanel myPaneld = (MyPanel) myFramed.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
				Insets myInsetsd = myFramed.getInsets();
				int x1d = myInsetsd.left;
				int y1d = myInsetsd.top;
				e.translatePoint(-x1d, -y1d);
				int xd = e.getX();
				int yd = e.getY();
				myPaneld.x = xd;
				myPaneld.y = yd;
				int gridXd = myPaneld.getGridX(xd, yd);
				int gridYd = myPaneld.getGridY(xd, yd);

				if ((myPaneld.mouseDownGridX == -1) || (myPaneld.mouseDownGridY == -1)) {
					//Had pressed outside
					//Do nothing
				} else {
					if ((gridXd == -1) || (gridYd == -1)) {
						//Is releasing outside
						//Do nothing
					} else {
						if ((myPaneld.mouseDownGridX != gridXd) || (myPaneld.mouseDownGridY != gridYd)) {
							//Released the mouse button on a different cell where it was pressed
							//Do nothing
						} else {
							if (myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] == Color.LIGHT_GRAY){
								myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] = Color.RED;
							}
							else if(myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] == Color.RED){
								myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] = Color.LIGHT_GRAY;
							}
						}
					}
				}

				myPaneld.repaint();
				break;
			default:    //Some other button (2 = Middle mouse button, etc.)
				//Do nothing
				break;
			}
		}
	}