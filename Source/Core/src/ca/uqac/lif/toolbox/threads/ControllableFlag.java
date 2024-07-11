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
 * A {@link Flag} that cancels a task when told by a call to its method
 * {@link #cancel()}.
 * 
 * @author Sylvain Hallé
 */
public class ControllableFlag implements Flag
{
	/**
	 * The internal flag determining if the task should be cancelled.
	 */
	protected boolean m_cancelled;
	
	/**
	 * Creates a new controllable flag.
	 */
	public ControllableFlag()
	{
		super();
		m_cancelled = false;
	}
	
	/**
	 * Sets the flag so that it should declare the task as cancelled.
	 */
	public void cancel()
	{
		m_cancelled = true;
	}
	
	@Override
	public boolean isCancelled()
	{
		return m_cancelled;
	}
}
