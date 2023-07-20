package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import java.util.List;
import java.util.SplittableRandom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

public abstract class AbstractSpell implements Spell
{
    private final String name;
    private final String id;
    private final String description;
    private final CasterLevel levelRequirement;
    private final Damages baseDamage;
    private final Durations effectDuration;
    private final ManaCosts manaCost;
    private final long coolDown;
    private final SplittableRandom random = new SplittableRandom();

    /**
     * Creates a new spell with the given parameters.
     *
     * @param name             The display name of the spell.
     * @param id               The actual id of the spell, typically the display name in all lowercase
     *                         separated by underscores instead of spaces.
     * @param description      The description of the spell.
     * @param levelRequirement The level requirement (using {@link CasterLevel}) of the spell.
     * @param baseDamage       The base damage (using {@link Damages}) of the spell.
     * @param effectDuration   The duration (using {@link Durations}) of the spell's effects.
     * @param manaCost         The mana cost (using {@link ManaCosts}) of the spell.
     * @param coolDown         The cool down of the spell in seconds.
     */
    protected AbstractSpell(final String name, final String id,
                            final String description, final CasterLevel levelRequirement,
                            final Damages baseDamage, final Durations effectDuration,
                            final ManaCosts manaCost, final long coolDown)
    {
        this.name = name;
        this.id = id;
        this.description = description;
        this.levelRequirement = levelRequirement;
        this.baseDamage = baseDamage;
        this.effectDuration = effectDuration;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
    }

