package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Sign extends Item{

    /**
     * Constructs an item.
     *
     * @param theGP The 'GamePanel' to draw the item onto.
     */
    public Sign(GamePanel theGP) {
        super(theGP);
        setItemImage();
    }

    @Override
    public void setItemImage() {
        try {
            myItemImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sign0.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
