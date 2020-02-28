// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.extensions.getOrNull
import carpenterbee.functionality.HasParent
import carpenterbee.functionality.TagFinder
import carpenterbee.functionality.specifiers.Specifiers
import carpenterbee.sequencers.SequencerList

public sealed class Element(val session: Session) {
    init {
        session.track(this)
    }

    public abstract val scope: SearchContext?

    public companion object {
        public val by: Specifiers get() = Specifiers
    }
}

public sealed class Block(session: Session) : Element(session)

public abstract class Page(session: Session) : Block(session) {
    public override val scope: SearchContext?
        get() = session.driver.switchTo().defaultContent()

    public abstract class Navigator<TThis : Page>(
        val constructor: (Session) -> TThis
    ) : (Block) -> TThis {
        public override fun invoke(p1: Block): TThis = constructor(p1.session)

        public fun <TParent : Block, TRouteTo : Section<TParent>> section(
            path: TThis.() -> TRouteTo
        ): (Block) -> TRouteTo =
            { source: Block -> constructor(source.session).path() }
    }
}

public abstract class Section<TParent : Block>(
    public override val parent: TParent,
    public override val specifier: By
) : Block(parent.session), HasParent<TParent> {
    public override val scope: SearchContext? get() = getOrNull()

    public val tag: WebElement get() = TagFinder.find(this)
}

public abstract class Control<TParent : Block, TDefaultTo : Block>(
    public override val parent: TParent,
    public override val specifier: By,
    protected val route: TParent.() -> TDefaultTo
) : Element(parent.session), HasParent<TParent> {
    public override val scope: SearchContext? get() = getOrNull()

    public val sequencers: SequencerList = SequencerList()

    private val tag: WebElement get() = TagFinder.find(this)

    public fun <TRouteTo : Block> interact(
        route: TParent.() -> TRouteTo,
        interaction: WebElement.() -> Unit
    ): TRouteTo {
        sequencers.sequence(tag, interaction)
        return parent.route()
    }

    public fun <T> read(retriever: WebElement.() -> T): T = retriever(tag)
}