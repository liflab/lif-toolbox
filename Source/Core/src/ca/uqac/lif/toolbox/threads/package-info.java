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

/**
 * A set of classes implementing a mechanism for "cooperative cancellation" of
 * long running tasks.
 * <p>
 * Therads in Java <a href="https://stackoverflow.com/questions/5241822/is-there-a-good-way-to-forcefully-stop-a-java-thread#5242382">cannot
 * reliably be forced to stop</a>. The most appropriate way of doing it is to
 * write methods that periodically check a flag and terminate out of their own
 * volition when the flag tells them so. The classes in this package provide
 * facilities for easily and cleanly implementing this behavior. In a nutshell:
 * <ol>
 * <li>A {@code Flag} object is created. This object implements the method
 * {@link Flag#isCancelled() isCancelled()}, which can be called any number of
 * times to determine when a task needs to be stopped.</li>
 * <li>An object that implements the {@code Cancellable} interface is passed
 * a flag through method {@link Cancellable#setFlag(Flag) setFlag()}.</li>
 * <li>One or more methods inside the object may check the status of the flag
 * at any moment, and choose to terminate in any way they deem appropriate when
 * the flag declares cancellation. One such way is by simply throwing a
 * {@link CancelledException}. This is a runtime exception, so callers do not
 * need to enclose their calls in a <tt>try</tt>/<tt>catch</tt> block if they
 * do not wish to.</li>
 * </ol>
 * <p>
 * {@link Flag} is just an interface; depending on the actual flag instance
 * passed to a {@link Cancellable} object, several strategies can be
 * implemented. The package provides two of them:
 * <ul>
 * <li>{@link ControllableFlag} declares cancellation once a
 * call to its method {@link ControllableFlag#cancel() cancel} is made. It
 * provides a way to manually stop a task from an external point in code.</li>
 * <li>{@link TimeoutFlag} declares cancellation once a
 * user-defined delay has been reached.</li>
 * </ul>
 * @author Sylvain Hallé
 */
package ca.uqac.lif.toolbox.threads;