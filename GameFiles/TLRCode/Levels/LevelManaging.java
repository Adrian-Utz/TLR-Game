package TLRCode.Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import TLRCode.Game;
import TLRCode.utils.LoadLVL;
import TLRCode.Levels.StartingArea;

public class LevelManaging {
    private Game game;
    private BufferedImage[] LVLSprites;
    private StartingArea startinglevel;

    public LevelManaging(Game game){
        this.game = game;
        importGroundSprites();
        startinglevel = new StartingArea(LoadLVL.RecieveLVLData());
    }

    private void importGroundSprites() {
        BufferedImage img = LoadLVL.GetSpriteAtlas(LoadLVL.STARTING_AREA_SPRITE);
        int spriteWidth = 32;
        int spriteHeight = 32;

        LVLSprites = new BufferedImage[3];
        for(int y = 0; y < 1; y++){
            for(int x = 0; x < 3; x++){
                int index = y * 1 + x;
                LVLSprites[index] = img.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }

    public void draw(Graphics g, int lvlxview, int lvlyview){
        for(int y = 0; y < startinglevel.getLVLData().length; y++){
            for(int x = 0; x < startinglevel.getLVLData().length; x++){
                int index = startinglevel.getSpriteIdx(x, y);

                /*Debuggin tools*/
                //int value = LVLSprites[index].getRGB(0, 0) & 0xFF;
                //System.out.println("index: " + index + " | value: " + value);
                /*This part of the code will tell you the location and red value of the subimage pixel*/

                if(index >= 0 && index < LVLSprites.length){
                    g.drawImage(LVLSprites[index],Game.TILES_SIZE * x - lvlxview, Game.TILES_SIZE * y - lvlyview, Game.TILES_SIZE, Game.TILES_SIZE, null);
                } else{
                    System.out.println("Index out of bounds: " + index);
                }
            }
        }
    }

    public StartingArea getStartingLevel(){
        return startinglevel;
    }


    public void update() {
       
    }
    
}
