package app.simplexdev.arcanumocculta.spells.soul;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

public final class SoulPebble extends AbstractSpell
{
    public SoulPebble()
    {
        super("Soul Pebble",
              "soul_pebble",
              "Fires a small soul pebble",
              CasterLevel.APPRENTICE,
              Damages.MINIMAL,
              Durations.INSTANT,
              ManaCosts.MINIMAL_CAST,
              5L);
    }

    @Override
    public SpellEffect[] getSpellEffects()
    {
        final SpellEffect[] effects = new SpellEffect[1];
        effects[0] = SpellUtils.soulEffectBase(baseDamage());
        return effects;
    }

    @Override
    public void cast(Caster caster, Wand wand)
    {
        if (!this.checkManaCosts(caster))
        {
            return;
        }

        final Entity projectile = prepareProjectile(caster, Material.AIR,
                                                    tracerVector(caster));

        while (!projectile.isOnGround() || !projectile.isDead())
        {
            tracerDirectional(projectile.getWorld(), projectile.getLocation(), Particle.SOUL);

            if (!projectile.getNearbyEntities(1, 1, 1).isEmpty())
            {
                applyEffects(projectile.getNearbyEntities(1, 1, 1),
                             caster);
                projectile.remove();
            }

            if (projectile.isOnGround())
                projectile.remove();
        }
    }
}
