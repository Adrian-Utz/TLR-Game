package TLRCode.utils;

import TLRCode.Game;

public class Constants {
    public static class UI {
        
        
    }
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int D_IDLE = 0;
        public static final int R_IDLE = 1;
        public static final int L_IDLE = 2;
        public static final int U_IDLE = 3;
        public static final int D_MOVE = 4;
        public static final int R_MOVE = 5;
        public static final int L_MOVE = 6;
        public static final int U_MOVE = 7;
    
        public static int RecieveSpriteAmount(int player_action){
            switch(player_action){
                case D_IDLE:
                    return 9;
                case R_IDLE:
                    return 9;
                case L_IDLE:
                    return 9;
                case U_IDLE:
                    return 9;
                case D_MOVE:
                    return 6;
                case R_MOVE:
                    return 6;
                case L_MOVE:
                    return 6;
                case U_MOVE:
                    return 6;
                
                
                default:
                    return 1;
            }
        }
    }

}
