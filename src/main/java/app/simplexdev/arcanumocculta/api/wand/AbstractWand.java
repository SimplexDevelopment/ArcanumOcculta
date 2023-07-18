package app.simplexdev.arcanumocculta.api.wand;

import org.bukkit.inventory.ItemStack;

public abstract class AbstractWand implements Wand
{
    private final ItemStack item;
    private final CapType capType;
    private final CoreType coreType;
    private final GemType gemType;
    private final String name;
    private final String description;

    protected AbstractWand(String name,
                           String description, ItemStack item, CapType capType,
                           CoreType coreType, GemType gemType)
    {
        this.name = name;
        this.description = description;
        this.item = item;
        this.capType = capType;
        this.coreType = coreType;
        this.gemType = gemType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public ItemStack getItem()
    {
        return item;
    }

    @Override
    public double getSpellBonus()
    {
        return getCapType().getSpellBoost()
            + getCoreType().getSpellBoost()
            + getGemType().getSpellBoost();
    }

    @Override
    public CapType getCapType()
    {
        return capType;
    }

    @Override
    public CoreType getCoreType()
    {
        return coreType;
    }

    @Override
    public GemType getGemType()
    {
        return gemType;
    }
}
