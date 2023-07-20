package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.wand.Wand;
import java.util.UUID;
import org.bukkit.entity.Player;

public interface Caster
{
    /**
     * @return The Caster's currently attuned wand.
     */
    Wand getWand();

    /**
     * Sets the Caster's currently attuned wand.
     *
     * @param wand The wand to attune to.
     */
    void setWand(Wand wand);

    /**
     * @return The Caster's spellbook.
     */
    SpellBook getSpellBook();

    /**
     * @return The Caster's name.
     */
    String getName();

    /**
     * @return The Caster's unique ID, as provided by Bukkit's {@link Player#getUniqueId()}.
     */
    UUID getUniqueId();

    /**
     * @return The Caster's current mana.
     */
    double getCurrentMana();

    /**
     * Sets the Caster's current mana.
     *
     * @param mana The amount of mana to set.
     */
    void setCurrentMana(double mana);

    /**
     * @return The Caster's maximum mana.
     */
    double getMaxMana();

    /**
     * Sets the Caster's maximum mana.
     *
     * @param maxMana The amount of mana to set.
     */
    void setMaxMana(double maxMana);

    /**
     * @return The Caster's current level.
     */
    CasterLevel getCurrentLevel();

    /**
     * Sets the Caster's current level.
     *
     * @param level The level to set.
     */
    void setCurrentLevel(CasterLevel level);

    /**
     * @return The Caster's current experience.
     */
    double getCurrentExperience();

    /**
     * Sets the Caster's current experience.
     *
     * @param experience The amount of experience to set.
     */
    void setCurrentExperience(double experience);

    /**
     * Adds mana to the Caster's current mana.
     *
     * @param mana The amount of mana to add.
     */
    void addMana(double mana);

    /**
     * Removes mana from the Caster's current mana.
     *
     * @param mana The amount of mana to remove.
     */
    void removeMana(double mana);

    /**
     * Adds experience to the Caster's current experience.
     *
     * @param experience The amount of experience to add.
     */
    void addExperience(double experience);

    /**
     * Removes experience from the Caster's current experience.
     *
     * @param experience The amount of experience to remove.
     */
    void removeExperience(double experience);

    /**
     * @return The Caster's Bukkit Player object.
     */
    Player bukkit();

    /**
     * Resets the Caster's current experience back to zero.
     */
    void setExperienceToZero();
}
