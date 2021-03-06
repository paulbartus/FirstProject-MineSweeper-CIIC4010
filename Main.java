import java.util.Random;
import javax.swing.JFrame;



public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame(" Classic MineSweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 250);
		myFrame.setSize(500, 500);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);



		//Set Bombs Randomly
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				Random random = new Random();
				int randomNumber = random.nextInt((10));
				if (randomNumber == 1){
					myPanel.numsArray[i][j] = 9;

				}
			}
		}

		//Assign numbers to cells with nearby bombs
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				//for empty cells
				if(myPanel.numsArray[i][j] != 9){
					int counter = 0;
					if (i == 0)
						if(j == 0){
							if (myPanel.numsArray[i+1][j] == 8){
								counter++;
								}
							if (myPanel.numsArray[i+1][j+1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i][j+1] == 8){
								counter++;
							}
							myPanel.numsArray[i][j] = counter;
						}
					if (i == 8) 
						if (j == 0){
							if (myPanel.numsArray[i-1][j] == 8){
								counter++;
								}
							if (myPanel.numsArray[i-1][j+1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i][j+1] == 8){
								counter++;
								}
							myPanel.numsArray[i][j] = counter;
							}
					if (i == 0) 
						if (j == 8){
							if (myPanel.numsArray[i][j-1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i+1][j-1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i+1][j] == 8){
								counter++;
								}
							myPanel.numsArray[i][j] = counter;
							}
					if(i == 8) 
						if (j == 8){
							if (myPanel.numsArray[i-1][j-1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i][j-1] == 8){
								counter++;
								}
							if (myPanel.numsArray[i-1][j] == 8){
								counter++;
							}
							myPanel.numsArray[i][j] = counter;
						}
					if(i == 0) 
						if (j > 0)  
							if (j < 8){
								if (myPanel.numsArray[i][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j+1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i][j+1] == 8){
									counter++;
								}
								myPanel.numsArray[i][j] = counter;
							}
					if (j == 0) 
						if (i > 0) 
							if (i < 8){
								if (myPanel.numsArray[i-1][j] == 8){
									counter++;
								}
								if (myPanel.numsArray[i-1][j+1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i][j+1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j+1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j] == 8){
									counter++;
								}
								myPanel.numsArray[i][j] = counter;
							}
					if(i == 8) 
						if (j > 0)  
							if (j < 8){
								if (myPanel.numsArray[i][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i-1][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i-1][j] == 8){
									counter++;
								}
								if (myPanel.numsArray[i-1][j+1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i][j+1] == 8){
									counter++;
								}
								myPanel.numsArray[i][j] = counter;
							}
					if (j == 8 ) 
						if (i > 0) 
							if (i < 8){
								if (myPanel.numsArray[i-1][j] == 8){
									counter++;
								}
								if (myPanel.numsArray[i-1][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j-1] == 8){
									counter++;
								}
								if (myPanel.numsArray[i+1][j] == 8){
									counter++;
								}
								myPanel.numsArray[i][j] = counter;
							}
					if (i > 0) 
						if (i < 8) 
							if (j > 0) 
								if (j < 8){
									if (myPanel.numsArray[i-1][j-1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i][j-1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i+1][j-1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i+1][j] == 8){
										counter++;
									}
									if (myPanel.numsArray[i+1][j+1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i][j+1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i-1][j+1] == 8){
										counter++;
									}
									if (myPanel.numsArray[i-1][j] == 8){
										counter++;
									}
									myPanel.numsArray[i][j] = counter;
								}
				}
			}
		}
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}
