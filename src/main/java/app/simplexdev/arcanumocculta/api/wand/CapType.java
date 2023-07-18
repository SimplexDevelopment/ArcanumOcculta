package app.simplexdev.arcanumocculta.api.wand;

import org.bukkit.Material;

public enum CapType
{
    IRON(Material.IRON_NUGGET, 1.0),
    GOLD(Material.GOLD_NUGGET, 2.5),
    DIAMOND(Material.DIAMOND, 3.0),
    OBSIDIAN(Material.OBSIDIAN, 4.5),
    NETHERITE(Material.NETHERITE_INGOT, 6.0);

    private final Material material;
    private final double spellBoost;

    CapType(final Material material, final double spellBoost) {
        this.material = material;
        this.spellBoost = spellBoost;
    }

    public final Material getMaterial() {
        return this.material;
    }

    public final double getSpellBoost() {
        return this.spellBoost;
    }
}
