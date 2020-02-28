// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.driving

import carpenterbee.Element
import java.net.URL

public fun <TElement : Element> TElement.urlOf(property: TElement.() -> String?): URL? =
    property()?.let { URL(URL(session.driver.currentUrl), it) }