import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class TicTacToe implements ActionListener, MouseListener{
    JFrame jFrame = new JFrame("Tic Tac Toe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn=true, hasWon=false, hasChance=false;

    //Theme colors
    Color color1 = new Color(236, 154, 83);
    Color color2 = new Color(255, 255, 255);
    Color color3 = new Color(252, 65, 65);
    //Color color4 = new Color(231, 112, 22);

    public TicTacToe(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 400);
        jFrame.getContentPane().setBackground(color2);
        jFrame.setLayout(new BorderLayout());

        textField.setBackground(color2);
        textField.setForeground(color3);
        textField.setFont(new Font("Ink Free", Font.BOLD, 40));
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
            buttons[i].addMouseListener(this);
            buttons[i].setBackground(color1);
            buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color1, color1, color1, color1));
        }

        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(color2);
        titlePanel.setBounds(0, 30, 300, 300);
        titlePanel.add(textField);

        buttonPanel.setLayout(new GridLayout(3, 3, 6, 6));
        buttonPanel.setBackground(color2);

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
                buttons[i].setForeground(color2);
                if(player1Turn){
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                    }
                }
                else{
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                    }
                }
                check();
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
        buttons[a].setBackground(color3);
        buttons[a].setBorder(new BevelBorder(BevelBorder.LOWERED, color3, color3, color3, color3));
        buttons[b].setBackground(color3);
        buttons[b].setBorder(new BevelBorder(BevelBorder.LOWERED, color3, color3, color3, color3));
        buttons[c].setBackground(color3);
        buttons[c].setBorder(new BevelBorder(BevelBorder.LOWERED, color3, color3, color3, color3));
        for(int i=0; i<9; i++){
            buttons[i].setEnabled(false); //cannot play the game again
        }
        textField.setText(winner + " Wins");
        hasWon = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(color3);
                buttons[i].setForeground(color2);
                buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color3, color3, color3, color3));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(color2);
                buttons[i].setForeground(color3);
                buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color2, color2, color2, color2));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(color1);
                buttons[i].setForeground(color2);
                buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color1, color1, color1, color1));
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(color3);
                buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color3, color3, color3, color3));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                buttons[i].setBackground(color1);
                buttons[i].setBorder(new BevelBorder(BevelBorder.LOWERED, color1, color1, color1, color1));
            }
        }
    }
}