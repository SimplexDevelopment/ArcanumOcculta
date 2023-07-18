package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import org.bukkit.entity.LivingEntity;

@FunctionalInterface
public interface SpellEffect
{
    void apply(final LivingEntity target, final Caster caster);
}
