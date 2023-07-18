package app.simplexdev.arcanumocculta.cooldown;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CooldownService
{
    private final Map<Caster, List<CooldownStatus>> cooldowns = new HashMap<>();

    public void tick()
    {
        cooldowns.forEach((c, sl) -> {
            sl.removeIf(CooldownStatus::hasExpired);

            if (sl.isEmpty()) cooldowns.remove(c);
        });
    }

    public void include(final Caster caster, final CooldownStatus status)
    {
        List<CooldownStatus> statuses = cooldowns.get(caster);
        if (status == null) statuses = new ArrayList<>();

        statuses.add(status);
        cooldowns.put(caster, statuses);
    }

    public void exclude(final CooldownStatus status)
    {
        cooldowns.forEach((c, sl) -> sl.removeIf(s -> s.getSpellUUID().equals(status.getSpellUUID())));
    }

    public boolean isOnCooldown(final Spell spell)
    {
        tick();
        return cooldowns.values()
                        .stream()
                        .flatMap(List::stream)
                        .anyMatch(status -> status.getSpellUUID().equals(spell.getUniqueId()));
    }
}
