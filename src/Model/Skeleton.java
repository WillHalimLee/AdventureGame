package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Skeleton class that extends monster one of the mobs
 * for the game.
 *
 * @author Halim Lee
 * @version July 2023.
 */
public class Skeleton extends Monster {

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     */
    protected Skeleton(GamePanel theGamePanel) {
        super(theGamePanel);
        setEntityImage();
    }

    /**
     * Returns the monsters image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
