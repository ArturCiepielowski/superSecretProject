package entity;

import main.GamePanel;
import object.*;

public class NPC_Sylphiel extends Entity{
    public NPC_Sylphiel(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
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

    }
}
