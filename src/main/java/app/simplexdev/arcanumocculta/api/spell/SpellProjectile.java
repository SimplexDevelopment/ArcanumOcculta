package app.simplexdev.arcanumocculta.api.spell;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import java.util.List;

public interface SpellProjectile<T extends Projectile> {
    Class<T> getProjectileType();

    List<Particle> getParticles();

    void cast(final Location target);

    void cast(final LivingEntity target);
}
