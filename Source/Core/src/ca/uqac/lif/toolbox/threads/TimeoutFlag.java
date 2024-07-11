/*
    A toolbox of common classes and interfaces
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

import ca.uqac.lif.toolbox.objects.Startable;
import ca.uqac.lif.toolbox.time.Clock;
import ca.uqac.lif.toolbox.time.WallClock;

/**
 * A flag that declares cancellation after some amount of time has passed.
 * Optionally, the flag can be passed a {@link Clock} object which it then uses
 * as its source to get the current time.
 * 
 * @author Sylvain Hallé
 */
public class TimeoutFlag implements Flag, Startable
{
	/**
	 * The delay after which cancellation should be declared, in milliseconds.
	 */
	protected final long m_delay;
	
	/**
	 * The time at which the countdown has been started.
	 */
	protected long m_startTime;
	
	/**
	 * The clock object used to fetch the current time.
	 */
	protected final Clock m_clock;
	
	/**
	 * Creates a new timeout flag and starts the countdown.
	 * @param delay The delay after which cancellation should be declared, in
	 * milliseconds
	 */
	public TimeoutFlag(long delay)
	{
		this(delay, true);
	}
	
	/**
	 * Creates a new timeout flag.
	 * @param delay The delay after which cancellation should be declared, in
	 * milliseconds
	 * @param start Set to {@code true} to start the countdown immediately,
	 * {@code false} otherwise
	 */
	public TimeoutFlag(long delay, boolean start)
	{
		this(delay, start, new WallClock(true));
	}
	
	/**
	 * Creates a new timeout flag.
	 * @param delay The delay after which cancellation should be declared, in
	 * milliseconds
	 * @param start Set to {@code true} to start the countdown immediately,
	 * @param clock The clock object used to fetch the current time 
	 * {@code false} otherwise
	 */
	public TimeoutFlag(long delay, boolean start, Clock clock)
	{
		super();
		m_delay = delay;
		m_clock = clock;
		if (start)
		{
			start();
		}
		else
		{
			m_startTime = -1;
		}
	}
	
	/**
	 * Starts the countdown.
	 * @return This flag
	 */
	@Override
	public void start()
	{
		m_startTime = System.currentTimeMillis();
	}
	
	/**
	 * Gets the delay after which cancellation is declared.
	 * @return The delay in milliseconds
	 */
	/*@ pure @*/ public long getDelay()
	{
		return m_delay;
	}
	
	@Override
	public boolean isCancelled() throws CancelledException
	{
		if (m_startTime < 0 || m_delay < 0)
		{
			return false;
		}
		return System.currentTimeMillis() - m_startTime >= m_delay;
	}
}
