package app.simplexdev.arcanumocculta.api.spell;

import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.spell.enums.Damages;
import app.simplexdev.arcanumocculta.api.spell.enums.Durations;
import app.simplexdev.arcanumocculta.api.spell.enums.ManaCosts;
import java.util.SplittableRandom;
import org.bukkit.Bukkit;

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
}
