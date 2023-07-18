package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import java.util.SplittableRandom;
import org.bukkit.Bukkit;

public abstract class AbstractSpell implements Spell
{
    private final String name;
    private final String id;
    private final String description;
    private final int levelRequirement;
    private final double baseDamage;
    private final long effectDuration;
    private final double manaCost;
    private final long coolDown;
    private final SplittableRandom random = new SplittableRandom();

    protected AbstractSpell(final String name, final String id,
                            final String description, final int levelRequirement,
                            final double baseDamage, final long effectDuration, final double manaCost,
                            final long coolDown)
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
    public int getLevelRequirement()
    {
        return levelRequirement;
    }

    @Override
    public double baseDamage()
    {
        return baseDamage;
    }

    @Override
    public long effectDuration()
    {
        return effectDuration;
    }

    @Override
    public double manaCost()
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
            && caster.getCurrentLevel().getLevel() >= this.levelRequirement;
    }

    protected SplittableRandom random()
    {
        return random;
    }
}
