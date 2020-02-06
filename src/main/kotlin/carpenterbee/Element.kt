// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.functionality.HasParent
import carpenterbee.extensions.getOrNull
import carpenterbee.functionality.TagFinder
import carpenterbee.sequencers.Sequencer
import carpenterbee.specifiers.Specifiers
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

public sealed class Element(val session: Session) {
    init {
        session.track(this)
    }

    public abstract val scope: SearchContext?

    public companion object {
        val by get() = Specifiers
    }
}

public sealed class Block(session: Session) : Element(session)

public abstract class Page(session: Session) : Block(session) {
    public override val scope: SearchContext?
        get() = session.driver.switchTo().defaultContent()

    public abstract class Navigator<TThis : Page>(val constructor: (Session) -> TThis) : (Block) -> TThis {
        public override fun invoke(p1: Block): TThis = constructor(p1.session)
        public fun <TRouteTo : Section<*>> section(path: TThis.() -> TRouteTo) =
            { source: Block -> constructor(source.session).path() }
    }
}

public abstract class Section<TParent : Block>(
    public override val parent: TParent,
    public override val specifier: By
) : Block(parent.session), HasParent<TParent> {
    public override val scope: SearchContext? get() = getOrNull()

    public val tag get() = TagFinder.find(this)
}

public abstract class Control<TParent : Block, TDefaultRoute : Block>(
    public override val parent: TParent,
    public override val specifier: By,
    protected val route: (TParent) -> TDefaultRoute
) : Element(parent.session), HasParent<TParent> {
    public override val scope: SearchContext? get() = getOrNull()

    public val sequencers = mutableListOf<Sequencer>()

    internal val tag get() = TagFinder.find(this)

    public fun <TRouteTo : Block> interact(
        route: (TParent) -> TRouteTo,
        interaction: WebElement.() -> Unit
    ): TRouteTo {
        val tag = tag
        sequencers.forEach { it.preInteract(tag) }
        tag.interaction()
        sequencers.forEach { it.postInteract(tag) }
        return route(parent)
    }

    public fun <T> read(retriever: WebElement.() -> T): T = retriever(tag)
}