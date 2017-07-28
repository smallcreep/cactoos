/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.func.UncheckedScalar;

/**
 * Array as iterable.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @author Ix (ixmanuel@yahoo.com)
 * @version $Id$
 * @param <X> Type of item
 * @since 0.12
 */
public final class ArrayOf<X> implements Iterable<X> {

    /**
     * The encapsulated iterator of X.
     */
    private final UncheckedScalar<Iterator<X>> scalar;

    /**
     * Ctor.
     * @param text The text
     */
    public ArrayOf(final Text text) {
        this(() -> new CharactersOf<X>(text).iterator());
    }

    /**
     * Ctor.
     * @param map The map to be flatten as an array of values
     */
    @SuppressWarnings("unchecked")
    public ArrayOf(final Map<?, ?> map) {
        this(
            () -> (Iterator<X>) Arrays.asList(
                map.values().toArray()
            ).iterator()
        );
    }

    /**
     * Ctor.
     * @param items The array
     */
    @SafeVarargs
    public ArrayOf(final X... items) {
        this(() -> Arrays.asList(items).iterator());
    }

    /**
     * Ctor.
     * @param sclr The encapsulated iterator of x
     */
    private ArrayOf(final Scalar<Iterator<X>> sclr) {
        this.scalar = new UncheckedScalar<>(sclr);
    }

    @Override
    public Iterator<X> iterator() {
        return this.scalar.value();
    }

}