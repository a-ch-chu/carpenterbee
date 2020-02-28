// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.Block
import carpenterbee.By
import carpenterbee.controls.traits.HasText
import carpenterbee.extensions.getOrNull
import carpenterbee.functionality.id
import carpenterbee.functionality.TagFinder
import carpenterbee.functionality.specifiers.Specifiers

private val noLabelException
    get() = UnsupportedOperationException(
        "Attempted to find label for unlabelled element."
    )

internal fun <TOption : Option<*, *>> TOption.getLabel(): String {
    val labelId = this.read { id } ?: throw noLabelException
    val labelBy = Specifiers.css(":root label[for='$labelId']")
    val label = TagFinder.findOrNull(this) { scope.getOrNull(labelBy) }
    return label?.text ?: throw noLabelException
}

public open class Checkbox<TParent : Block, TDefaultTo : Block>(
    parent: TParent,
    specifier: By,
    route: TParent.() -> TDefaultTo
) : Option<TParent, TDefaultTo>(parent, specifier, route), HasText {
    public override val text get() = getLabel()
}