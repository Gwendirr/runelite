package net.runelite.client.plugins.pk;

import lombok.Getter;

import java.util.*;

public class TimerCollection {

    @Getter
    private List<PkTimer> timers = new ArrayList<>();

    private boolean isFrozen;

    public void registerTimere(PkTimersEnum timerEnum) {
        if (timerEnum.isFreeze() && isFrozen) {
            return;
        }

        if (timerEnum.isFreeze()) {
            isFrozen = true;
        }

        timers.add(new PkTimer(timerEnum));
    }


    public boolean decrementByOneTick() {
        Iterator it = timers.iterator();
        while (it.hasNext()) {
            PkTimer collection = (PkTimer) it.next();
            if (collection.decrementByOneTick()) {
                if (collection.getBasedOn().isFreeze()) {
                    isFrozen = false;
                }
                it.remove();
            }
        }
        return timers.isEmpty();
    }



}
