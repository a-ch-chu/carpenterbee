// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee

import carpenterbee.functionality.*
import carpenterbee.functionality.interfaces.HasName
import carpenterbee.functionality.interfaces.IsFindable
import carpenterbee.functionality.specifiers.Specifiers
import carpenterbee.sequencers.SequencerList

public sealed class Element(val session: Session) : HasName {
    init {
        session.track(this)
    }

    public override val name: String = deriveName()

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
) : Block(parent.session), IsFindable<TParent> {
    public override val scope: SearchContext? get() = getTagOrNull()

    public fun readTag(name: String): String = tag.getAttrOrThrow(name)
    public val tag: WebElement get() = TagFinder.find(this)
}

public abstract class Control<TParent : Block, TDefaultTo : Block>(
    public override val parent: TParent,
    public override val specifier: By,
    protected val route: TParent.() -> TDefaultTo
) : Element(parent.session), IsFindable<TParent> {
    public override val scope: SearchContext? get() = getTagOrNull()

    public val sequencers: SequencerList = SequencerList()

    private val tag: WebElement get() = TagFinder.find(this)

    public fun <TRouteTo : Block> interact(
        route: TParent.() -> TRouteTo,
        interaction: WebElement.() -> Unit
    ): TRouteTo {
        sequencers.sequence(tag, interaction)
        return parent.route()
    }

    public fun read(name: String): String = read { getAttrOrThrow(name) }
    public fun <T> read(retriever: WebElement.() -> T): T = retriever(tag)
}