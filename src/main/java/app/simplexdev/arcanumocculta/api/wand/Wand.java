package app.simplexdev.arcanumocculta.api.wand;

import org.bukkit.inventory.ItemStack;

public interface Wand
{
    String getName();

    String getDescription();

    ItemStack getItem();

    double getSpellBonus();

    CapType getCapType();

    CoreType getCoreType();

    GemType getGemType();
}
