package TLRCode.GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TLRCode.Game;
import TLRCode.Levels.LevelManaging;
import TLRCode.entities.Player;
import TLRCode.utils.LoadLVL;

public class Playing extends States implements gameStateMethods{
    private Player player;
    private LevelManaging levelManaging;

    private boolean Paused = false;
    private int lvlxview;
    private int lvlyview;
    private int LeftBorder = (int)(0.4 * Game.GAME_WIDTH);
    private int RightBorder = (int)(0.6 * Game.GAME_WIDTH);
    private int TopBorder = (int)(0.4 * Game.GAME_HEIGHT);
    private int BottomBorder = (int)(0.5 * Game.GAME_HEIGHT);
    private int maxLVLWidth = LoadLVL.RecieveLVLData()[0].length;
    private int maxLVLHeight = LoadLVL.RecieveLVLData().length;
    private int maxSpriteXOffset = maxLVLWidth - Game.NUMBER_TILES_WIDTH;
    private int maxSpriteWidthOffset = maxSpriteXOffset * Game.TILES_SIZE;
    private int maxSpriteYOffset = maxLVLHeight - Game.NUMBER_TILES_HEIGHT;
    private int maxSpriteHeightOffset = maxSpriteYOffset * Game.TILES_SIZE;
    private int[][] LVLData;
    



    public Playing(Game game) {
        super(game);
        initializeClasses();
       
    }

    private void initializeClasses() {
        levelManaging = new LevelManaging(game);
        player = new Player(200, 200, (int)(32 * Game.SCALE), (int)(32 * Game.SCALE));
        player.loadLVLData(levelManaging.getStartingLevel().getLVLData());
    }

    public Player getPlayer(){
        return player;
    }

    public void lostWindowFocus(){
        player.resetDirectionBools();
    }

    @Override
    public void update() {
        if(!Paused){
            levelManaging.update();
            player.update();
            playerPositionIRTBorder();
        }else{

        }
    }

    private void playerPositionIRTBorder(){
        int playerX = (int)player.gethitbox().x;
        int playerY = (int)player.gethitbox().y;
        int xDifference = playerX - lvlxview;
        int yDifference = playerY - lvlyview;

        //Border X Position
        if(xDifference > RightBorder){
            lvlxview += xDifference - RightBorder;
        }else if(xDifference < LeftBorder){
            lvlxview += xDifference - LeftBorder;
        }

        //Border Y Position
        if(yDifference > BottomBorder){
            lvlyview += yDifference - BottomBorder;
        }else if(yDifference < TopBorder){
            lvlyview += yDifference - TopBorder;
        }

        //Clamp the view within the lvl boundaries so the camera doesn't go out of bounds
        if(lvlxview > maxSpriteWidthOffset){
            lvlxview = maxSpriteWidthOffset;
        }else if(lvlxview < 0){
            lvlxview = 0;
        }

        if(lvlyview > maxSpriteHeightOffset){
            lvlyview = maxSpriteHeightOffset;
        }else if(lvlyview < 0){
            lvlyview = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
       levelManaging.draw(g, lvlxview, lvlyview);
       player.render(g, lvlxview, lvlyview);


        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
       }
    } 
}