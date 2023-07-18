package app.simplexdev.arcanumocculta.storage.local;

import app.simplexdev.arcanumocculta.ArcanumOcculta;
import app.simplexdev.arcanumocculta.api.caster.Caster;
import app.simplexdev.arcanumocculta.api.caster.CasterLevel;
import app.simplexdev.arcanumocculta.api.caster.SpellBook;
import app.simplexdev.arcanumocculta.api.wand.CapType;
import app.simplexdev.arcanumocculta.api.wand.CoreType;
import app.simplexdev.arcanumocculta.api.wand.GemType;
import app.simplexdev.arcanumocculta.api.wand.Wand;
import app.simplexdev.arcanumocculta.base.SimpleCaster;
import app.simplexdev.arcanumocculta.base.SimpleSpellBook;
import app.simplexdev.arcanumocculta.base.SimpleWand;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerYaml
{
    private final YamlHolder yamlHolder;

    public PlayerYaml(final ArcanumOcculta plugin, final String name)
    {
        this.yamlHolder = new YamlHolder(plugin, "player-default.yml", name, plugin.getDataFolder(), false);
    }

    public YamlHolder getYamlHolder()
    {
        return yamlHolder;
    }

    public Wand retrieveWand()
    {
        ConfigurationSection wandSection = yamlHolder.getConfigurationSection("wand");
        String name = wandSection.getString("name");
        String description = wandSection.getString("description");
        Material material = Material.getMaterial(wandSection.getString("item"));
        CoreType core = CoreType.valueOf(wandSection.getString("core"));
        CapType cap = CapType.valueOf(wandSection.getString("cap"));
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null)
        {
            meta.setDisplayName(name);
            meta.setLore(List.of(description));
            item.setItemMeta(meta);
        }
        GemType gem = GemType.valueOf(wandSection.getString("gem"));
        return new SimpleWand(name, description, item, cap, core, gem);
    }

    public void saveWand(final Wand wand)
    {
        ConfigurationSection wandSection = yamlHolder.getConfigurationSection("wand");
        wandSection.set("name", wand.getName());
        wandSection.set("description", wand.getDescription());
        wandSection.set("item", wand.getItem().getType().name());
        wandSection.set("core", wand.getCoreType().name());
        wandSection.set("cap", wand.getCapType().name());
        wandSection.set("gem", wand.getGemType().name());
        yamlHolder.save();
    }

    public Caster fetchCaster(final Player player)
    {
        ConfigurationSection caster = yamlHolder.getConfigurationSection("caster");
        CasterLevel level = CasterLevel.fromOrdinal(caster.getInt("caster_rank"));
        double xp = caster.getDouble("current_experience");
        double currentMana = caster.getDouble("current_mana");
        double maxMana = caster.getDouble("max_mana");

        final Wand wand = retrieveWand();
        final SpellBook spellBook = retrieveSpellBook();
        Caster c = new SimpleCaster(player, wand, spellBook, level);
        c.setCurrentExperience(xp);
        c.setCurrentMana(currentMana);
        c.setMaxMana(maxMana);
        return c;
    }

    public SpellBook retrieveSpellBook()
    {
        final SpellBook book = new SimpleSpellBook();
        List<String> spellBook = yamlHolder.getStringList("spellbook");
        for (String spell : spellBook)
        {
            book.addSpell(SpellUtils.copyFromPrimaryList(spell));
        }
        return book;
    }
}
