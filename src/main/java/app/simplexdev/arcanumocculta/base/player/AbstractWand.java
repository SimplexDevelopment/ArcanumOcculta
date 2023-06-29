package app.simplexdev.arcanumocculta.base.player;

import app.simplexdev.arcanumocculta.api.effect.PassiveEffect;
import app.simplexdev.arcanumocculta.api.player.Wand;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWand implements Wand {
    private final List<PassiveEffect> passiveEffects = new ArrayList<>();
    private String name;
    private String description;
    private ItemStack item;
    private double manaPenalty;

    protected AbstractWand(String name, String description, ItemStack item, double manaPenalty) {
        this.name = name;
        this.description = description;
        this.item = item;
        this.manaPenalty = manaPenalty;
    }

    @Override
    public String getWandName() {
        return name;
    }

    @Override
    public String getWandDescription() {
        return description;
    }

    @Override
    public ItemStack getWandItem() {
        return item;
    }

    @Override
    public double getManaPenalty() {
        return manaPenalty;
    }

    @Override
    public List<PassiveEffect> getPassiveEffects() {
        return passiveEffects;
    }
}
