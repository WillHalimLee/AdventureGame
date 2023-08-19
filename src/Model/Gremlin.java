package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * This class creates the Gremlin monster object.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Gremlin extends Monster {
    /**
     * The constructor of Monster that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     */
    //Testing out a new Sqlite database implemented way
    protected Gremlin(GamePanel theGamePanel) {
        super(theGamePanel);
        setEntityImage();
    }
    /**
     * Override method that return the gremlins image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
