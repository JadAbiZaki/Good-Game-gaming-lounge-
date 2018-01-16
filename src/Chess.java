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
public class Chess extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n=2;
        if(n==1){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFrameChess().setVisible(true);
            }
        });
        }else if(n==2){
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFrameChessLan().setVisible(true);
            }
        });
        }
    }
    
}
