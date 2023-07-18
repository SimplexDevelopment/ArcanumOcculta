package app.simplexdev.arcanumocculta.api.wand;

import org.bukkit.Material;

public enum CoreType
{
    WOOD(Material.STICK, 1.0),
    IRON(Material.IRON_INGOT, 1.25),
    GOLD(Material.GOLD_INGOT, 1.5),
    OBSIDIAN(Material.OBSIDIAN, 1.75),
    BLAZE(Material.BLAZE_ROD, 2.0),
    ENDER(Material.END_ROD, 2.5);

    private final Material material;
    private final double spellBoost;

    CoreType(final Material material, final double spellBoost)
    {
        this.spellBoost = spellBoost;
        this.material = material;
    }

    public Material getMaterial()
    {
        return this.material;
    }

    public double getSpellBoost()
    {
        return spellBoost;
    }
}
