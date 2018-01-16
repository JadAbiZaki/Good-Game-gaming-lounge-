import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nader
 */
public class Sudoko extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              jFramesudoku sudoku;
       sudoku = new jFramesudoku();
       sudoku.setVisible(true);
            }
        }); 
    }
    
}
