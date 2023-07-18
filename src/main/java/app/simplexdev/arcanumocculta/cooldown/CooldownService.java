package app.simplexdev.arcanumocculta.cooldown;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import java.util.HashSet;
import java.util.Set;

public class CooldownService
{
    private final Set<CooldownStatus> cooldowns = new HashSet<>();

    public void tick()
    {
        cooldowns.removeIf(CooldownStatus::hasExpired);
    }

    public void include(final CooldownStatus status)
    {
        cooldowns.add(status);
    }

    public void exclude(final CooldownStatus status)
    {
        cooldowns.remove(status);
    }

    public boolean isOnCooldown(final Spell spell)
    {
        this.cooldowns.removeIf(CooldownStatus::hasExpired);
        return cooldowns.stream()
                        .anyMatch(status -> status.getSpellUUID().equals(spell.getUniqueId()));
    }
}
