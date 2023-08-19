package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;


/**
 * Heroes class which extends from DungeonCharacter
 * we will be creating 3 other subclasses which extends
 * off of Heroes which will be Priestess, Thief, and Warrior.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public abstract class Heroes extends Entity {

    /**
     * The x coordinate that is in the middle of screen.
     */
    private int myScreensMiddleX;
    /**
     * The y coordinate that is in the middle of screen.
     */
    private int myScreensMiddleY;
    /**
     * A Keyboard object.
     */
    Keyboard myKeyInputs;
    /**
     * A series of images that represent the hero's health.
     */
    public  BufferedImage hp0,hp1,hp2,hp3,hp4,hp5,hp6,hp7,hp8,hp9,hp10,hp11,hp12,hp13,hp14,hp15,hp16;

    /**
     * Heroes constructor that initializes the hp, name, attack speed, min damage, max damage,
     * hit chance, block chance, gamepanel, and keyboard of the Hero.
     *
     * @param theGamePanel   the gamepanel of the hero.
     * @param theKeyBoard    the keyboard input of the hero.
     */
    protected Heroes(final GamePanel theGamePanel, final Keyboard theKeyBoard) {
        super(theGamePanel);
        myKeyInputs = theKeyBoard;

        myScreensMiddleX = myGamePanel.getMyScreenWidth() / 2 - (myGamePanel.getSpriteSize() / 2);
        myScreensMiddleY = myGamePanel.getMyScreenHeight() / 2 - (myGamePanel.getSpriteSize() / 2);
        setMyWorldXCoordinate((myGamePanel.getMyWorldCol() * myGamePanel.getSpriteSize()) / 2);
        setMyWorldYCoordinate ((myGamePanel.getMyWorldRow() * myGamePanel.getSpriteSize()) / 2);

        setMySolidArea(new Rectangle(12, 12, myGamePanel.getSpriteSize() - 24, myGamePanel.getSpriteSize() - 24));
        setMySpeed(6);
        setHp(100);
        setHPBar();
    }

    /**
     * A method that return the screen's middle x coordinate.
     * @return the x coordinate that is in the middle of the screen.
     */
    public int getMyScreensMiddleX() {
        return myScreensMiddleX;
    }

    /**
     * Sets the heroes screens middle x coordinate.
     * @param theScreensMiddleX the screens middle x coordinate.
     */
    public void setMyScreensMiddleX(int theScreensMiddleX) {
        myScreensMiddleX = theScreensMiddleX;
    }

    /**
     * A method that return the screen's middle y coordinate.
     * @return the y coordinate that is in the middle of the screen.
     */
    public int getMyScreensMiddleY() {
        return myScreensMiddleY;
    }
    /**
     * Sets the heroes screens middle y coordinate.
     * @param theScreensMiddleY the screens middle y coordinate.
     */
    public void setMyScreensMiddleY(int theScreensMiddleY) {
        myScreensMiddleY = theScreensMiddleY;
    }

    /**
     * A method that updates the heroes movement and direction.
     */
    public void update() {
        if (myKeyInputs.up) {
            myDirection = "up";
        } else if (myKeyInputs.down) {
            myDirection = "down";
        } else if (myKeyInputs.left) {
            myDirection = "left";
        } else if (myKeyInputs.right) {
            myDirection = "right";
        }

        // Checking Map Tile Collision
        myCollision = false;
        myGamePanel.getMyCollision().checkTile(this);
        //Check Collision with Monsters
        int monster = myGamePanel.getMyCollision().checkEntity(this, myGamePanel.getMyMonsterArray());
        // Check collision with the items
        int item = myGamePanel.getMyCollision().checkItem(this, myGamePanel.getMyItemArray());

        if (myKeyInputs.up || myKeyInputs.down || myKeyInputs.left || myKeyInputs.right) {
            if (!myCollision) {
                switch (myDirection) {
                    case "up" ->goUp();
                    case "down" -> goDown();
                    case "left" -> goLeft();
                    case "right" -> goRight();
                }
            }
        }
        incSpriteCounter();
        if (getMySpriteCounter() > 12) {
            switchMySpriteNum();
            resetMySpriteCounter();
        }
    }

    /**
     * Draw the hero.
     * @param theGraphics the pen used to draw.
     */
    public void draw(final Graphics2D theGraphics) {
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
        // Draw my hero in the middle of the viewable screen.
        theGraphics.drawImage(image, myScreensMiddleX, myScreensMiddleY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        drawHpBar(theGraphics);
    }

    /**
     * Sets the Hp Bar.
     */
    public void setHPBar(){
        try {
            hp0 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar00.png")));
            hp1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar01.png")));
            hp2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar02.png")));
            hp3 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar03.png")));
            hp4 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar04.png")));
            hp5 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar05.png")));
            hp6 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar06.png")));
            hp7 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar07.png")));
            hp8 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar08.png")));
            hp9 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar09.png")));
            hp10 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar10.png")));
            hp11 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar11.png")));
            hp12 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar12.png")));
            hp13 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar13.png")));
            hp14 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar14.png")));
            hp15 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar15.png")));
            hp16 = read(Objects.requireNonNull(getClass().getResourceAsStream("/healthBar/healthBar16.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawHpBar(Graphics2D theGraphics) {
        int hp = this.getHp();

        if (hp >= 100) {
            theGraphics.drawImage(hp0, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 95) {
            theGraphics.drawImage(hp1, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 85) {
            theGraphics.drawImage(hp2, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 65) {
            theGraphics.drawImage(hp3, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 60) {
            theGraphics.drawImage(hp4, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 55) {
            theGraphics.drawImage(hp5, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 50) {
            theGraphics.drawImage(hp6, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 45) {
            theGraphics.drawImage(hp7, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 40) {
            theGraphics.drawImage(hp8, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 35) {
            theGraphics.drawImage(hp9, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 30) {
            theGraphics.drawImage(hp10, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 25) {
            theGraphics.drawImage(hp11, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 20) {
            theGraphics.drawImage(hp12, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 15) {
            theGraphics.drawImage(hp13, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 10) {
            theGraphics.drawImage(hp14, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 5) {
            theGraphics.drawImage(hp15, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else {
            theGraphics.drawImage(hp16, getMyScreensMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        }
    }
}
