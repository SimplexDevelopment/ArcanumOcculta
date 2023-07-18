package app.simplexdev.arcanumocculta.base;

import app.simplexdev.arcanumocculta.api.wand.AbstractWand;
import app.simplexdev.arcanumocculta.api.wand.CapType;
import app.simplexdev.arcanumocculta.api.wand.CoreType;
import app.simplexdev.arcanumocculta.api.wand.GemType;
import org.bukkit.inventory.ItemStack;

public class SimpleWand extends AbstractWand
{
    public SimpleWand(String name, String description, ItemStack item,
                      CapType capType,
                      CoreType coreType,
                      GemType gemType)
    {
        super(name, description, item, capType, coreType, gemType);
    }
}
