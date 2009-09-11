/***************************************************************************
 *   Copyright (C) 2008 by Elvis Ciotti                                    *
 *   Copyright (C) 2009 by Fabrizio Montesi                                *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU Library General Public License as       *
 *   published by the Free Software Foundation; either version 2 of the    *
 *   License, or (at your option) any later version.                       *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU Library General Public     *
 *   License along with this program; if not, write to the                 *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 *                                                                         *
 *   For details about the authors of this software, see the AUTHORS file. *
 ***************************************************************************/

package jolie.util;

/**
 * A <code>Range</code> instance represents a range between two natural numbers.
 * @author Fabrizio Montesi
 */
public class Range
{
	private final int min;
	private final int max;

	/**
	 * Constructor
	 * @param min
	 * @param max
	 * @throws IllegalArgumentException if min or max are less than zero
	 */
	public Range( int min, int max )
	{
		if ( min < 0 || max < 0 ) {
			throw new IllegalArgumentException();
		}

		this.min = min;
		this.max = max;
	}

	public final int min()
	{
		return min;
	}

	public final int max()
	{
		return max;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 47 * hash + this.min;
		hash = 47 * hash + this.max;
		return hash;
	}

	@Override
	public boolean equals( Object other )
	{
		if ( this == other ) {
			return true;
		}

		if ( other instanceof Range ) {
			Range otherRange = (Range)other;
			return min == otherRange.min && max == otherRange.max;
		}

		return false;
	}
}
