import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class ConnectFour implements ActionListener {
	
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JButton[] buttons = new JButton[42];
	JButton[][] twoDbuttons = new JButton[6][7];
	JLabel text_field = new JLabel();
	Random random = new Random();
	boolean redFlag = false;
	
	ConnectFour(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 800, 400);
		
		text_field.setBackground(new Color(25,25,25));
		text_field.setForeground(new Color(0,0,0));
		text_field.setFont(new Font("Ink Tree",Font.BOLD,25));
		text_field.setText("Connect Four");
		text_field.setHorizontalAlignment(JLabel.LEFT);
		text_field.setOpaque(false);
		
		button_panel.setLayout(new GridLayout(6,7));
		button_panel.setBackground(new Color(150,150,150));
		
		
		
		
		
		for(int i = 0; i < twoDbuttons.length; i++) {
			for(int j = 0; j < twoDbuttons[0].length; j++) {
				twoDbuttons[i][j] = new JButton();
				//twoDbuttons[i][j].setText(i + "-" + j);
				button_panel.add(twoDbuttons[i][j]);
				twoDbuttons[i][j].setFont(new Font("MV Boli",Font.BOLD,25));
				twoDbuttons[i][j].setFocusable(false);
				twoDbuttons[i][j].addActionListener(this);
				
			}
		}
		
		frame.add(button_panel);
		title_panel.add(text_field);
		frame.add(title_panel,BorderLayout.NORTH);
		
		firstTurn();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= 6; i++) {
			if(e.getSource() == twoDbuttons[0][i]) {
					for(int j = twoDbuttons.length - 1; j >=0; j--) {
						if(twoDbuttons[j][i].getText()=="") {
							if(redFlag) {
								twoDbuttons[j][i].setForeground(new Color(255,0,0));
								twoDbuttons[j][i].setText("O");
								redFlag = false;
								text_field.setText("Connect Four: Blue's Turn");
								check();
								break;
							}else {
								twoDbuttons[j][i].setForeground(new Color(0,0,255));
								twoDbuttons[j][i].setText("0");
								redFlag = true;
								text_field.setText("Connect Four: Red's Turn");
								check();
								break;
							}
							
						}
					}
				
			}
		}
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		if(random.nextInt(2)== 0) {
			redFlag = true;
			text_field.setText(text_field.getText() + ": Red's Turn");
		}else {
			redFlag = false;
			text_field.setText(text_field.getText() + ": Blue's Turn");
		}
	}
	
	public void check() {
		//check for across
		
		
		for(int i = 0; i < twoDbuttons.length; i++) {
			for(int j = 0; j < twoDbuttons[0].length - 3; j++) {
				if(twoDbuttons[i][j].getText().equals("O") &&
				   twoDbuttons[i][j + 1].getText().equals("O") &&
		           twoDbuttons[i][j + 2].getText().equals("O") &&
		           twoDbuttons[i][j + 3].getText().equals("O")) {
		                 redWins(i,j,"across");
		        }

			}
		}
		
		for(int i = 0; i < twoDbuttons.length; i++) {
			for(int j = 0; j < twoDbuttons[0].length - 3; j++) {
				if(twoDbuttons[i][j].getText().equals("0") &&
				   twoDbuttons[i][j + 1].getText().equals("0") &&
		           twoDbuttons[i][j + 2].getText().equals("0") &&
		           twoDbuttons[i][j + 3].getText().equals("0")) {
		                 blueWins(i,j,"across");
		        }

			}
		}
		
		//check up and down
		
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int col = 0; col < twoDbuttons[0].length; col++) {
				if(twoDbuttons[i][col].getText().equals("O") &&
				   twoDbuttons[i + 1][col].getText().equals("O") &&
		           twoDbuttons[i + 2][col].getText().equals("O") &&
       	           twoDbuttons[i + 3][col].getText().equals("O")) {
					redWins(i,col,"vertical");
				}
			}
		}
		
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int col = 0; col < twoDbuttons[0].length; col++) {
				if(twoDbuttons[i][col].getText().equals("0") &&
				   twoDbuttons[i + 1][col].getText().equals("0") &&
		           twoDbuttons[i + 2][col].getText().equals("0") &&
       	           twoDbuttons[i + 3][col].getText().equals("0")) {
					blueWins(i,col,"vertical");
				}
			}
		}
		
		//check downward and diagonal
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int j =0 ; j < twoDbuttons[0].length - 3; j++) {
				if(twoDbuttons[i][j].getText().equals("O") &&
				   twoDbuttons[i + 1][j + 1].getText().equals("O") &&
		           twoDbuttons[i + 2][j + 2].getText().equals("O") &&
    	           twoDbuttons[i + 3][j + 3].getText().equals("O")) {
						redWins(i,j,"diagonal");
					}
			}
		}
		
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int j =0 ; j < twoDbuttons[0].length - 3; j++) {
				if(twoDbuttons[i][j].getText().equals("0") &&
				   twoDbuttons[i + 1][j + 1].getText().equals("0") &&
		           twoDbuttons[i + 2][j + 2].getText().equals("0") &&
    	           twoDbuttons[i + 3][j + 3].getText().equals("0")) {
						blueWins(i,j,"diagonal");
					}
			}
		}
		
		//check upward and diagonal
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int j = twoDbuttons[0].length - 1; j > 2; j--) {
				if(twoDbuttons[i][j].getText().equals("O") &&
				   twoDbuttons[i + 1][j - 1].getText().equals("O") &&
		           twoDbuttons[i + 2][j - 2].getText().equals("O") &&
	 	           twoDbuttons[i + 3][j - 3].getText().equals("O")) {
						redWins(i,j,"upward diagonal");
			    	}
			}
		}
		
		for(int i = 0; i < twoDbuttons.length - 3; i++) {
			for(int j = twoDbuttons[0].length - 1; j > 2; j--) {
				if(twoDbuttons[i][j].getText().equals("0") &&
				   twoDbuttons[i + 1][j - 1].getText().equals("0") &&
		           twoDbuttons[i + 2][j - 2].getText().equals("0") &&
	 	           twoDbuttons[i + 3][j - 3].getText().equals("0")) {
						blueWins(i,j,"upward diagonal");
			    	}
			}
		}
	}
	
	public void redWins(int i, int j, String str) {
		if(str.equals("across")) {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 1].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 2].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else if(str.equals("vertical")) {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else if(str.equals("diagonal")){
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j + 1].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j + 2].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j + 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j - 1].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j - 2].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j - 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}
	
		
		text_field.setText("Connect Four: Red Wins");
	}
	
	public void blueWins(int i, int j, String str) {
		if(str.equals("across")) {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 1].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 2].setBackground(new Color(50,50,50));
			twoDbuttons[i][j + 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else if(str.equals("vertical")) {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else if(str.equals("diagonal")) {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j + 1].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j + 2].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j + 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}else {
			twoDbuttons[i][j].setBackground(new Color(50,50,50));
			twoDbuttons[i + 1][j - 1].setBackground(new Color(50,50,50));
			twoDbuttons[i + 2][j - 2].setBackground(new Color(50,50,50));
			twoDbuttons[i + 3][j - 3].setBackground(new Color(50,50,50));
			disableAllButtons(twoDbuttons);
		}
		
		text_field.setText("Connect Four: Blue Wins");
	}
	
	public void disableAllButtons(JButton[][] buttons) {
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[0].length; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
	}
}


