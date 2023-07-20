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

package app.simplexdev.arcanumocculta.command;

import app.simplexdev.arcanumocculta.command.base.CommandResponse;
import app.simplexdev.arcanumocculta.command.base.Commander;
import app.simplexdev.arcanumocculta.command.base.Completion;
import app.simplexdev.arcanumocculta.command.base.Info;
import app.simplexdev.arcanumocculta.command.base.Permissions;
import org.bukkit.command.CommandSender;

@Info(name = "caster", description = "Caster commands", usage = "/caster <subcommand> [args]")
@Permissions("arcaneum.caster")
@Completion(index = 0, args = {})
@Completion(index = 1, args = {})
public class CasterCommand implements Commander
{
    @Override
    public CommandResponse run(CommandSender sender, String[] args)
    {
        return CommandResponse.of("Not implemented yet!");
    }
}
