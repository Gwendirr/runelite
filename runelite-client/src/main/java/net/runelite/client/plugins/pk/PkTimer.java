package net.runelite.client.plugins.pk;

import lombok.Getter;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class PkTimer {

    @Getter
    private long timeLeft;

    @Getter
    private long immunity;

    @Getter
    private final int imageId;

    @Getter
    private PkTimersEnum basedOn;

    private BufferedImage image;


    public PkTimer(PkTimersEnum basedOn) {
        this.basedOn = basedOn;
        timeLeft = basedOn.getDuration();
        immunity = basedOn.getImmunity();
        imageId = basedOn.getImageId();
    }

    public boolean decrementByOneTick() {
        timeLeft -= 600;
        return timeLeft < -immunity;
    }

    public String getTimeLeftInSeconds() {
        return Long.toString(TimeUnit.MILLISECONDS.toSeconds(timeLeft));
    }

    BufferedImage getImage(SpriteManager manager)
    {
        if (image != null)
        {
            return image;
        }
        image = manager.getSprite(imageId, 0);
        return image;
    }

}
