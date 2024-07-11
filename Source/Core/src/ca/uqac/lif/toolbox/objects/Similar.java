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
 * Interface implemented by objects that can be compared to others for
 * "similarity". The precise implementation of method
 * {@link #similarTo(Object) similarTo()} is left to each class, but it is
 * assumed that it forms an equivalence relation with the additional
 * condition that if {@code o1.equals(o2)}, then
 * {@code o1.similarTo(o2) == true}.
 * <p>
 * In addition, similarity is retrofitted on a few native Java classes through
 * the use of {@link #similarTo(Object, Object)}.
 * <p>
 * Similarity is provided for objects that require a looser form of comparison
 * without overriding the behavior of {@link Object#equals(Object)}.
 * 
 * @author Sylvain Hallé
 */
public interface Similar
{
	/**
	 * Determines if an object is similar to another one.
	 * @param o The other object
	 * @return {@code true} if this object is similar to the current object,
	 * {@code false} otherwise
	 */
	public boolean similarTo(Object o);
	
	/**
	 * Determines if two arbitrary objects {@code o1} and {@code o2} are similar.
	 * The rules to determine similarity are as follows:
	 * <ul>
	 * <li>{@code null} is similar to {@code null}, but not to any other object
	 * or value</li>
	 * <li>if {@code o1} and {@code o2} implement {@link Similar}, they are
	 * similar if and only if {@code o1.isSimilarTo(o2) == true}</li>
	 * <li>two strings are similar if {@code o1.compareTo(o2) == 0}</li>
	 * <li>two numbers are similar if they have the same value when converted
	 * into {@link Double}s</li>
	 * <li>in any other case, {@code o1} and {@code o2} are similar if and only
	 * if {@code o1.equals(o2)}</li>
	 * </ul>
	 * @param o1 The first object
	 * @param o2 The second object
	 * @return {@code true} if both objects are deemed similar, {@code false}
	 * otherwise
	 */
	public default boolean similarTo(Object o1, Object o2)
	{
		if ((o1 == null) != (o2 == null))
		{
			return false;
		}
		if (o1 instanceof Similar && o2 instanceof Similar)
		{
			return ((Similar) o1).similarTo(o2);
		}
		if (o1 instanceof String && o2 instanceof String)
		{
			return ((String) o1).compareTo((String) o2) == 0;
		}
		if (o1 instanceof Number && o2 instanceof Number)
		{
			return ((Number) o1).doubleValue() == ((Number) o2).doubleValue();
		}
		return o1.equals(o2);
	}
}
