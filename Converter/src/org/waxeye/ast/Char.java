/*
 * Waxeye Parser Generator
 * www.waxeye.org
 * Copyright (C) 2008-2010 Orlando Hill
 * Licensed under the MIT license. See 'LICENSE' for details.
 */
package org.waxeye.ast;

/**
 * An AST node with a character.
 *
 * @param <E> The node types for the AST.
 *
 * @author Orlando Hill
 */
public final class Char <E extends Enum<?>> extends NoChildren<E>
implements IChar
{
    /** The value of the char. */
    private final char value;

    /** The position of the char. */
    private final int position;

    /**
     * Creates a new Char AST.
     *
     * @param value The character value.
     *
     * @param position The character index in the input.
     *
     * @param type The type of the AST.
     */
    public Char(final char value, final E type, final int position)
    {
        super(type);
        this.value = value;
        this.position = position;
    }

    /** {@inheritDoc} */
    public char getValue()
    {
        return value;
    }

    /** {@inheritDoc} */
    public int getPos()
    {
      return position;
    }

    /** {@inheritDoc} */
    public void acceptASTVisitor(final IASTVisitor visitor)
    {
        visitor.visitChar(this);
    }

    /** {@inheritDoc} */
    public String toString()
    {
        return Character.toString(value);
    }
}
