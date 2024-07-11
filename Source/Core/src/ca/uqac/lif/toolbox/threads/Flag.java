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
 * An object passed to a {@link Cancellable} object that determines whether
 * this object should cancel the task it is currently doing.
 * 
 * @author Sylvain Hallé
 */
public interface Flag
{
	/**
	 * Determines if the task this flag is associated with should be cancelled
	 * immediately.
	 * @return {@code true} if the task should be cancelled, {@code false}
	 * otherwise
	 */
	public boolean isCancelled();
}