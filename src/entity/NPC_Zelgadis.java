package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Zelgadis extends Entity{
    public NPC_Zelgadis(GamePanel gp) {
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

        up1 = setup("/npc/ZelBackWalkFeetRight",gp.tileSize,gp.tileSize); //boy up1
        up2 = setup("/npc/ZelBackWalkFeetLeft",gp.tileSize,gp.tileSize); //boy up2
        down1 = setup("/npc/ZelFrontWalkFeetLeft",gp.tileSize,gp.tileSize);//boy_down_1
        down2 = setup("/npc/ZelFrontWalkFeetRight",gp.tileSize,gp.tileSize);//boy_down_2
        left1 = setup("/npc/ZelLeftStanding",gp.tileSize,gp.tileSize);//boy_left_1
        left2 = setup("/npc/ZelLeftWalking",gp.tileSize,gp.tileSize);//boy_left_2
        right1 = setup("/npc/ZelRightStanding",gp.tileSize,gp.tileSize);//boy_right_1
        right2 = setup("/npc/ZelRightWalking",gp.tileSize,gp.tileSize);//boy_right_2
        stand1 = setup("/npc/ZelFrontStanding",gp.tileSize,gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello I am Zelgadis.";
        dialogues[1] = "I merely wish to perform\na business transaction.";
        dialogues[2] = "Now then, to business.";
        dialogues[3] = "Follow me.";
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
