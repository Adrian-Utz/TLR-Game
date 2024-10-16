package TLRCode.GameStates;

import java.awt.event.MouseEvent;

import TLRCode.Game;


public class States {
    
    protected Game game;
    public States(Game game){
        this.game = game;
    }

    // public boolean isPlayerInButtons(MouseEvent e){
        
    // }

    public Game getGame(){
        return game;
    }
}
