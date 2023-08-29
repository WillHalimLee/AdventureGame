package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class TileManager {
    /**
     * The main game panel.
     */
    private final GamePanel myGamePanel;
    /**
     * An array of tiles that holds different types of tiles.
     */
    private final Tile[] myTile;
    /**
     * A 2d array made from a text file.
     */
    private final int myMapArr[][][];
    private final int myNumberOfMaps = 10;
    /**
     * Constructor that creates the TileManage object.
     * @param theGamePanel the main game panel.
     */
    public TileManager(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myTile = new Tile[50];

        myMapArr = new int[myNumberOfMaps][myGamePanel.getMyWorldRow()][myGamePanel.getMyWorldCol()];
        getTileImage();
        loadMap("/map/Entrance.txt",0);
        loadMap("/map/WorldFloor01.txt",1);
    }

    /**
     * Set the tile's to their images.
     */
    public void getTileImage() {
        try {
            myTile[0] = new Tile();
            myTile[0].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/blackBackGround.png"))));
            myTile[1] = new Tile();
            myTile[1].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/drkStoneWall.png"))));
            myTile[1].setMyCollision(true);
            myTile[2] = new Tile();
            myTile[2].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/stoneFloor.png"))));
            myTile[3] = new Tile();
            myTile[3].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/drk_door.png"))));
            myTile[3].setMyCollision(true);
            myTile[4] = new Tile();
            myTile[4].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/drk_stoneFloor.png"))));
            myTile[5] = new Tile();
            myTile[5].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/entrance_stairs.png"))));
            myTile[6] = new Tile();
            myTile[6].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/entrance_lantern_l.png"))));
            myTile[7] = new Tile();
            myTile[7].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/entrance_lantern_r.png"))));
            myTile[8] = new Tile();
            myTile[8].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/grass.png"))));
            myTile[9] = new Tile();
            myTile[9].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/water.png"))));
            myTile[9].setMyCollision(true);
            myTile[10] = new Tile();
            myTile[10].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/log.png"))));
            myTile[10].setMyCollision(true);
            myTile[11] = new Tile();
            myTile[11].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandRoad0.png"))));
            myTile[12] = new Tile();
            myTile[12].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandRoad1.png"))));
            myTile[13] = new Tile();
            myTile[13].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandRoad2.png"))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the map array.
     * @return a 2d array that represents the dungeon map.
     */
    public int[][][] getMyMapArray() {
        return myMapArr;
    }

    /**
     * Returns the tile array.
     * @return the tile array.
     */
    public Tile[] getMyTile() {
        return myTile;
    }

    /**
     * Loads the dungeon.txt file and converts it to a 2d array.
     * @param theMap the dungeon.txt file.
     */
    public void loadMap(final String theMap, int theMapNumber) {
        int col = 0;
        int row = 0;
        try {
            InputStream input = getClass().getResourceAsStream(theMap);
            Scanner scan = new Scanner(Objects.requireNonNull(input));
            while (col < myGamePanel.getMyWorldCol() && row < myGamePanel.getMyWorldRow()) {
                int number = scan.nextInt();
                myMapArr[theMapNumber][row][col] = number;
                col++;
                if (col == myGamePanel.getMyWorldCol()) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws the world map.
     * @param theGraphics the pen used to draw.
     */
    public void draw(final Graphics2D theGraphics) {
        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < myGamePanel.getMyWorldCol() && worldRow < myGamePanel.getMyWorldRow()) {
            int tileTexture = myMapArr[myGamePanel.getCurrentMap()][worldRow][worldCol];

            int worldX = worldCol * myGamePanel.getSpriteSize();
            int worldY = worldRow * myGamePanel.getSpriteSize();

            int screenX = worldX - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
            int screenY = worldY - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

            // draw only the tiles that are width a certain screen size.
            if (worldX + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                    worldX - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                    worldY + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                    worldY - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
                theGraphics.drawImage(myTile[tileTexture].getMyImage(), screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
            }
            worldCol++;
            if (worldCol == myGamePanel.getMyWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
