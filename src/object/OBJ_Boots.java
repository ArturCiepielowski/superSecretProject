package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gp){
        super(gp);
        down1 =setup("/objects/Boots",gp.tileSize,gp.tileSize);
        price = 50;

    }
}
