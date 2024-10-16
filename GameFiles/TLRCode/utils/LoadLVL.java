package TLRCode.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class LoadLVL {
    
    public static final String PLAYER_ATLAS = "TLRCode/res/MC_Sprites.png";
    public static final String STARTING_AREA_ATLAS = "TLRCode/res/Start_Area.png";
    public static final String STARTING_AREA_SPRITE = "TLRCode/res/Starting_Sprites.png";


    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadLVL.class.getResourceAsStream("/" + fileName);

        try{
            img = ImageIO.read(is);
        } catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(is != null){
                    is.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
    public static int[][] RecieveLVLData(){
        BufferedImage img = GetSpriteAtlas(STARTING_AREA_ATLAS);
        int[][] LVLData = new int[img.getHeight()][img.getWidth()];

        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed();

                if(value >= 0){
                    switch(value){
                        case 56:
                            LVLData[y][x] = 0;
                            break;
                        case 0:
                            LVLData[y][x] = 1;
                            break;
                        case 79:
                            LVLData[y][x] = 2;
                            break;
                        default:
                            LVLData[y][x] = 1;
                            break;
                    }
                }else{
                    LVLData[y][x] = 0;
                }
                
            }
        }
        return LVLData;
    }


}
