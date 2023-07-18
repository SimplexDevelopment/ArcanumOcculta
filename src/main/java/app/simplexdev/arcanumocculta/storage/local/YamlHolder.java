package app.simplexdev.arcanumocculta.storage.local;

import app.simplexdev.arcanumocculta.ArcanumOcculta;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlHolder
{
    private final YamlConfiguration configuration;
    private final ArcanumOcculta plugin;

    public YamlHolder(final ArcanumOcculta plugin, final String resourceLocation, final String fileName,
                      final File parentFolder,
                      final boolean copyDefaults)
    {
        final File file = new File(parentFolder, fileName);
        this.plugin = plugin;

        try
        {
            if (file.createNewFile() || copyDefaults)
            {
                final String creatingFile = String.format("Creating %s...", fileName);
                plugin.getLogger().info(creatingFile);
                plugin.saveResource(resourceLocation, true);
            }
        }
        catch (IOException ex)
        {
            plugin.getLogger().severe(ex.getMessage());
        }

        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public String getString(final String path)
    {
        return this.configuration.getString(path);
    }

    public boolean getBoolean(final String path)
    {
        return this.configuration.getBoolean(path);
    }

    public int getInt(final String path)
    {
        return this.configuration.getInt(path);
    }

    public double getDouble(final String path)
    {
        return this.configuration.getDouble(path);
    }

    public ConfigurationSection getConfigurationSection(final String path)
    {
        return this.configuration.getConfigurationSection(path);
    }

    public List<String> getStringList(final String path)
    {
        return this.configuration.getStringList(path);
    }

    public void set(final String path, final Object value)
    {
        this.configuration.set(path, value);
    }

    public void save()
    {
        try
        {
            this.configuration.save(this.configuration.getCurrentPath());
        }
        catch (IOException ex)
        {
            final String message = MessageFormat.format("Failed to save {0}!", this.configuration.getCurrentPath());
            plugin.getLogger().severe(message);
        }
    }

    public void load()
    {
        try
        {
            this.configuration.load(this.configuration.getCurrentPath());
        }
        catch (IOException | org.bukkit.configuration.InvalidConfigurationException ex)
        {
            final String message = MessageFormat.format("Failed to load {0}!", this.configuration.getCurrentPath());
            plugin.getLogger().severe(message);
        }
    }

    public void reload()
    {
        this.save();
        this.load();
    }
}
