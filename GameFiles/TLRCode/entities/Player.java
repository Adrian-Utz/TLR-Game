package TLRCode.entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.util.Random;

import TLRCode.Game;
import TLRCode.utils.LoadLVL;
import TLRCode.utils.Constants.PlayerConstants;

import static TLRCode.utils.HelpMethods.*;

public class Player extends entity{

    private BufferedImage[][] animations;
    private int animaTic;
    private int animaSpeed = 15;
    private int animaSpeedIdle = 15;
    private int animaSpeedRunning = 16;
    private int animaIndex;
    private int player_action = PlayerConstants.R_IDLE;
    private int[][] LVLData;
    private int lastDirection;

    private boolean isMoving = false;
    private boolean isRunning = false;
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean up;

    private float player_speed = (Game.SCALE / 2);
    private float player_running_speed = (Game.SCALE / 4);
    private float player_hitbox_x = 8 * Game.SCALE;
    private float player_hitbox_y = 2 * Game.SCALE;

    private Random random = new Random();
    private int randomAnimationInterval = random.nextInt(1000) + 50;
    private int randomAnimationTimer = 0;
    private boolean playAnimation = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        hitboxInitialize(x, y, (int)(8 * Game.SCALE), (int)(24 * Game.SCALE));
    }

    public void update() {
        positionUpdate();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g, int lvlxview, int lvlyview) {
        g.drawImage(animations[player_action][animaIndex], 
                    (int)(hitboxRectangle.x - player_hitbox_x) - lvlxview, 
                    (int)(hitboxRectangle.y - player_hitbox_y) - lvlyview, 
                    (int)(25 * Game.SCALE), //control the player's width
                    (int)(35 * Game.SCALE), //control the player's height
                    null);
        //hitboxDraw(g); //Here is the hitbox of the player
    }

    private void setAnimation() {
        int animationStart = player_action;

        if(isMoving == true){
            if (down) {
                player_action = PlayerConstants.D_MOVE;
                lastDirection = PlayerConstants.D_IDLE;
            } else if (up) {
                player_action = PlayerConstants.U_MOVE;
                lastDirection = PlayerConstants.U_IDLE;
            } else if (left) {
                player_action = PlayerConstants.L_MOVE;
                lastDirection = PlayerConstants.L_IDLE;
            } else if (right) {
                player_action = PlayerConstants.R_MOVE;
                lastDirection = PlayerConstants.R_IDLE;
            }
        }else{
            player_action = lastDirection;
        }
        
        if(animationStart != player_action){
            resetAnimaTic();
        }
               
    }

    private void updateAnimation() {
        animaTic++;
        randomAnimationTimer++;

        int currentAnimationSpeed;

        if(isMoving == true){
            currentAnimationSpeed = animaSpeedRunning;
            
            if(animaTic >= currentAnimationSpeed){
                animaTic = 0;
                animaIndex++;
                if(animaIndex >= PlayerConstants.RecieveSpriteAmount(player_action)){
                    animaIndex = 0;
                }
            }

        }else{
            currentAnimationSpeed = animaSpeedIdle;

            if(randomAnimationTimer >= randomAnimationInterval){
                playAnimation = true;
                randomAnimationTimer = 0;
                randomAnimationInterval = random.nextInt(1000) + 50; 
                //new random interval between 50 and 1050 ticks
            }

            if(playAnimation){
                animaTic++;
                if(animaTic >= currentAnimationSpeed){
                    animaTic = 0;
                    animaIndex++;
                    if(animaIndex >= PlayerConstants.RecieveSpriteAmount(player_action)){
                        animaIndex = 0;
                        playAnimation = false;
                    }
                }
            }else{
                animaIndex = 0;
            }   
        }        
    }

    private void resetAnimaTic(){
        animaTic = 0;
        animaIndex = 0;
    }

    private void positionUpdate() {

        float xSpeed = 0;
        float ySpeed = 0;
        isMoving = false;

        if(left){
            xSpeed -= player_speed;
            isMoving = true;
        }
        if(right){
            xSpeed += player_speed;
            isMoving = true;
        }

        if(up){
            ySpeed -= player_speed;
            isMoving = true;
        }
        if(down){
            ySpeed += player_speed;
            isMoving = true;
        }

        //debugging
        //System.out.println("Moving Left: " + left + ", Right: " + right + ", Up: " + up + ", Down: " + down + ", isMoving: " + isMoving);


        //Check if player can move here and Update the Player's Position
        if(player_Can_Move_Here(hitboxRectangle.x, hitboxRectangle.y, hitboxRectangle.width, hitboxRectangle.height, LVLData)){
            xPositionUpdate(xSpeed);
            yPositionUpdate(ySpeed);
        }
    }

    private void xPositionUpdate(float xSpeed){
        if(player_Can_Move_Here(hitboxRectangle.x + xSpeed, hitboxRectangle.y, hitboxRectangle.width, hitboxRectangle.height, LVLData)){
            hitboxRectangle.x += xSpeed;
        }else {
            hitboxRectangle.x = getEntityPositionXwall(hitboxRectangle, xSpeed);
        }
    }

    private void yPositionUpdate(float ySpeed){
        if(player_Can_Move_Here(hitboxRectangle.x, hitboxRectangle.y + ySpeed, hitboxRectangle.width, hitboxRectangle.height, LVLData)){
            hitboxRectangle.y += ySpeed;
        }else {
            hitboxRectangle.y = getEntityPositionYwall(hitboxRectangle, ySpeed);
        }
    }



    private void loadAnimations() {

        BufferedImage img = LoadLVL.GetSpriteAtlas(LoadLVL.PLAYER_ATLAS);

        animations = new BufferedImage[15][10];

        for(int j = 0; j < animations.length; j++){
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i * 32, j * 64, 32, 64);
            }
        }
    }

    public void resetDirectionBools() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isLeft(){
        return left;
    }

    public boolean isUp(){
        return up;
    }

    public boolean isRight(){
        return right;
    }

    public boolean isDown(){
        return down;
    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public void setUp(boolean b) {
        this.up = b;
    }

    public void setDown(boolean b) {
        this.down = b;
    }

    public void playerRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void loadLVLData(int[][] lvlData) {
        this.LVLData = lvlData;
    }
    
}
