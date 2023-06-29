package app.simplexdev.arcanumocculta.api.book;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface SpellBook {
    String getBookName();

    void setBookName(final String name);

    String getBookDescription();

    void setBookDescription(final String description);

    ItemStack getBookItem();

    void setBookItem(final ItemStack itemStack);

    List<Spell> getSpells();

    void addSpell(Spell spell);

    void removeSpell(Spell spell);

    Spell getSpell(final String name);

    Spell getSpell(final int index);

    int getSpellCount();
}
