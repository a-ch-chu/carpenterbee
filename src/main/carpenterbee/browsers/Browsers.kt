// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.browsers

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.safari.SafariDriver

object Browsers {
    private fun <TDriver : WebDriver> create(constructor: () -> TDriver) =
        constructor().apply { manage().window().maximize() }

    public val chrome get() = create(::ChromeDriver)
    public val firefox get() = create(::FirefoxDriver)
    public val internetExplorer get() = create(::InternetExplorerDriver)
    public val microsoftEdge get() = create(::EdgeDriver)
    public val safari get() = create(::SafariDriver)
}