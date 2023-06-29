package app.simplexdev.arcanumocculta.base.book;

import app.simplexdev.arcanumocculta.api.book.SpellBook;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpellBook implements SpellBook {
    private final List<Spell> spellbook = new ArrayList<>();

    private String name;
    private String description;
    private ItemStack itemStack;

    protected AbstractSpellBook(final String name) {
        this.name = name;
        this.description = ChatColor.BLUE + "A simple spell book.";

        final ItemStack stack = new ItemStack(Material.WRITTEN_BOOK, 1);
        ItemMeta meta = stack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(List.of(description));
            stack.setItemMeta(meta);
        }

        this.itemStack = stack;
    }

    @Override
    public String getBookName() {
        return name;
    }

    @Override
    public void setBookName(String name) {
        this.name = name;
    }

    @Override
    public String getBookDescription() {
        return description;
    }

    @Override
    public void setBookDescription(String description) {
        this.description = description;
    }

    @Override
    public ItemStack getBookItem() {
        return itemStack;
    }

    @Override
    public void setBookItem(final ItemStack stack) {
        this.itemStack = stack;
    }

    @Override
    public List<Spell> getSpells() {
        return this.spellbook;
    }

    @Override
    public void addSpell(Spell spell) {
        this.spellbook.add(spell);
    }

    @Override
    public void removeSpell(Spell spell) {
        this.spellbook.remove(spell);
    }

    @Override
    public Spell getSpell(String name) {
        return getSpells()
                .stream()
                .filter(spell -> spell.getSpellName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Spell getSpell(int index) {
        return getSpells().get(index);
    }

    @Override
    public int getSpellCount() {
        return getSpells().size();
    }
}
