/*
 * Copyright (c) 2023 Simplex Development Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * with the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS," WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package app.simplexdev.arcanumocculta.command.base;

import app.simplexdev.arcanumocculta.ArcanumOcculta;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

final class CommandDelegate extends Command implements PluginIdentifiableCommand
{
    final Commander command;
    final ArcanumOcculta plugin;

    public CommandDelegate(final ArcanumOcculta plugin, final Commander command)
    {
        super(command.getName());
        this.plugin = plugin;
        this.command = command;
        this.setLabel(command.getName());
        this.setDescription(command.getDescription());
        this.setUsage(command.getUsage());
        this.setPermission(command.getPermission());
        this.setPermissionMessage(command.getPermissionMessage());
        this.setAliases(command.getAliasList());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args)
    {
        if (!commandLabel.equalsIgnoreCase(command.getName())
            || !command.getAliasList().contains(commandLabel))
            return false;

        if (!command.allowConsole() && sender instanceof ConsoleCommandSender)
        {
            sender.sendMessage("This command cannot be executed from the console.");
            return true;
        }

        if (!sender.hasPermission(command.getPermission()))
        {
            sender.sendMessage(command.getPermissionMessage());
            return true;
        }

        final CommandResponse response = command.run(sender, args);

        if (response != null && !response.isEmpty())
        {
            sender.sendMessage(response.getResponse());
            Bukkit.getLogger().info(response.getResponse());
            return true;
        }

        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args)
        throws IllegalArgumentException
    {
        return command.getTabCompletions(args);
    }

    @NotNull
    @Override
    public Plugin getPlugin()
    {
        return this.plugin;
    }
}
