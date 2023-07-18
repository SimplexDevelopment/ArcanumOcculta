package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import java.util.List;
import java.util.SplittableRandom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    public void applyEffects(final List<Entity> targets, final Caster caster)
    {
        targets.stream()
               .filter(LivingEntity.class::isInstance)
               .map(LivingEntity.class::cast)
               .forEach(entity -> applyEffectsIndividually(
                   getSpellEffects(),
                   entity,
                   caster));
    }

    public Entity prepareProjectile(final Caster caster, final Material visual)
    {
        final double expMod = getLevelRequirement().getExperienceMarker();

        final Player player = caster.bukkit();
        final Location location = player.getLocation().clone().add(0, player.getEyeHeight(), 0);
        final Vector velocity = player.getLocation().getDirection().multiply(2);
        final Entity projectile = createProjectile(visual, player.getWorld(), location,
                                                              velocity);
        caster.removeMana(manaCost().getManaCost());
        caster.addExperience(random().nextDouble(expMod * 0.25));
        return projectile;
    }

    public void tracer(final World world, final Location location, final Particle particle)
    {
        world.spawnParticle(particle,
                            location,
                            0,
                            random().nextDouble(-2, 2),
                            random().nextDouble(-2, 2),
                            random().nextDouble(-2, 2));
    }

    private void applyEffectsIndividually(final SpellEffect[] effects, final LivingEntity target,
                                          final Caster caster)
    {
        for (final SpellEffect effect : effects)
            effect.apply(target, caster);
    }

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
}
