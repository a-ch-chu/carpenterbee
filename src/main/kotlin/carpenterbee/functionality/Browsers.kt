// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.functionality

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.safari.SafariDriver

object Browsers {
    private fun <TDriver : WebDriver> create(
        constructor: () -> TDriver
    ): () -> TDriver = { ->
        constructor().apply { manage().window().maximize() }
    }

    public val chrome get() = create(::ChromeDriver)
    public val firefox get() = create(::FirefoxDriver)
    public val ie get() = create(::InternetExplorerDriver)
    public val microsoftEdge get() = create(::EdgeDriver)
    public val opera get() = create(::OperaDriver)
    public val safari get() = create(::SafariDriver)

    public val headless get() = HeadlessBrowsers
}

public object HeadlessBrowsers {
    public val chrome: () -> ChromeDriver
        get() = { ChromeDriver(ChromeOptions().setHeadless((true))) }
    public val firefox: () -> FirefoxDriver
        get() = { FirefoxDriver(FirefoxOptions().setHeadless(true)) }
}