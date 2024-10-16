package TLRCode;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class Gameframe extends JFrame{
    private GamePanel gamePanel;

    Gameframe(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        this.setTitle("The Long Road");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addWindowFocusListener(new WindowFocusListener() {
            
            @Override
            public void windowGainedFocus(WindowEvent e){
                gamePanel.getGame().lostWindowFocus();
            }

            @Override
            public void windowLostFocus(WindowEvent e){

            }
        });

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }
}
