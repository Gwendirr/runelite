package net.runelite.client.plugins.pk;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.SpriteID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Slf4j
public enum PkTimersEnum {

    BARRAGE(SpriteID.SPELL_ICE_BARRAGE, TimeUnit.SECONDS.toMillis(20), TimeUnit.SECONDS.toMillis(3), true),
    BLITZ(SpriteID.SPELL_ICE_BLITZ, TimeUnit.SECONDS.toMillis(15), TimeUnit.SECONDS.toMillis(3), true),
    BURST(SpriteID.SPELL_ICE_BURST, TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(3), true),
    RUSH(SpriteID.SPELL_ICE_RUSH, TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(3), true),
    ENTANGLE(SpriteID.SPELL_ENTANGLE, TimeUnit.SECONDS.toMillis(15), TimeUnit.SECONDS.toMillis(3), true),
    HALFENTANGLE(SpriteID.SPELL_ENTANGLE, TimeUnit.SECONDS.toMillis(8), TimeUnit.SECONDS.toMillis(3), true),
    TB(SpriteID.SPELL_TELE_BLOCK, TimeUnit.MINUTES.toMillis(5), TimeUnit.SECONDS.toMillis(60), false),
    HALFTB(SpriteID.SPELL_TELE_BLOCK, TimeUnit.SECONDS.toMillis(150), TimeUnit.SECONDS.toMillis(60), false),
    VENG(SpriteID.SPELL_VENGEANCE, TimeUnit.SECONDS.toMillis(30), TimeUnit.SECONDS.toMillis(0), false),;

    @Getter
    private final int imageId;

    @Getter
    private final long duration;

    @Getter
    private final long immunity;

    @Getter
    private final boolean isFreeze;


    PkTimersEnum(int imageId, long duration, long immunity, boolean isFreeze) {
        this.imageId = imageId;
        this.duration = duration;
        this.immunity = immunity;
        this.isFreeze = isFreeze;
    }



}
