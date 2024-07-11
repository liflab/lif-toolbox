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
 * A {@link Runnable} object whose execution can be cancelled.
 * @author Sylvain Hallé
 */
public interface CancellableRunnable extends Runnable, Cancellable
{
	/**
	 * Gets the {@link Flag} object that controls the cancellation of this
	 * runnable.
	 * @return The flag; can be {@code null} if no flag controls this runnable
	 */
	/*@ null @*/ public Flag getFlag();
}
