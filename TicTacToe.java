import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class TicTacToe implements ActionListener{
    JFrame jFrame = new JFrame("Tic Tac Toe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn=true, hasWon=false, hasChance=false;

    public TicTacToe(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.getContentPane().setBackground(new Color(0, 0, 0));
        jFrame.setLayout(new BorderLayout());

        textField.setBackground(new Color(0, 10, 0));
        textField.setForeground(new Color(255, 119, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 30));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setVerticalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 25));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(new Color(255, 119, 0));
            buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, Color.black, Color.black, Color.black));
        }

        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(Color.black);
        titlePanel.setBounds(0, 30, 400, 400);
        titlePanel.add(textField);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(255, 119, 0));

        jFrame.add(buttonPanel);
        jFrame.add(titlePanel, BorderLayout.NORTH);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                if(player1Turn){
                    if(Objects.equals(buttons[i].getText(), "")){
                        buttons[i].setForeground(new Color(0, 0, 0));
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                }
                else{
                    if(Objects.equals(buttons[i].getText(), "")){
                        buttons[i].setForeground(new Color(0, 0, 0));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void check(){
        //Win conditions
        String row1 = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
        String row2 = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
        String row3 = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
        String col1 = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
        String col2 = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
        String col3 = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
        String diag1 = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
        String diag2 = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
        String X = "XXX";
        String O = "OOO";

        //X Wins?
        if(row1.equals(X)){ win(0, 1, 2, "X");}
        else if(row2.equals(X)){ win(3, 4, 5, "X");}
        else if(row3.equals(X)) { win(6, 7, 8, "X");}
        else if(col1.equals(X)) {win(0, 3, 6, "X");}
        else if(col2.equals(X)) { win(1,4, 7, "X");}
        else if(col3.equals(X)) { win(2, 5, 8, "X");}
        else if(diag1.equals(X)) { win(0, 4, 8, "X");}
        else if(diag2.equals(X)) { win(2, 4, 6, "X");}

        //O Wins?
        else if(row1.equals(O)){ win(0, 1, 2, "O");}
        else if(row2.equals(O)){ win(3, 4, 5, "O");}
        else if(row3.equals(O)) { win(6, 7, 8, "O");}
        else if(col1.equals(O)) {win(0, 3, 6, "O");}
        else if(col2.equals(O)) { win(1,4, 7, "O");}
        else if(col3.equals(O)) { win(2, 5, 8, "O");}
        else if(diag1.equals(O)) { win(0, 4, 8, "O");}
        else if(diag2.equals(O)) { win(2, 4, 6, "O");}

        //Draw
        else{
            hasChance=false;
            for(int i=0; i<9; i++){
                if(buttons[i].getText().isEmpty()){
                    hasChance=true;
                    break;
                }
            }
            if(!hasChance && !hasWon){
                textField.setText("Match Draw");
                for(int i=0; i<9; i++){
                    buttons[i].setEnabled(false); //cannot play the game again
                }
            }
        }
    }

    public void win(int a, int b, int c, String winner){
        buttons[a].setBackground(Color.WHITE);
        buttons[b].setBackground(Color.WHITE);
        buttons[c].setBackground(Color.WHITE);
        for(int i=0; i<9; i++){
            buttons[i].setEnabled(false); //cannot play the game again
        }
        textField.setText(winner + "Wins");
        hasWon = true;
    }
}