package TLRCode.Controls;

import java.awt.event.*;

import TLRCode.Game;
import TLRCode.GamePanel;
import TLRCode.GameStates.GameStates;

public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameStates.playingGame){
            case MENU:

                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameStates.playingGame){
            case MENU:

                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }

    
}
