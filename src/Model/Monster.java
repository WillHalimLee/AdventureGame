package Model;


import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A abstract monster class that extends from DungeonCharacter.
 *
 * @author Halim Lee
 * @version July 2023.
 */
public abstract class Monster extends Entity {
    private int actionLockCounter = 0;

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     */
    protected Monster(GamePanel theGamePanel) {
        super(theGamePanel);
        setHp(100);
        setMySpeed(2);
        setMySolidArea(new Rectangle(0, 0, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize()));
    }
    /**
     * Resets the monsters solid area.
     */
    public void resetSolidArea(){
        getMySolidArea().x = 0;
        getMySolidArea().y = 0;
    }
    /**
     * Updates the monster's data.
     */
    public void update() {
        setAction();
        myCollision = false;
        myGamePanel.getMyCollision().checkTile(this);
//      myGamePanel.getMyCollision().monsterToPlayer(this);
        if (!myCollision) {
            switch (myDirection) {
                case "up" -> goUp();
                case "down" -> goDown();
                case "left" -> goLeft();
                case "right" -> goRight();
            }
        }
        incSpriteCounter();
        if (getMySpriteCounter() > 12) {
            switchMySpriteNum();
            resetMySpriteCounter();
        }
    }

    /**
     * Sets the monsters movement direction with "randomness".
     */
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 30) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                if (!myDirection.equals("up")) {
                    myDirection = "up";
                }
            }
            if (i > 25 && i <= 50) {
                if (!myDirection.equals("down")) {
                    myDirection = "down";
                }
            }
            if (i > 50 && i <= 75) {
                if (!myDirection.equals("right")) {
                    myDirection = "right";
                }
            }
            if (i > 75) {
                if (!myDirection.equals("left")) {
                    myDirection = "left";
                }
            }
            actionLockCounter = 0;
        }
    }

    /**
     * Draws the monster on the GUI.
     * @param theGraphics the pen used to draw the monster.
     */
    public void draw(final Graphics2D theGraphics) {

        int screenX = getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
        int screenY = getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();
        if (getMyWorldXCoordinate() + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                getMyWorldXCoordinate() - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                getMyWorldYCoordinate() + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                getMyWorldYCoordinate() - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
            BufferedImage image = null;

            switch (myDirection) {
                case "up":
                    if (getMySpriteNum() == 1) {
                        image = up1;
                    } else {
                        image = up2;
                    }
                    break;
                case "down":
                    if (getMySpriteNum() == 1) {
                        image = down1;
                    } else {
                        image = down2;
                    }
                    break;
                case "left":
                    if (getMySpriteNum() == 1) {
                        image = left1;
                    } else {
                        image = left2;
                    }
                    break;
                case "right":
                    if (getMySpriteNum() == 1) {
                        image = right1;
                    } else {
                        image = right2;
                    }
                    break;
            }
            theGraphics.drawImage(image, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
            // draw the rectangle that acts as the collision indicator.
            theGraphics.drawRect(screenX + getMySolidArea().x,screenY + getMySolidArea().y, getMySolidArea().width,  getMySolidArea().height);
        }
    }
}
