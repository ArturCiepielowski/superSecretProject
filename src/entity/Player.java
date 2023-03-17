package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {


    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        //PLAYER STATUS

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

//        up1 = setup("/player/LinaBackWalkFeetRight"); //boy up1
//        up2 = setup("/player/LinaBackWalkFeetLeft"); //boy up2
//        down1 = setup("/player/LinaFrontWalkFeetLeft");//boy_down_1
//        down2 = setup("/player/LinaFrontWalkFeetRight");//boy_down_2  LINA INVERSE
//        left1 = setup("/player/LinaLeftStanding");//boy_left_1
//        left2 = setup("/player/LinaLeftWalking");//boy_left_2
//        right1 = setup("/player/LinaRightStanding");//boy_right_1
//        right2 = setup("/player/LinaRightWalking");//boy_right_2

        up1 = setup("/player/GourryBackWalkFeetRight", gp.tileSize, gp.tileSize); //boy up1
        up2 = setup("/player/GourryBackWalkFeetLeft", gp.tileSize, gp.tileSize); //boy up2
        down1 = setup("/player/GourryFrontWalkFeetLeft", gp.tileSize, gp.tileSize);//boy_down_1
        down2 = setup("/player/GourryFrontWalkFeetRight", gp.tileSize, gp.tileSize);//boy_down_2
        left1 = setup("/player/GourryLeftStanding", gp.tileSize, gp.tileSize);//boy_left_1
        left2 = setup("/player/GourryLeftWalking", gp.tileSize, gp.tileSize);//boy_left_2
        right1 = setup("/player/GourryRightStanding", gp.tileSize, gp.tileSize);//boy_right_1
        right2 = setup("/player/GourryRightWalking", gp.tileSize, gp.tileSize);//boy_right_2
    }

    public void getPlayerAttackImage() {


        attackUp1 = setup("/player/Gourry_down_atack-1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/Gourry_down_atack-2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/Gourry_up_atack-1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/Gourry_up_atack-2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/Gourry_left_atack-1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/Gourry_left_atack-2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/Gourry_right_atack-1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/Gourry_right_atack-2", gp.tileSize * 2, gp.tileSize);

    }


    public void update() {
        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||
                keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //CHECK EVENT
            gp.eHandler.checkEvent();


            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            gp.keyH.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            //attack area becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //After checking collision restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed == true) {
            if (i != 999) {

                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;

            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {

                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[i].invincible == false) {

                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if (gp.monster[i].life <= 0) {
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //DEBUG
//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10,400);
    }

}
