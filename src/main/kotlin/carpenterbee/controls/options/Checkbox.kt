// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.controls.options

import carpenterbee.*
import carpenterbee.controls.traits.HasText
import carpenterbee.functionality.Tag
import carpenterbee.functionality.waiting.TagFinder
import carpenterbee.specifiers.Specifiers
import org.openqa.selenium.By

internal fun <TOption : Option<*, *>> TOption.getLabel(): String {
    val labelId = this.read { getAttribute(Tag.id) }
    val labelBy = Specifiers.css(":root label[for='$labelId']")
    val label = TagFinder.findOrNull(this) { scope.findOrNull(labelBy) }
    return label?.text ?: throw IllegalStateException("Attempted to find label for unlabelled element.")
}

public open class Checkbox<TParent : Block, TDefaultRoute : Block>(
    parent: TParent,
    specifier: By,
    route: (TParent) -> TDefaultRoute
) : Option<TParent, TDefaultRoute>(parent, specifier, route), HasText {
    public override val text get() = getLabel()
}