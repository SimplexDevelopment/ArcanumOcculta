package app.simplexdev.arcanumocculta.api.book;

import app.simplexdev.arcanumocculta.api.spell.Spell;
import org.bukkit.inventory.ItemStack;

public interface SpellTome {
    String getTomeName();

    String getTomeDescription();

    ItemStack getTomeItem();

    Spell getContainedSpell();
}
