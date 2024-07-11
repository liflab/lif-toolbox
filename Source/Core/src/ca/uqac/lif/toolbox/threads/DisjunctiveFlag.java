/*
    Simple management of threads and tasks
    Copyright (C) 2024 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.toolbox.threads;

/**
 * A {@link Flag} that is passed one or more other flags, and which declares
 * cancellation as soon as one of them does.
 * 
 * @author Sylvain Hallé
 */
public class DisjunctiveFlag implements Flag
{
	/**
	 * The flags that this disjunctive flag queries.
	 */
	protected final Flag[] m_flags;
	
	/**
	 * Creates a new instance of disjunctive flag.
	 * @param flags The flags that this disjunctive flag queries
	 */
	public DisjunctiveFlag(Flag ... flags)
	{
		super();
		m_flags = flags;
	}
	
	/**
	 * Gets the flags that are contained inside this disjunctive flag.
	 * @return The array of flags
	 */
	public Flag[] getFlags()
	{
		return m_flags;
	}
	
	@Override
	public boolean isCancelled()
	{
		for (Flag f : m_flags)
		{
			if (f.isCancelled())
			{
				return true;
			}
		}
		return false;
	}
}
