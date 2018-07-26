package net.runelite.client.plugins.pk;

import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.poh.PohIcons;
import net.runelite.client.plugins.timers.GameTimer;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Singleton
public class PkOverlay extends Overlay {

    private final Client client;
    PkPlugin plugin;
    PkConfig config;
    BufferedImage image;
    SpriteManager manager;

    @Inject
    public PkOverlay(PkPlugin plugin, PkConfig config, SpriteManager manager, Client client) {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
        this.manager = manager;
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        for (Player player : client.getCachedPlayers()) {
            TimerCollection coll = plugin.getFreezeTimers().get(player);
            if (coll != null) {
                renderPlayerOverlay(graphics, player, coll);
            }
        }
      //  plugin.getFreezeTimers().forEach((p, c) -> renderPlayerOverlay(graphics, p, c));
        return null;
    }

    private void renderPlayerOverlay(Graphics2D graphics, Player player, TimerCollection collection) {
        //TODO figure out how to render to the precise location
        for (int i=0; i<collection.getTimers().size(); ++i) {
            if (player != null) {
                Point point = player.getCanvasImageLocation(graphics, collection.getTimers().get(i).getImage(manager), 0);
                if (point != null) {
                    Point realpoint = new Point(point.getX() + i * 40, point.getY());
                    OverlayUtil.renderImageLocation(graphics, realpoint, collection.getTimers().get(i).getImage(manager));
                }
                graphics.setFont(new Font("default", Font.BOLD, 14));
                Point textPoint = player.getCanvasTextLocation(graphics, collection.getTimers().get(i).getTimeLeftInSeconds(), 0);
                if (textPoint != null) {
                    Point anotherRealPoint = new Point(textPoint.getX() + 20 + i * 40, textPoint.getY());
                    OverlayUtil.renderTextLocation(graphics, anotherRealPoint, collection.getTimers().get(i).getTimeLeftInSeconds(), Color.ORANGE);
                }
            }
        }
    }

    }