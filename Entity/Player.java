package Entity;

import main.GamePanel;
import main.KeyHdle;

public class Player extends Entity{
    GamePanel gp;
    KeyHdle keyHdle;
    public Player(GamePanel inpgp, KeyHdle inpkeyhdle ){
 this.gp=inpgp;
 this.keyHdle=inpkeyhdle;
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
    }
    public void update(){

    }
    public void draw(){

    }

}
