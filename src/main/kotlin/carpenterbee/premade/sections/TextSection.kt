// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.premade.sections

import carpenterbee.*
import carpenterbee.premade.HasText

public class TextSection<TParent : Block>(parent: TParent, specifier: By) :
    Section<TParent>(parent, specifier), HasText {

    override val text: String
        get() = tag.text
}