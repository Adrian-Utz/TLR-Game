package TLRCode;

import java.awt.*;
import javax.swing.*;

import TLRCode.Controls.*;
import TLRCode.entities.Player;
import static TLRCode.Game.GAME_HEIGHT;
import static TLRCode.Game.GAME_WIDTH;

public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private Game game;

    GamePanel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize(){
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_WIDTH + ": " + GAME_HEIGHT);
    }

    public void drawPlayer(Graphics g, Player player){
        player.render(g);
    }

    public void setGame(Game game){
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}
