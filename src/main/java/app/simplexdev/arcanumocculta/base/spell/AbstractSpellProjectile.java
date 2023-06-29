package app.simplexdev.arcanumocculta.base.spell;

import app.simplexdev.arcanumocculta.api.effect.CompoundEffect;
import app.simplexdev.arcanumocculta.api.effect.Effect;
import app.simplexdev.arcanumocculta.api.effect.SpecialEffect;
import app.simplexdev.arcanumocculta.api.effect.SpellEffect;
import app.simplexdev.arcanumocculta.api.player.Caster;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.api.spell.SpellProjectile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractSpellProjectile<T extends Projectile> implements SpellProjectile<T> {

    private static final List<EntityType> accepted = List.of(
            EntityType.ARROW,
            EntityType.SPECTRAL_ARROW,
            EntityType.FIREBALL,
            EntityType.DRAGON_FIREBALL,
            EntityType.SMALL_FIREBALL,
            EntityType.WITHER_SKULL,
            EntityType.ENDER_PEARL,
            EntityType.SNOWBALL,
            EntityType.EGG,
            EntityType.TRIDENT,
            EntityType.FIREWORK,
            EntityType.LLAMA_SPIT,
            EntityType.SHULKER_BULLET,
            EntityType.FISHING_HOOK,
            EntityType.SPLASH_POTION,
            EntityType.THROWN_EXP_BOTTLE);

    private final Class<T> projectile;
    private final List<Particle> particles;
    private final Caster caster;
    private final Spell<?> spell;

    protected AbstractSpellProjectile(final Spell<?> spell, final Caster caster, final Class<T> projectile, final Particle... particles) {
        this.projectile = projectile;
        this.particles = Arrays.asList(particles);
        this.caster = caster;
        this.spell = spell;
    }

    @Override
    public Class<T> getProjectileType() {
        return projectile;
    }

    @Override
    public List<Particle> getParticles() {
        return particles;
    }

    @Override
    public void cast(Location target) {
        final Vector velocity = caster.bukkit().getEyeLocation().getDirection().multiply(2);
        final T launched = caster.bukkit().launchProjectile(getProjectileType(), velocity);

        while (!launched.isDead()) {
            final double randomXOffset = Math.sin((Math.random() * 10 - 2) % Math.PI) * 5;
            final double randomYOffset = ((Math.random() * 10 - 2) % Math.PI) * 5;
            final double randomZOffset = Math.cos((Math.random() * 10 - 2) % (2 * Math.PI)) * 5;
            particles.forEach(particle -> launched.getWorld().spawnParticle(particle, launched.getLocation(), 1, randomXOffset, randomYOffset, randomZOffset));

            if (launched.isOnGround()
                    || launched.isDead()
                    || launched.getTicksLived() > 100
                    || launched.getLocation().clone().equals(target)) {
                break;
            }
        }

        final Effect effect = spell.getEffect();
        if (effect instanceof SpellEffect sp) {
            PotionEffect e = sp.getEffectType().createEffect((int) sp.getDuration().getSeconds(), (int) sp.getAmplifier());
            AreaEffectCloud cloud = (AreaEffectCloud) target.getWorld().spawnEntity(target, EntityType.AREA_EFFECT_CLOUD);
            cloud.addCustomEffect(e, true);
            cloud.setRadius(2.5F);
            cloud.setDuration((int) sp.getDuration().getSeconds());
            cloud.setSource(caster.bukkit());
        } else if (effect instanceof CompoundEffect sp) {
            final List<PotionEffect> effects = new ArrayList<>();
            for (PotionEffectType type : sp.getEffectTypes()) {
                PotionEffect e = type.createEffect((int) sp.getDuration().getSeconds(), (int) sp.getAmplifier());
                effects.add(e);
            }
            AreaEffectCloud cloud = (AreaEffectCloud) target.getWorld().spawnEntity(target, EntityType.AREA_EFFECT_CLOUD);
            for (PotionEffect e : effects) {
                cloud.addCustomEffect(e, true);
            }
            cloud.setRadius(2.5F);
            cloud.setDuration((int) sp.getDuration().getSeconds());
            cloud.setSource(caster.bukkit());
        }
    }

    @Override
    public void cast(LivingEntity target) {
        final Vector velocity = caster.bukkit().getEyeLocation().getDirection().multiply(2);
        final T launched = caster.bukkit().launchProjectile(getProjectileType(), velocity);

        while (!launched.isDead()) {
            final double randomXOffset = Math.sin((Math.random() * 10 - 2) % Math.PI) * 5;
            final double randomYOffset = ((Math.random() * 10 - 2) % Math.PI) * 5;
            final double randomZOffset = Math.cos((Math.random() * 10 - 2) % (2 * Math.PI)) * 5;
            particles.forEach(particle -> launched.getWorld().spawnParticle(particle, launched.getLocation(), 1, randomXOffset, randomYOffset, randomZOffset));

            if (launched.isOnGround()
                    || launched.isDead()
                    || launched.getTicksLived() > 100
                    || launched.getLocation().clone().equals(target)) {
                break;
            }
        }

        final Effect effect = spell.getEffect();
        if (effect instanceof SpellEffect sp) {
            PotionEffect e = sp.getEffectType().createEffect((int) sp.getDuration().getSeconds(), (int) sp.getAmplifier());
            target.addPotionEffect(e);
        } else if (effect instanceof CompoundEffect sp) {
            final List<PotionEffect> effects = new ArrayList<>();
            for (PotionEffectType type : sp.getEffectTypes()) {
                PotionEffect e = type.createEffect((int) sp.getDuration().getSeconds(), (int) sp.getAmplifier());
                effects.add(e);
            }
            target.addPotionEffects(effects);
        } else if (effect instanceof SpecialEffect sp) {
            sp.applyEffect(target);
        }
    }
}
