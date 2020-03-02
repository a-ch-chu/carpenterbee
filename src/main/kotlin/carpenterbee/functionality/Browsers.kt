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
    private fun <TDriver : WebDriver> create(constructor: () -> TDriver) =
        constructor().apply { manage().window().maximize() }

    public val chrome: () -> ChromeDriver get() = { create(::ChromeDriver) }
    public val firefox: () -> FirefoxDriver get() = { create(::FirefoxDriver) }
    public val internetExplorer: () -> InternetExplorerDriver get() = { create(::InternetExplorerDriver) }
    public val microsoftEdge: () -> EdgeDriver get() = { create(::EdgeDriver) }
    public val opera: () -> OperaDriver get() = { create(::OperaDriver) }
    public val safari: () -> SafariDriver get() = { create(::SafariDriver) }

    public val headless: HeadlessBrowsers get() = HeadlessBrowsers
}

public object HeadlessBrowsers {
    public val chrome: () -> ChromeDriver get() = { ChromeDriver(ChromeOptions().setHeadless((true))) }
    public val firefox: () -> FirefoxDriver get() = { FirefoxDriver(FirefoxOptions().setHeadless(true)) }
}