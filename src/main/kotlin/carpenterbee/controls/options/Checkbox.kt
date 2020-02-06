// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.Block
import carpenterbee.controls.traits.HasText
import carpenterbee.extensions.getOrNull
import carpenterbee.extensions.id
import carpenterbee.functionality.TagFinder
import carpenterbee.specifiers.Specifiers
import org.openqa.selenium.By

private val noLabelException
    get() = IllegalStateException("Attempted to find label for unlabelled element.")

internal fun <TOption : Option<*, *>> TOption.getLabel(): String {
    val labelId = this.read { id } ?: throw noLabelException
    val labelBy = Specifiers.css(":root label[for='$labelId']")
    val label = TagFinder.findOrNull(this) { scope.getOrNull(labelBy) }
    return label?.text ?: throw noLabelException
}

public open class Checkbox<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Option<TParent, TDefaultRoute>(parent, specifier, route), HasText {
    public override val text get() = getLabel()
}