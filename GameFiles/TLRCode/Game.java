package TLRCode;

import javax.swing.SwingUtilities;
import java.awt.*;

import TLRCode.GameStates.*;

public class Game implements Runnable{
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int gameFPS = 60;
    private final int gameUPS = 100;

    //private MainMenu mainMenu;
    private Playing playing;
    // private GameMenu gameMenu;
    // private Options options;

    public final static int DEFAULT_TILE_SIZE = 16;
    public final static float SCALE = 3.0f;
    public final static int NUMBER_TILES_WIDTH = 25;
    public final static int NUMBER_TILES_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * NUMBER_TILES_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * NUMBER_TILES_HEIGHT;

    Game(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        initialClasses();
        gamePanel.setGame(this);
        GameStart();
    }

    private void GameStart() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (GameStates.playingGame) {
            case MENU:
                
                break;
            case PLAYING:
                playing.update();
                break;
        
            default:
                break;
        }
    }

    public void render(Graphics g){
        switch (GameStates.playingGame) {
            case MENU:
                
                break;
            case PLAYING:
                playing.draw(g);
                break;
        
            default:
                break;
        }
    }

    private void initialClasses() {
        playing = new Playing(this);
    }

    //This is the frame rate counter that also doubles as the update clock
    @Override
    public void run() {
        
        double frameTime = 1000000000.0 / gameFPS;
        double updateTime = 1000000000.0 / gameUPS;
        double deltaU = 0;
        double deltaF = 0;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        while(true){
            
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / updateTime;
            deltaF += (currentTime - previousTime) / frameTime;
            previousTime = currentTime;
            
            // This is the main part of the update loop
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        gamePanel.repaint();
                    }
                });
                deltaF--;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void lostWindowFocus(){
        if(GameStates.playingGame == GameStates.PLAYING){
            playing.getPlayer().resetDirectionBools();
        }
    }

    // public MainMenu getMainMenu(){
    //     return mainMenu;
    // }

    public Playing getPlaying(){
        return playing;
    }

}
