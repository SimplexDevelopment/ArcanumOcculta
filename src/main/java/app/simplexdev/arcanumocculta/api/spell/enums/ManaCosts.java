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

package app.simplexdev.arcanumocculta.api.spell.enums;

public enum ManaCosts
{
    MINIMAL_CAST(5.0),
    LIGHT_CAST(10.0),
    MODERATE_CAST(20.0),
    HEAVY_CAST(30.0),
    MAJOR_CAST(50.0),
    EXTREME_CAST(100.0),
    IMMENSE_CAST(200.0),
    MASTER_CAST(500.0);

    private final double manaCost;

    ManaCosts(final double manaCost) {
        this.manaCost = manaCost;
    }

    public double getManaCost() {
        return manaCost;
    }

    public boolean isMoreThan(final double manaCost) {
        return this.manaCost > manaCost;
    }
}
