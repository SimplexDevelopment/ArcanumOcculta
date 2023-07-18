package app.simplexdev.arcanumocculta.spells.soul;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
        effects[0] = (target, caster) ->
        {
            final Wand wand = caster.getWand();
            final double damage = baseDamage().multiply(wand.getSpellBonus());

            SpellUtils.damage(target, caster.bukkit(), damage);
        };
        return effects;
    }

    @Override
    public void cast(Caster caster, Wand wand)
    {
        if (this.manaCost().isMoreThan(caster.getCurrentMana()))
        {
            caster.bukkit().sendMessage("You do not have enough mana to cast this spell!");
            return;
        }

        final double expMod = this.getLevelRequirement().getExperienceMarker();

        final Player player = caster.bukkit();
        final Location location = player.getLocation().clone().add(0, player.getEyeHeight(), 0);
        final Vector velocity = player.getLocation().getDirection().multiply(2);
        final ItemDisplay projectile = SpellUtils.createProjectile(Material.AIR, player.getWorld(), location,
                                                                   velocity);
        caster.removeMana(this.manaCost().getManaCost());
        caster.addExperience(random().nextDouble(expMod * 0.25));

        while (!projectile.isOnGround() || !projectile.isDead())
        {
            projectile.getWorld().spawnParticle(Particle.SOUL, projectile.getLocation(), 1,
                                                random().nextGaussian() * 0.2,
                                                random().nextGaussian() * 0.2,
                                                random().nextGaussian() * 0.2);

            if (!projectile.getNearbyEntities(1, 1, 1).isEmpty()) {
                projectile.getNearbyEntities(1, 1, 1)
                          .stream()
                          .filter(LivingEntity.class::isInstance)
                          .map(LivingEntity.class::cast)
                          .forEach(entity -> SpellUtils.applyEffects(
                              this.getSpellEffects(),
                              entity,
                              caster));
                projectile.remove();
                break;
            }
        }

        if (projectile.isOnGround()) projectile.remove();
    }
}
