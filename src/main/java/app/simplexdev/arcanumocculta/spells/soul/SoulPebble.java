package app.simplexdev.arcanumocculta.spells.soul;

import app.simplexdev.arcanumocculta.util.SpellUtils;
import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.SpellEffect;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public final class SoulPebble extends AbstractSpell
{
    public SoulPebble()
    {
        super("Soul Pebble",
              "soul_pebble",
              "Fires a small soul pebble",
              1,
              1.0,
            1L,
              10.0,
              5L);
    }

    @Override
    public SpellEffect[] getSpellEffects()
    {
        final SpellEffect[] effects = new SpellEffect[1];
        effects[0] = (target, caster) ->
        {
            final Wand wand = caster.getWand();
            final double damage = wand.getSpellBonus() * this.baseDamage();

            SpellUtils.damage(target, caster.bukkit(), damage);
        };
        return effects;
    }

    @Override
    public void cast(Caster caster, Wand wand)
    {
        if (this.manaCost() > caster.getCurrentMana())
        {
            caster.bukkit().sendMessage("You do not have enough mana to cast this spell!");
            return;
        }

        final Player player = caster.bukkit();
        final double expMod = CasterLevel.fromOrdinal(this.getLevelRequirement()).getExperienceMarker();
        final Projectile projectile = player.launchProjectile(Projectile.class);
        projectile.setVelocity(player.getLocation().getDirection().multiply(2));
        caster.removeMana(this.manaCost());
        caster.addExperience(random().nextDouble(expMod * 0.25));

        while (!projectile.isOnGround() || !projectile.isDead())
        {
            projectile.getWorld().spawnParticle(Particle.SOUL, projectile.getLocation(), 10,
                                                random().nextGaussian() * 0.8,
                                                random().nextGaussian() * 0.8,
                                                random().nextGaussian() * 0.8);
        }

        projectile.getNearbyEntities(1, 1, 1)
                  .stream()
                  .filter(LivingEntity.class::isInstance)
                  .map(LivingEntity.class::cast)
                  .forEach(entity -> SpellUtils.applyEffects(
                      this.getSpellEffects(),
                      entity,
                      caster));
    }
}
