package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Thief class which extends from Heroes.
 *
 * @author Halim Lee
 * @version July 2023.
 */
public class Thief extends Heroes {

    /**
     * Thief constructor which sets up all the stats for the character including
     * some background gui.
     *
     * @param theGamePanel gamepanel of character
     * @param theKeyboard  keyboard input of character
     */
    public Thief(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(theGamePanel, theKeyboard);
        setEntityImage();
    }

    /**
     * Return the heroes image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
