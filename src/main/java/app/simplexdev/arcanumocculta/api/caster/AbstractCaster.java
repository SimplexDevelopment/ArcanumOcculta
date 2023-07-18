package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.wand.Wand;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class AbstractCaster implements Caster
{
    private final SpellBook spellBook;
    private final UUID playerUUID;
    private final String name;
    private Wand wand;
    private CasterLevel level;
    private double currentExperience;
    private double currentMana;
    private double maxMana;

    protected AbstractCaster(final Player player, final Wand wand, final SpellBook spellBook, final CasterLevel level)
    {
        this.spellBook = spellBook;
        this.playerUUID = player.getUniqueId();
        this.name = player.getName();
        this.wand = wand;
        this.level = level;
    }

    @Override
    public Wand getWand()
    {
        return this.wand;
    }

    public void setWand(final Wand wand)
    {
        this.wand = wand;
    }

    @Override
    public SpellBook getSpellBook()
    {
        return this.spellBook;
    }

    @Override
    public UUID getUniqueId()
    {
        return this.playerUUID;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public CasterLevel getCurrentLevel()
    {
        return this.level;
    }

    public void setCurrentLevel(final CasterLevel level)
    {
        this.level = level;
    }

    @Override
    public double getCurrentExperience()
    {
        return this.currentExperience;
    }

    public void setCurrentExperience(final double experience)
    {
        this.currentExperience = experience;
    }

    @Override
    public double getCurrentMana()
    {
        return this.currentMana;
    }

    public void setCurrentMana(final double mana)
    {
        this.currentMana = mana;
    }

    @Override
    public double getMaxMana()
    {
        return this.maxMana;
    }

    @Override
    public void setMaxMana(final double mana)
    {
        this.maxMana = mana;
    }

    public void addExperience(final double experience)
    {
        this.currentExperience = this.currentExperience + experience;
    }

    public void removeExperience(final double experience)
    {
        this.currentExperience = this.currentExperience - experience;
    }

    public void addMana(final double mana)
    {
        this.currentMana = this.currentMana + mana;
    }

    public void removeMana(final double mana)
    {
        this.currentMana = this.currentMana - mana;
    }

    public void setManaToMax()
    {
        this.currentMana = this.maxMana;
    }

    public void setExperienceToZero()
    {
        this.currentExperience = 0;
    }

    @Override
    public Player bukkit()
    {
        return Bukkit.getPlayer(this.playerUUID);
    }
}
