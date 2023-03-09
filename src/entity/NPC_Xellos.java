package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Xellos extends Entity {

    public NPC_Xellos(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/npc/boy_up_1"); //boy up1
        up2 = setup("/npc/boy_up_2"); //boy up2
        down1 = setup("/npc/boy_down_1");//boy_down_1
        down2 = setup("/npc/boy_down_2");//boy_down_2
        left1 = setup("/npc/boy_left_1");//boy_left_1
        left2 = setup("/npc/boy_left_2");//boy_left_2
        right1 = setup("/npc/boy_right_1");//boy_right_1
        right2 = setup("/npc/boy_right_2");//boy_right_2
    }

    public void setDialogue() {
        dialogues[0] = "Hello I am Xellos a mysterious priest.";
        dialogues[1] = "If you can fool your friends, you can\nfool your enemies";
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
                direction = "down";
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
