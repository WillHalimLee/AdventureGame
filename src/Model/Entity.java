package Model;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Creates the abstract class DungeonCharacter which other
 * classes extends from like Hero and Monster.
 *
 * @author Halim Lee
 * @version July 2023.
 */

public abstract class Entity  {

    /**
     * The int myHp that tracks the character health points.
     */
    private int myHp;
    /**
     * The hp the character initially starts with.
     */
    private int myDefaultHp = 100;

    /**
     * The main game panel.
     */
    public GamePanel myGamePanel;
    /**
     * The characters direction images.
     */
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    /**
     * A String that dictates the characters directions.
     */
    public String myDirection = "down";
    /**
     * The speed of the character.
     */
    private int mySpeed;
    /**
     * The characters collision boolean.
     */
    public boolean myCollision = false;
    /**
     * A rectangle that dictates the characters collision area.
     */
    private Rectangle mySolidArea;
    /**
     * A counter for sprite animation.
     */
    private int mySpriteCounter = 0;
    /**
     * A number for sprite animation logic.
     */
    private int mySpriteNum = 1;
    /**
     * The characters x coordinate.
     */
    private int myWorldXCoordinate;
    /**
     * The characters y coordinate.
     */
    private int myWorldYCoordinate;
    /**
     * The entity in the game. Player, monsters, NPCs.
     * @param theGamePanel the main game panel.
     */
    protected Entity(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;

    }
    /**
     * Returns the character's X coordinate.
     * @return the x coordinate.
     */
    public int getMyWorldXCoordinate() {
        return myWorldXCoordinate;
    }
    public void goDown() {
        myWorldYCoordinate += mySpeed;
    }
    public void goUp(){
        myWorldYCoordinate -= mySpeed;
    }
    public void goLeft(){
        myWorldXCoordinate -= mySpeed;
    }
    public void goRight(){
        myWorldXCoordinate += mySpeed;
    }
    public void incSpriteCounter() {
        mySpriteCounter++;
    }
    public int getMySpriteCounter(){
        return mySpriteCounter;
    }
    public void switchMySpriteNum(){
        mySpriteNum = mySpriteNum == 1 ? 2 : 1;
    }
    public void resetMySpriteCounter(){
        mySpriteCounter = 0;
    }
    public int getMySpriteNum() {
        return mySpriteNum;
    }

    /**
     * Returns the character's y coordinate.
     * @return the y coordinate.
     */
    public int getMyWorldYCoordinate() {
        return myWorldYCoordinate;
    }
    public void setMyWorldXCoordinate(final int newX) {
        myWorldXCoordinate = newX;
    }
    public void setMyWorldYCoordinate(final int newY) {
        myWorldYCoordinate = newY;
    }
    /**
     * Gets the hp that the character has currently.
     * @return the int hp of the character.
     */
    public int getHp() {
        return myHp;
    }
    /**
     * Sets the new hp of the character.
     * @param theHp new health that the character should acquire.
     */
    public void setHp(int theHp) {
        myHp = theHp;
    }

    /**
     * Returns the character's direction.
     * @return a String that indicates the direction.
     */
    public String getMyDirection() {
        return myDirection;
    }

    /**
     * Returns the entity's speed.
     * @return an integer representing the players speed.
     */
    public int getMySpeed(){
        return mySpeed;
    }

    /**
     * Sets the players speed/
     * @param theSpeed the speed of the character.
     */
    public void setMySpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }
    /**
     * Sets the collision state of the character.
     * @param theBool the collision state.
     */
    public void setMyCollision(final boolean theBool) {
        myCollision = theBool;
    }
    /**
     * Returns the collision area.
     * @return a rectangle.
     */
    public Rectangle getMySolidArea() {
        return mySolidArea;
    }

    /**
     * Set the solid area.
     * @param theSolidArea the solid area.
     */
    public void setMySolidArea(final Rectangle theSolidArea){
        mySolidArea = theSolidArea;
    }

    /**
     * Checks whether the entity is alive or not for both
     * @return Hp greater than 0
     */
    public boolean isAlive() {
        return getHp() > 0;
    }

    /**
     * Resets the hp of the character back to its default.
     */
    public void resetHP(){
        myHp = myDefaultHp;
    }
    public abstract void resetSolidArea();
    public abstract void setEntityImage();

}
