package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font eagleLake_40, eagleLake_80B;


    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int comandNum = 0;
    public int titleScreenState = 0; // 0 the first screen 1: the second screen

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/EagleLake-Regular.ttf");
            eagleLake_40 = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(eagleLake_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY STATE
        if (gp.gameState == gp.playState) {

        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Slayers";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 4, y + 4);
            //MAIN COLOR
            g2.setColor(Color.yellow);
            g2.drawString(text, x, y);


            String subText = "Xellos lost stories";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56F));
            x = getXforCenteredText(subText);

            //SHADOW
            g2.setColor(new Color(84, 68, 135));
            g2.drawString(subText, x + 4, y + 99);
            //MAIN COLOR
            g2.setColor(new Color(196, 179, 228));
            g2.drawString(subText, x, y + 96);

            //XELLOS CHARACTER
            g2.setColor(Color.white);
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2 + 10;
            g2.drawImage(gp.xellos.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (comandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (comandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (comandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            //CHARACTER SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your hero!";
            int x=getXforCenteredText(text);
            int y=gp.tileSize*3;
            g2.drawString(text,x,y);

            text="Lina Inverse";
            x= getXforCenteredText(text);
            y+=gp.tileSize*3;
            g2.drawString(text,x,y);
            if(comandNum==0){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="Zelgadiss Graywords";
            x= getXforCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if(comandNum==1){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="Amelia Wil Tesla Seyruun";
            x= getXforCenteredText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if(comandNum==2){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="Back";
            x= getXforCenteredText(text);
            y+=gp.tileSize*2;
            g2.drawString(text,x,y);
            if(comandNum==3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        //WINDOW

        int x = gp.tileSize / 2 - 20;
//        int x = gp.tileSize * 2;
        int y = gp.tileSize * 8;
//        int y = gp.tileSize / 2;
        int width = gp.screenWidth /*- (gp.tileSize * 4)*/ - 5;
        int height = gp.tileSize * 4;
        drawSubWindows(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindows(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawXellos() {

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
