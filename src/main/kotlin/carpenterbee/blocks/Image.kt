// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.blocks

import carpenterbee.*
import org.openqa.selenium.By
import java.net.URL

public open class Image<TParent : Block>(parent: TParent, specifier: By) : Section<TParent>(parent, specifier) {
    public val source: String?
        get() = tag.getAttribute("src")

    public val sourceUrl: URL?
        get() =
            if (source == null) null
            else URL(URL(session.driver.currentUrl), source)
}