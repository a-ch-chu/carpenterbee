// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.construction

import carpenterbee.Control
import carpenterbee.Tag
import carpenterbee.getAttrOrNull
import carpenterbee.functionality.TagFinder
import carpenterbee.functionality.getTagOrNull
import carpenterbee.functionality.specifiers.Specifiers

private val noLabelException
    get() = UnsupportedOperationException(
        "Attempted to find label for unlabelled element."
    )

public fun <TControl : Control<*, *>> TControl.getLabel(): String {
    val labelId = read { getAttrOrNull(Tag.id) } ?: throw noLabelException
    val labelBy = Specifiers.css(":root label[for='$labelId']")
    val label = TagFinder.findOrNull(this) { scope.getTagOrNull(labelBy) }
    return label?.text ?: throw noLabelException
}