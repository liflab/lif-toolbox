/*
    A toolbox of common classes and interfaces
    Copyright (C) 2022-2024 Sylvain Hallé

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
package ca.uqac.lif.toolbox.objects;

/**
 * Interface implemented by objects that can produce a copy of themselves.
 * @param <T> The type of the objects returned
 * 
 * @author Sylvain Hallé
 */
public interface Duplicable<T>
{
	/**
	 * Creates a copy of the current object.
	 * @return The copy
	 */
	public default T duplicate()
	{
		return duplicate(false);
	}
	
	/**
	 * Creates a copy of the current object.
	 * @param with_state If set to {@code true}, the copy has the same internal
	 * state as the original object; if set to {@code false}, the copy is in the
	 * initial state
	 * @return The copy
	 */
	public T duplicate(boolean with_state);
}
