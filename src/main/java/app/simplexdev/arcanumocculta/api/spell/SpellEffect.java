package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import org.bukkit.entity.LivingEntity;

@FunctionalInterface
public interface SpellEffect
{
    /**
     * Apply this spell effect to the given target, from the given caster.
     *
     * @param target The target of the spell.
     * @param caster The caster of the spell.
     */
    void apply(final LivingEntity target, final Caster caster);
}
