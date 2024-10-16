package TLRCode.Levels;

public class StartingArea {
    private int[][] LVLData;
    
    public StartingArea(int[][] LVLData){
        this.LVLData = LVLData;
    }

    public int getSpriteIdx(int x, int y){
        int index = LVLData[y][x];

        return index;
    }

    public int[][] getLVLData(){
        return LVLData;
    }
}
