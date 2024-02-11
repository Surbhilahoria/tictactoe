import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class tictactoe implements ActionListener{
    Random turn = new Random();
    JFrame frame = new JFrame();
    JPanel title = new JPanel();
    JPanel button = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    JButton startAgainButton;


    tictactoe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD, 65));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0,0,800,100);

        button.setLayout(new GridLayout(3,3));
        button.setBackground(new Color(150,150,150));

        startAgainButton = new JButton("Start Again");
        startAgainButton.setFont(new Font("MV Boli",Font.BOLD,40));
        startAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                resetGame();
            }
            
        });
        frame.add(startAgainButton, BorderLayout.SOUTH);
        firstTurn();
    
        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            button.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);



        }

        title.add(textfield);
        frame.add(title,BorderLayout.NORTH); 
        frame.add(button);  
        
            firstTurn();
         

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0; i<9;i++)  {
            if(e.getSource()== buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn= false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else if(buttons[i].getText()==""){
                    buttons[i].setForeground(new Color(0,0,255));
                    buttons[i].setText("O");
                    player1_turn= true;
                    textfield.setText("X turn");
                    check();
            }
        }}
        
    }

public void firstTurn() {
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }


   
if (turn.nextInt(2)==0){
        player1_turn = true;
        textfield.setText("x turn");
        
    }
    else {
        player1_turn = false;
        textfield.setText("0 turn"); 
    }

}
public void check(){
    // Define winning combinations
    int[][] winningCombinations = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Horizontal
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Vertical
        {0, 4, 8}, {2, 4, 6}              // Diagonal
    };

    // Check for each winning combination
    for (int[] combination : winningCombinations) {
        if (buttons[combination[0]].getText().equals("X") &&
            buttons[combination[1]].getText().equals("X") &&
            buttons[combination[2]].getText().equals("X")) {
            xwins(combination[0], combination[1], combination[2]);
            return;
        } else if (buttons[combination[0]].getText().equals("O") &&
                   buttons[combination[1]].getText().equals("O") &&
                   buttons[combination[2]].getText().equals("O")) {
            owins(combination[0], combination[1], combination[2]);
            return;
        }
    }
    //check for tie 
    boolean tie = true;
    for(JButton button : buttons){
        if (button.getText().isEmpty()){
            tie = false;
            break;
        }
    }
    if (tie){
        textfield.setText("It is a tie" );
        button.setForeground(Color.black);
        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
    }
}


public void xwins (int x, int y, int z)
{
    buttons[x].setBackground(Color.GREEN);
    buttons[y].setBackground(Color.GREEN);
    buttons[z].setBackground(Color.GREEN);
    
    for(int i=0;i<9;i++) {
        buttons[i].setEnabled(false);
    }
    textfield.setText("X wins");
}
public void owins (int x, int y, int z)
{
    buttons[x].setBackground(Color.GREEN);
    buttons[y].setBackground(Color.GREEN);
    buttons[z].setBackground(Color.GREEN);
    
    for(int i=0;i<9;i++) {
        buttons[i].setEnabled(false);
    }
    textfield.setText("O wins");

}
public void resetGame(){
    for (int i= 0;i<9;i++){
        buttons[i].setText("");
        buttons[i].setEnabled(true);
        buttons[i].setBackground(null);}
        player1_turn = true;
        textfield.setText("Tic-Tac-Toe");
        firstTurn();
    }
}


