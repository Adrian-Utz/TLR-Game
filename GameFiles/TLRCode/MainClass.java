package TLRCode;

import javax.swing.SwingUtilities;

public class MainClass {
    
    public static void main(String[] args){
        GamePanel gamePanel = new GamePanel(null);
        Game game = new Game(gamePanel);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Gameframe(gamePanel);
            }
        });
    }

}
