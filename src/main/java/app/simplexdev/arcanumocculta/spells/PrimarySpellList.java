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

package app.simplexdev.arcanumocculta.spells;

import app.simplexdev.arcanumocculta.api.caster.AbstractSpellBook;
import app.simplexdev.arcanumocculta.api.spell.AbstractSpell;
import app.simplexdev.arcanumocculta.api.spell.Spell;
import app.simplexdev.arcanumocculta.util.SpellUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

public class PrimarySpellList extends AbstractSpellBook
{
    public PrimarySpellList()
    {
        Set<Class<? extends AbstractSpell>> spellClasses = m0().getSubTypesOf(AbstractSpell.class);
        final Set<Spell> v0 = new HashSet<>();
        spellClasses.forEach(spellClass ->
                             {
                                 try
                                 {
                                     final Spell spell = spellClass.getDeclaredConstructor().newInstance();
                                     v0.add(spell);
                                 }
                                 catch (ReflectiveOperationException e)
                                 {
                                     Bukkit.getLogger().severe(e.getMessage());
                                 }
                             });
        this.addAll(v0);
    }

    private Reflections m0()
    {
        final Reflections v0 = new Reflections(SpellUtils.SPELL_PACKAGE + ".flame",
                                               Scanners.SubTypes);
        final Reflections v1 = new Reflections(SpellUtils.SPELL_PACKAGE + ".soul",
                                               Scanners.SubTypes);
        final Reflections v2 = new Reflections(SpellUtils.SPELL_PACKAGE + ".wither", Scanners.SubTypes);

        return v0.merge(v1).merge(v2);
    }
}
