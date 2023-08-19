package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;


/**
 * Priestess class which has the ability to heal and
 * extends from the Heroes class.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */

public class Priestess extends Heroes {

    /**
     * Priestess constructor which sets up all her stats
     * in hp, name, attack speed, damage, hit chance and block chance.
     * Also sets up background for the characters for the view.
     *
     * @param theGamePanel gamepanel of character
     * @param theKeyboard  keyboard input of character
     */
    public Priestess(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(theGamePanel, theKeyboard);
        setEntityImage();
    }

    /**
     * Sets the players image.
     */
    @Override
    public void setEntityImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
