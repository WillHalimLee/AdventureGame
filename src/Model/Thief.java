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
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Thief extends Heroes {

//    private static final long serialVersionUID = 123456789L;

    private static final long serialversionUID = 1234567998L;

    /**
     * The chance at which the surprise attack succeeds.
     */
    private static final double SURPRISE_ATTACK_CHANCE = 0.9;

    /**
     * The chance at which it fails and thief gets caught.
     */
    private static final double SURPRISE_FAIL_CHANCE = 0.1;

    /**
     * Hp of thief.
     */
    private static final int HP = 80;

    /**
     * The name of the character.
     */
    private static final String NAME = "Thief";

    /**
     * Setting this characters attack speed to 6.
     */
    private static final int ATTACK_SPEED = 6;

    /**
     * Minimum damage of thief.
     */
    private static final int MIN_DAMAGE = 20;

    /**
     * Maximum damage of thief.
     */
    private static final int MAX_DAMAGE = 50;

    /**
     * Hit chance of thief.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * Block chance of thief.
     */
    private static final double BLOCK_CHANCE = 0.7;


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
