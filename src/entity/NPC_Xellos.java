package entity;

import main.GamePanel;
import java.awt.*;

import java.util.Random;

public class NPC_Xellos extends Entity {

    public NPC_Xellos(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/npc/XellosBackWalkFeetRight",gp.tileSize,gp.tileSize); //boy up1
        up2 = setup("/npc/XellosBackWalkFeetLeft",gp.tileSize,gp.tileSize); //boy up2
        down1 = setup("/npc/XellosFrontWalkFeetLeft",gp.tileSize,gp.tileSize);//boy_down_1
        down2 = setup("/npc/XellosFrontWalkFeetRight",gp.tileSize,gp.tileSize);//boy_down_2
        left1 = setup("/npc/XellosRightStanding",gp.tileSize,gp.tileSize);//boy_left_1
        left2 = setup("/npc/XellosRightWalking",gp.tileSize,gp.tileSize);//boy_left_2
        right1 = setup("/npc/XellosLeftStanding",gp.tileSize,gp.tileSize);//boy_right_1
        right2 = setup("/npc/XellosLeftWalking",gp.tileSize,gp.tileSize);//boy_right_2
        stand1 = setup("/npc/XellosFrontStanding",gp.tileSize,gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello I am Xellos a mysterious priest.";
        dialogues[1] = "If you can fool your friends, you can fool your\nenemies";
        dialogues[2] = "Oh, I make it a point to never lie";
        dialogues[3] = "Can't say. That is a secret!";
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void speak() {
        super.speak();
    }


}
