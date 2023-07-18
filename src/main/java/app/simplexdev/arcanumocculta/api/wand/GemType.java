package app.simplexdev.arcanumocculta.api.wand;

import org.bukkit.Material;

public enum GemType
{
    NONE(Material.AIR, 1.0),
    LAPIS(Material.LAPIS_LAZULI, 1.25),
    REDSTONE(Material.REDSTONE, 1.5),
    EMERALD(Material.EMERALD, 2.0),
    DIAMOND(Material.DIAMOND, 3.0),
    QUARTZ(Material.QUARTZ, 4.0);

    private final Material material;
    private final double spellBoost;

    GemType(final Material material, final double spellBoost)
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
