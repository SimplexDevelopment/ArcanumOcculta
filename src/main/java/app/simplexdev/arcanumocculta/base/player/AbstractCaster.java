package app.simplexdev.arcanumocculta.base.player;

import app.simplexdev.arcanumocculta.api.book.SpellBook;
import app.simplexdev.arcanumocculta.api.player.Caster;
import app.simplexdev.arcanumocculta.api.player.SpellResistance;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCaster implements Caster {
    private final String name;
    private final UUID uuid;
    private final SpellBook spellBook;
    private final List<SpellResistance> spellResistances = new ArrayList<>();

    private double maximumMana;
    private double currentMana;

    protected AbstractCaster(final Player player, SpellBook spellBook) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.spellBook = spellBook;

        this.maximumMana = 20;
        this.currentMana = maximumMana;
    }

    @Override
    public UUID getCasterUUID() {
        return uuid;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public double getMaximumMana() {
        return maximumMana;
    }

    @Override
    public void setMaximumMana(double mana) {
        this.maximumMana = mana;
    }

    @Override
    public double getCurrentMana() {
        return currentMana;
    }

    @Override
    public double addMana(double mana) {
        this.currentMana += mana;
        return getCurrentMana();
    }

    @Override
    public double increaseMaximumMana(double mana) {
        this.maximumMana += mana;
        return getMaximumMana();
    }

    @Override
    public double removeMana(double mana) {
        final double newMana = currentMana - mana;
        if (newMana < 0) {
            currentMana = 0;
        } else {
            currentMana = newMana;
        }
        return getCurrentMana();
    }

    @Override
    public double decreaseMaximumMana(double mana) {
        final double newMana = maximumMana - mana;
        if (newMana < 0) {
            maximumMana = 0;
        } else {
            maximumMana = newMana;
        }
        return getMaximumMana();
    }

    @Override
    public void setMana(double mana) {
        if (mana > getMaximumMana()) {
            currentMana = getMaximumMana();
        } else if (mana < 0) {
            currentMana = 0;
        } else {
            currentMana = mana;
        }
    }

    @Override
    public SpellBook getSpellbook() {
        return spellBook;
    }

    @Override
    public List<SpellResistance> getSpellResistances() {
        return spellResistances;
    }

    @Override
    public void addSpellResistance(SpellResistance resistance) {
        this.spellResistances.add(resistance);
    }

    @Override
    public void removeSpellResistance(SpellResistance resistance) {
        this.spellResistances.remove(resistance);
    }

    @Override
    public boolean hasResistance(int ordinal) {
        return getSpellResistances().stream().anyMatch(resistance -> resistance.getOrdinal() == ordinal);
    }
}
