package TLRCode.utils;

import java.awt.geom.Rectangle2D;
import TLRCode.Game;
import TLRCode.entities.Player;

public class HelpMethods {
    
    public static boolean player_Can_Move_Here(float x, float y, float width, float height, int[][] LVLData){
        if(!solid_Ground(x, y, LVLData)){
            if(!solid_Ground(x + width, y + height, LVLData)){
                if(!solid_Ground(x + width, y, LVLData)){
                    if(!solid_Ground(x, y + height, LVLData)){
                        if(!solid_Ground(x + width, y + 32, LVLData)){
                            if(!solid_Ground(x, y + 32, LVLData)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean solid_Ground(float x, float y, int[][] LVLData) {
        int levelmaxWidth = LVLData[0].length * Game.TILES_SIZE;
        int levelmaxHeight = LVLData.length * Game.TILES_SIZE;

        if(x < 0 || x >= levelmaxWidth){
            return true;
        }
        if(y < 0 || y >= levelmaxHeight){
            return true;
        }

        float xidx = x / (Game.DEFAULT_TILE_SIZE * Game.SCALE);
        float yidx = y / (Game.DEFAULT_TILE_SIZE * Game.SCALE);

        int value = LVLData[(int)yidx][(int)xidx];

        /*Here put the coroponding sprite number into the if statement to make it have no collision*/
    
        if(value >= 3){
            return true;
        }else{
            return false;
        }
    }

    public static float getEntityPositionXwall(Rectangle2D.Float hitboxRectangle, Float xSpeed){
        int currentTile = (int)hitboxRectangle.x / Game.TILES_SIZE;
        if(xSpeed > 0){
            //Right
            int xTilepos = currentTile * Game.TILES_SIZE;
            int xDifference = (int)(Game.TILES_SIZE - hitboxRectangle.width);
            return xTilepos + xDifference -1;
        } else{
           //Left
            return currentTile * Game.TILES_SIZE;
        }


    }

    public static float getEntityPositionYwall(Rectangle2D.Float hitboxRectangle, Float ySpeed){
        int currentTile = (int)hitboxRectangle.y / Game.TILES_SIZE;
        if(ySpeed > 0){
            //Up
            int yTilepos = currentTile * Game.TILES_SIZE;
            int yDifference = (int)(Game.TILES_SIZE - hitboxRectangle.height);
            return yTilepos + yDifference -1;
        } else{
           //Down
            return currentTile * Game.TILES_SIZE;
        }


    }

}
