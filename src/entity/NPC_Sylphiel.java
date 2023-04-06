package entity;

import main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Sylphiel extends Entity{
    public NPC_Sylphiel(GamePanel gp) {
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
        setItems();
    }

    public void getImage() {

        up1 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
        stand1 = setup("/npc/SylphielFrontStanding",gp.tileSize,gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello Gourry dear. What do you need?";
    }
    public void setItems(){

        inventory.add(new OBJ_Potion(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }

    public void speak(){
//       super.speak(); // Fix so that merchant won't moove
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
       gp.gameState=gp.tradeState;
       gp.ui.npc=this;
    }
}
