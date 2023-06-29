package app.simplexdev.arcanumocculta.base.book;

import app.simplexdev.arcanumocculta.api.book.SpellTome;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class AbstractSpellTome implements SpellTome {
    private final Spell containedSpell;
    private final String name;
    private final String description;
    private final ItemStack item;

    protected AbstractSpellTome(final String name, final String description, final Spell containedSpell) {
        this.containedSpell = containedSpell;
        this.name = name;
        this.description = description;
        final ItemStack stack = new ItemStack(Material.BOOK, 1);
        final ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(List.of(description));
            stack.setItemMeta(meta);
        }
        this.item = stack;
    }

    @Override
    public String getTomeName() {
        return this.name;
    }

    @Override
    public String getTomeDescription() {
        return this.description;
    }

    @Override
    public ItemStack getTomeItem() {
        return this.item;
    }

    @Override
    public Spell getContainedSpell() {
        return this.containedSpell;
    }
}
