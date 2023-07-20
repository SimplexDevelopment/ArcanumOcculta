package app.simplexdev.arcanumocculta.api.caster;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import java.util.List;

public interface SpellBook
{
    /**
     * @return A list of all spells in the spell book.
     */
    List<Spell> getSpells();

    /**
     * Adds a spell to the spell book.
     *
     * @param spell The spell to add.
     */
    void addSpell(Spell spell);

    /**
     * Removes a spell from the spell book.
     *
     * @param spell The spell to remove.
     */
    void removeSpell(Spell spell);

    /**
     * Retrieves a spell from the spell book.
     *
     * @param id The id of the spell to retrieve.
     * @return The spell with the given id.
     */
    Spell getSpell(String id);

    /**
     * Retrieves a spell from the spell book with the given index.
     *
     * @param index The index of the spell to retrieve.
     * @return The spell at the given index.
     */
    Spell getSpell(int index);

    /**
     * @return The number of spells in the spell book.
     */
    int getSpellCount();

    /**
     * Sets the spell at the given index to the given spell.
     *
     * @param index The index of the spell to set.
     * @param spell The spell to set.
     */
    void setSpell(int index, Spell spell);

    /**
     * Sets the spell with the given id to the given spell.
     *
     * @param id    The id of the spell to set.
     * @param spell The spell to set.
     */
    void setSpell(String id, Spell spell);

    /**
     * Clears all spells from the spell book.
     */
    void clearSpells();

    /**
     * @return A random spell from the spell book.
     */
    Spell randomSpell();

    /**
     * Checks if the spell book contains the given spell.
     *
     * @param spell The spell to check for.
     * @return True if the spell book contains the given spell, false otherwise.
     */
    boolean hasSpell(final Spell spell);
}
