package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Warrior class that extends Heroes.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Warrior extends Heroes {

    /**
     * Warrior constructor that sets all the stats for
     * the character along with some background gui.
     *
     * @param theGamePanel game panel of character
     * @param theKeyboard  keyboard input of character
     */
    public Warrior(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(theGamePanel, theKeyboard);
        setEntityImage();
    }

    /**
     * Returns the heroes image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_right2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
