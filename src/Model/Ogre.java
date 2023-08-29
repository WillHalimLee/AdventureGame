package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * An Ogre class that extends from Monster.
 *
 * @author Halim Lee
 * @version July 2023.
 */
public class Ogre extends Monster {

    /**
     * The constructor of Monster that sets up the stats for Ogre class.
     */
    protected Ogre(final GamePanel theGamePanel) {
        super(theGamePanel);
        setEntityImage();
    }
    /**
     * Override method that returns the monsters image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
