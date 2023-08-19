package Model;

import View.GamePanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class initiates all the monsters and items that will be in the dungeon.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class InitiateEntities {
    /**
     * Represents the index in both the x and y direction of the middle of the room.
     */
    private static final int MIDDLE_OF_ROOM = 4;
    /**
     * The panel that we will draw all the entities to.
     */
    private  GamePanel myGamePanel;
    /**
     * A list that stores every monster that will be in the dungeon.
     */
    private List<Monster> myMonsterArray;
    /**
     * A list that stores every item that will be in the dungeon.
     */
    private List<Item> myItemArray;

    /**
     * Sets up all the entities that will be contained within the dungeon.
     *
     * @param theGamePanel The panel to draw all the entities onto.
     */
    public InitiateEntities(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myMonsterArray = new ArrayList<>();
        createMonster();
        myItemArray = new ArrayList<>();
        createItems();
    }

    /**
     * Creates and draws in all the monsters into the dungeon.
     */
    public void createMonster() {

    }

    /**
     * Creates and draws in all the items (including pillars) into the dungeon.
     */
    public void createItems() {

    }

    /**
     * Gets the list of every monster currently in the dungeon.
     *
     * @return The list of monsters contained in the dungeon.
     */
    public List<Monster> getMyMonsterArray() {
        return myMonsterArray;
    }

    /**
     * Gets the list of all items currently in the dungeon.
     *
     * @return The list of items contained in the dungeon.
     */
    public List<Item> getMyItemArray() {
        return myItemArray;
    }
}

