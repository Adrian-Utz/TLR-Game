package TLRCode.GameStates;

import TLRCode.Game;

public enum GameStates {
    
    TITLESCREEN, MENU, PLAYING, OPTIONS;

    public static GameStates titleScreen = TITLESCREEN;
    public static GameStates mainMenu = MENU;
    public static GameStates playingGame = PLAYING;
    public static GameStates optionState = OPTIONS;
     
}
