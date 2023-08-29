package Model;

import View.GamePanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class initiates all the monsters and items that will be in the dungeon.
 *
 * @author Halim Lee
 * @version July 2023.
 */
public class InitiateEntities {

    /**
     * The panel that we will draw all the entities to.
     */
    private  GamePanel myGamePanel;

    /**
     * Sets up all the entities that will be contained within the dungeon.
     *
     * @param theGamePanel The panel to draw all the entities onto.
     */
    public InitiateEntities(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    /**
     * Creates and draws in all the monsters into the dungeon.
     */
    public void createMonster() {
        Monster ogre = new Ogre(myGamePanel);
        ogre.setMyWorldXCoordinate(myGamePanel.getSpriteSize() * 51);
        ogre.setMyWorldYCoordinate(myGamePanel.getSpriteSize() * 14);
        myGamePanel.getMyMonsterArray().add(ogre);
    }

    /**
     * Creates and draws in all the items (including pillars) into the dungeon.
     */
    public void createItems() {
        Item sign = new Sign(myGamePanel);
        sign.setMyWorldXCoordinate(48 * myGamePanel.getSpriteSize());
        sign.setMyWorldYCoordinate(51 * myGamePanel.getSpriteSize());
        myGamePanel.getMyItemArray().add(sign);
    }

}

