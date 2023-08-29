package Model;

import View.GamePanel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * This class checks if the player collides with any objects within the dungeon.
 */
public class Collision implements Serializable {

    /**
     * The game panel that the character and entities are drawn on.
     */
    private final GamePanel myGamePanel;
    /**
     * Sets up the collision object with the correct game panel.
     *
     * @param theGamePanel The game panel the game is being drawn on.
     */
    public Collision(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    /**
     * Goes through all the tiles in the dungeon and checks if the player collided with any. <br>
     * If the player hits a tile that they can't move through, we stop the player.
     *
     * @param entity   The character that we are checking for collisions with.
     */
    public void checkTile(final Entity entity) {
        int entityLeftWorldX = entity.getMyWorldXCoordinate() + entity.getMySolidArea().x;
        int entityRightWorldX = entity.getMyWorldXCoordinate() + entity.getMySolidArea().x + entity.getMySolidArea().width;
        int entityTopWorldY = entity.getMyWorldYCoordinate() + entity.getMySolidArea().y;
        int entityBottomWorldY = entity.getMyWorldYCoordinate() + entity.getMySolidArea().y + entity.getMySolidArea().height;

        int entityLeftCol = entityLeftWorldX / myGamePanel.getSpriteSize();
        int entityRightCol = entityRightWorldX / myGamePanel.getSpriteSize();
        int entityTopRow = entityTopWorldY / myGamePanel.getSpriteSize();
        int entityBottomRow = entityBottomWorldY / myGamePanel.getSpriteSize();

        int tileNum1, tileNum2;

        switch (entity.getMyDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityTopRow][entityLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityTopRow][entityRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    entity.setMyCollision(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityBottomRow][entityLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityBottomRow][entityRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    entity.setMyCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityTopRow][entityLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityBottomRow][entityLeftCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    entity.setMyCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityTopRow][entityRightCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[myGamePanel.getCurrentMap()][entityBottomRow][entityRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    entity.setMyCollision(true);
                }
                break;
        }
    }

    /**
     * Goes through all the entities, or monsters, in the dungeon and checks if the player collided with any. <br>
     * If the player hits a monster, a battle will happen.
     *
     * @param theHero       The character that we are checking for collisions with.
     * @param theMonsters   The list of every monster in the dungeon.
     * @return The amount of collisions made with items.
     */
    public int checkEntity(Heroes theHero, List<Monster> theMonsters) {
        int index = 999;
        int i = 0;
        for (Monster mon : theMonsters) {
            if (mon.isAlive()) {
                theHero.getMySolidArea().x = theHero.getMyWorldXCoordinate() + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.getMyWorldYCoordinate() + theHero.getMySolidArea().y;

                mon.getMySolidArea().x = mon.getMyWorldXCoordinate() + mon.getMySolidArea().x;
                mon.getMySolidArea().y = mon.getMyWorldYCoordinate() + mon.getMySolidArea().y;

                switch (theHero.getMyDirection()) {
                    case "up":
                        theHero.getMySolidArea().y -= theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                        }
                        break;
                    case "down":
                        theHero.getMySolidArea().y += theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                        }
                        break;
                    case "left":
                        theHero.getMySolidArea().x -= theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                        }
                        break;
                    case "right":
                        theHero.getMySolidArea().x += theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                        }
                        break;
                }
                theHero.resetSolidArea();
                mon.resetSolidArea();
            }
        }
        return index;
    }

    /**
     * Goes through all the items in the dungeon and checks if the player collided with any. <br>
     * If the player hits a pit or a health potion, we will set their HP accordingly.
     *
     * @param theHero       The character that we are checking for collisions with.
     * @param theItemList   The list of every item in the dungeon.
     * @return The amount of collisions made with items.
     */
    public int checkItem(final Heroes theHero, final List<Item> theItemList) {
        // We preset the indices
        int index = 999;
        int i = 0;
        // Then go through the item list looking at every item that hasn't been found
        for (Item item : theItemList) {
            if (!item.getFound()) {
                theHero.getMySolidArea().x = theHero.getMyWorldXCoordinate() + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.getMyWorldYCoordinate() + theHero.getMySolidArea().y;

                item.getMySolidArea().x = item.getMyWorldXCoordinate() + item.getMySolidArea().x;
                item.getMySolidArea().y = item.getMyWorldYCoordinate() + item.getMySolidArea().y;

                // Then we go through every direction the player might be going in and check for a collision
                // If one happens we adjust their hp accordingly
                switch (theHero.getMyDirection()) {
                    case "up" -> {
                        theHero.getMySolidArea().y -= theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
//                            item.setFound(true);
                        }
                    }
                    case "down" -> {
                        theHero.getMySolidArea().y += theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
//                            item.setFound(true);

                        }
                    }
                    case "left" -> {
                        theHero.getMySolidArea().x -= theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
//                            item.setFound(true);
                        }
                    }
                    case "right" -> {
                        theHero.getMySolidArea().x += theHero.getMySpeed();
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
//+
                        }
                    }
                }
                theHero.resetSolidArea();
                item.resetSolidArea();
            }
        }
        return index;
    }
}

    // Probably wont need as we will delete the monster on player to monster collision.
//    public void monsterToPlayer(Monster theMon){
//        theMon.getMySolidArea().x = theMon.myWorldXCoordinate + theMon.getMySolidArea().x;
//        theMon.getMySolidArea().y = theMon.myWorldYCoordinate + theMon.getMySolidArea().y;
//
//        myGamePanel.getMyHero().getMySolidArea().x =  myGamePanel.getMyHero().getMyWorldXCoordinate() +  myGamePanel.getMyHero().getMySolidArea().x;
//        myGamePanel.getMyHero().getMySolidArea().y =  myGamePanel.getMyHero().myWorldYCoordinate +  myGamePanel.getMyHero().getMySolidArea().y;
//
//        switch (theMon.getMyDirection()){
//            case "up" :
//                theMon.getMySolidArea().y -= theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "down" :
//                theMon.getMySolidArea().y += theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "left" :
//                theMon.getMySolidArea().x -= theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "right" :
//                theMon.getMySolidArea().x += theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//        }
//        theMon.resetSolidArea();
//        myGamePanel.getMyHero().resetSolidArea();
//    }

