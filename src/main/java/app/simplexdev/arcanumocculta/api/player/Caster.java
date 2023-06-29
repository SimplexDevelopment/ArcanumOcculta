package app.simplexdev.arcanumocculta.api.player;

import app.simplexdev.arcanumocculta.api.book.SpellBook;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface Caster {
    /**
     * @return The {@link UUID} of the caster.
     */
    UUID getCasterUUID();

    /**
     * @return The name of the caster.
     */
    String getDisplayName();

    /**
     * @return The {@link Player} associated with this caster.
     */
    Player bukkit();

    /**
     * @return The amount of maximum mana the caster is allowed to have.
     */
    double getMaximumMana();

    /**
     * Sets the maximum amount of mana the caster is allowed to have.
     *
     * @param mana The amount of mana to set the maximum to.
     */
    void setMaximumMana(final double mana);

    /**
     * @return The amount of mana the caster currently has.
     */
    double getCurrentMana();

    /**
     * Adds mana to the caster.
     *
     * @param mana The amount of mana to add.
     * @return The players updated {@link #getCurrentMana()}.
     */
    double addMana(final double mana);

    /**
     * Increases the amount of maximum mana this caster is allowed to have.
     *
     * @param mana The amount of mana to increase the maximum by.
     * @return The players updated {@link #getMaximumMana()}.
     */
    double increaseMaximumMana(final double mana);

    /**
     * Removes mana from the caster.
     *
     * @param mana The amount of mana to remove.
     * @return The players updated {@link #getCurrentMana()}.
     */
    double removeMana(final double mana);

    /**
     * Decreases the amount of maximum mana this caster is allowed to have.
     *
     * @param mana The amount of mana to decrease the maximum by.
     * @return The players updated {@link #getMaximumMana()}.
     */
    double decreaseMaximumMana(final double mana);

    /**
     * Sets the amount of mana the caster currently has.
     *
     * @param mana The amount of mana to set the user's current mana to.
     */
    void setMana(final double mana);

    /**
     * @return The {@link SpellBook} of the caster.
     */
    SpellBook getSpellbook();

    /**
     * @return A list of {@link SpellResistance}s the caster has.
     */
    List<SpellResistance> getSpellResistances();

    void addSpellResistance(SpellResistance resistance);

    void removeSpellResistance(SpellResistance resistance);

    boolean hasResistance(int spellOrdinal);
}
