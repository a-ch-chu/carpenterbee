// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality.waiting

import org.openqa.selenium.support.ui.FluentWait
import kotlin.time.Duration
import kotlin.time.toJavaDuration

public open class Waiter(
    public var timeout: Duration = Default.timeout,
    public var pollCount: Int = Default.pollCount,
    public vararg var ignoring: Class<out Throwable>
) {
    private fun <TSubject : Any> wait(subject: TSubject) =
        FluentWait(subject)
            .withTimeout(timeout.toJavaDuration())
            .pollingEvery((timeout / pollCount).toJavaDuration())
            .ignoreAll(ignoring.toMutableSet())

    public fun <TSubject : Any, TRetrieved : Any> toGet(
        subject: TSubject,
        retrieve: TSubject.() -> TRetrieved?
    ): TRetrieved = wait(subject).until(retrieve)!!

    public fun <TSubject : Any> until(
        subject: TSubject,
        condition: TSubject.() -> Boolean?
    ): TSubject {
        wait(subject).until(condition)
        return subject
    }
}