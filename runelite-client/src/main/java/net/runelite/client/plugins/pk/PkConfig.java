package net.runelite.client.plugins.pk;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pk")
public interface PkConfig extends Config {
    @ConfigItem(
            position = 0,
            keyName = "freezetimer",
            name = "Freezetimer on opponents",
            description = "Freeze timer on opponents"
    )
    default boolean opponentFreezeTimer()
    {
        return true;
    }
}