    /**
     * Creates the actual "projectile," which is just an item display entity with a velocity.
     *
     * @param visual   The visual of the projectile
     * @param world    The world to spawn the projectile in
     * @param location The location to spawn the projectile at
     * @param velocity The velocity of the projectile
     * @return The projectile entity
     */
    private static ItemDisplay createProjectile(final Material visual, final World world, final Location location,
                                                final Vector velocity)
    {
        final ItemDisplay display = (ItemDisplay) world.spawnEntity(location, EntityType.ITEM_DISPLAY);
        display.setItemStack(new ItemStack(visual));
        display.setGravity(true);
        display.setPersistent(false);
        display.setSilent(true);
        display.setShadowRadius(0);
        display.setShadowStrength(0);
        display.setVelocity(velocity);
        return display;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public CasterLevel getLevelRequirement()
    {
        return levelRequirement;
    }

    @Override
    public Damages baseDamage()
    {
        return baseDamage;
    }

    @Override
    public Durations effectDuration()
    {
        return effectDuration;
    }

    @Override
    public ManaCosts manaCost()
    {
        return manaCost;
    }

    @Override
    public long coolDown()
    {
        return coolDown;
    }

    @Override
    public Spell dupe()
    {
        try
        {
            return this.getClass()
                       .getDeclaredConstructor()
                       .newInstance();
        }
        catch (ReflectiveOperationException e)
        {
            Bukkit.getLogger().severe(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isUnlocked(Caster caster)
    {
        return caster.getSpellBook().hasSpell(this)
            && caster.getCurrentLevel().isAtLeast(this.getLevelRequirement());
    }

    /**
     * @return A singular {@link SplittableRandom} instance for this spell.
     */
    protected SplittableRandom random()
    {
        return random;
    }

    /**
     * Checks if the caster has enough mana to cast this spell.
     *
     * @param caster The caster who is casting the spell
     * @return True if the caster has enough mana, false otherwise
     */
    protected boolean checkManaCosts(final Caster caster)
    {
        boolean isValid = !manaCost().isMoreThan(caster.getCurrentMana());
        if (!isValid)
            caster.bukkit().sendMessage(ChatColor.RED + "You don't have enough mana to cast this spell!");

        return isValid;
    }

    /**
     * Applies the contents of {@link #getSpellEffects()} to the given targets.
     *
     * @param targets The targets to apply the effects to
     * @param caster  The caster who is casting the spell
     */
    public void applyEffects(final List<Entity> targets, final Caster caster)
    {
        targets.stream()
               .filter(LivingEntity.class::isInstance)
               .map(LivingEntity.class::cast)
               .forEach(entity -> applyEffectsIndividually(
                   entity,
                   caster,
                   getSpellEffects()));
    }

    /**
     * Simulates an explosion at the given location.
     *
     * @param location    The location to simulate the explosion at
     * @param size        The size of the explosion
     * @param breakBlocks Whether the explosion should break blocks or not.
     */
    public void simulateExplosion(final Location location, final float size, boolean breakBlocks)
    {
        location.getWorld().createExplosion(location, size, true, breakBlocks);
    }

    /**
     * Creates a vector that points in the direction the caster is looking.
     *
     * @param caster The caster who is casting the spell
     * @return A vector that points in the direction the caster is looking
     */
    public Vector tracerVector(final Caster caster)
    {
        return caster.bukkit().getLocation().clone().getDirection().multiply(2);
    }

    /**
     * Gets the top most block at the caster's eye location.
     *
     * @param caster The caster who is casting the spell
     * @return The top most block at the caster's eye location
     */
    public Location topLocation(final Caster caster)
    {
        final World world = caster.bukkit().getWorld();
        final Location eyeLocation = caster.bukkit().getEyeLocation().clone();
        final int diff = world.getMaxHeight() - eyeLocation.getBlockY();
        return eyeLocation.add(0, diff, 0);
    }

    /**
     * Creates a vector that points downwards.
     *
     * @param caster The caster who is casting the spell
     * @return A vector that points downwards
     */
    public Vector meteorVector(final Caster caster)
    {
        final Location topLocation = topLocation(caster);
        final Vector v = topLocation.getDirection().normalize();
        return v.multiply(new Vector(0, -5, 0));
    }

    /**
     * Creates a projectile which fires from the caster's eye height.
     *
     * @param caster   The caster who is casting the spell
     * @param visual   The visual of the projectile
     * @param velocity The velocity of the projectile
     * @return The projectile entity
     */
    public Entity prepareProjectile(final Caster caster, final Material visual, final Vector velocity)
    {
        final double expMod = getLevelRequirement().getNextLevelExp();

        final Player player = caster.bukkit();
        final Location location = player.getLocation().clone().add(0, player.getEyeHeight(), 0);
        final Entity projectile = createProjectile(visual, player.getWorld(), location,
                                                   velocity);
        caster.removeMana(manaCost().getManaCost());
        caster.addExperience(random().nextDouble(expMod * 0.25));
        return projectile;
    }

    /**
     * Spawns a single particle with a random directional offset based on the standard gaussian distribution.
     * This keeps the particles in a relatively straight line while giving them some minor movement.
     *
     * @param world    The world to spawn the particle in
     * @param location The location to spawn the particle at
     * @param particle The particle to spawn
     */
    public void tracerDirectional(final World world, final Location location, final Particle particle)
    {
        world.spawnParticle(particle,
                            location,
                            0,
                            random().nextDouble(-2, 2),
                            random().nextDouble(-2, 2),
                            random().nextDouble(-2, 2));
    }

    /**
     * Spawns a single particle with a color based on the specified R G B values.
     *
     * @param world    The world to spawn the particle in
     * @param location The location to spawn the particle at
     * @param particle The particle to spawn
     * @param r        The red value of the particle
     * @param g        The green value of the particle
     * @param b        The blue value of the particle
     */
    public void tracerRGB(final World world, final Location location,
                          final Particle particle, final int r,
                          final int g, final int b)
    {
        world.spawnParticle(particle, location, 0, r, g, b);
    }

    /**
     * Spawns a particle spiral with a radius of 2 and an incremental step of 5 at the given location.
     *
     * @param world    The world to spawn the particle in
     * @param location The location to spawn the particle at
     * @param particle The particle to spawn
     */
    public void spiral(final World world, final Location location, final Particle particle)
    {
        final double step = 0.5;
        final double radius = 2;
        final double area = Math.PI * Math.pow(radius, 2);
        final double theta = area / step;
        final double phi = step / radius;
        for (double i = 0; i < 2 * Math.PI; i += step)
        {
            final double x = theta * Math.cos(i);
            final double y = phi * Math.sin(i);
            world.spawnParticle(particle, location.clone().add(x, y, 0), 0);
        }
    }

    /**
     * Spawns a cloud effect using an area effect cloud.
     * The PotionType parameter can be null if you don't want to apply a potion effect.
     *
     * @param world    The world to spawn the cloud in
     * @param location The location to spawn the cloud at
     * @param particle The particle to use for the cloud
     * @param size     The size of the cloud
     * @param duration The duration of the cloud
     * @param effect   The potion effect to apply to the cloud, can be null.
     * @return The cloud entity
     */
    public AreaEffectCloud cloud(final World world,
                                 final Location location,
                                 final Particle particle,
                                 final float size,
                                 final int duration,
                                 final PotionType effect)
    {
        AreaEffectCloud cloud = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        cloud.setParticle(particle);
        cloud.setDuration(duration);

        if (effect != null)
        {
            cloud.setBasePotionData(new PotionData(effect));
        }

        cloud.setRadius(size);
        cloud.setRadiusPerTick(size / 0.5F);

        return cloud;
    }

    /**
     * Applies the given effects to the target.
     *
     * @param target  The target to apply the effects to
     * @param caster  The caster who is casting the spell
     * @param effects The effects to apply
     */
    protected void applyEffectsIndividually(final LivingEntity target,
                                            final Caster caster, final SpellEffect... effects)
    {
        for (final SpellEffect effect : effects)
            effect.apply(target, caster);
    }
}
