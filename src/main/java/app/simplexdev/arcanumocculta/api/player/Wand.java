package app.simplexdev.arcanumocculta.api.player;

import app.simplexdev.arcanumocculta.api.effect.PassiveEffect;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Wand {
    String getWandName();

    String getWandDescription();

    ItemStack getWandItem();

    double getManaPenalty();

    List<PassiveEffect> getPassiveEffects();
}
