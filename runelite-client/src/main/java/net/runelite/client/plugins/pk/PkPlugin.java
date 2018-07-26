package net.runelite.client.plugins.pk;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Provides;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.*;

@PluginDescriptor(
        name = "Pk plugin",
        description = "Freeze timer etc",
        tags = {"pk"}
)
public class PkPlugin extends Plugin {

    private static final int TELEBLOCK = 345;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PkOverlay overlay;

    @Inject
    private Client client;

    @Provides
    PkConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PkConfig.class);
    }

    @Getter
    private Map<Player, TimerCollection> freezeTimers = new HashMap<>();


    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
    }


    @Subscribe
    public void onGraphicChanged(GraphicChanged event) {
        Actor actor = event.getActor();
        Player player;
        if (actor instanceof Player) {
            player = (Player) actor;
            freezeTimers.putIfAbsent(player, new TimerCollection());
            if (player.getGraphic() == GraphicID.ICE_BARRAGE) {
                    freezeTimers.get(player).registerTimere(PkTimersEnum.BARRAGE);
            } else if (player.getGraphic() == GraphicID.VENGEANCE) {
                freezeTimers.get(player).registerTimere(PkTimersEnum.VENG);
            } else if (player.getGraphic() == TELEBLOCK) {
                if (player.getOverheadIcon() == HeadIcon.MAGIC) {
                    freezeTimers.get(player).registerTimere(PkTimersEnum.HALFTB);
                } else {
                    freezeTimers.get(player).registerTimere(PkTimersEnum.TB);
                }
            }
            else if (player.getGraphic() == GraphicID.ICE_BLITZ) {
                freezeTimers.get(player).registerTimere(PkTimersEnum.BLITZ);
            } else if (player.getGraphic() == GraphicID.ENTANGLE) {
                if (player.getOverheadIcon() == HeadIcon.MAGIC) {
                    freezeTimers.get(player).registerTimere(PkTimersEnum.HALFENTANGLE);
                } else {
                    freezeTimers.get(player).registerTimere(PkTimersEnum.ENTANGLE);
                }
            }
            }
        }


    @Subscribe
    public void onGameTick(GameTick tick) {
        Iterator it = freezeTimers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Player, TimerCollection> entry = (Map.Entry<Player, TimerCollection>) it.next();
            if (entry.getValue().decrementByOneTick()) {
                it.remove();
            }
        }
    }


}