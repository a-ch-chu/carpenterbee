// Library
@file:Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused")

package carpenterbee.browsers

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

private val chromeHeadlessOptions get() = listOf(headlessOption, "--disable-gpu", "--no-sandbox")
private const val headlessOption = "--headless"

public val Browsers.headless get() = HeadlessBrowsers

public object HeadlessBrowsers {
    public val chrome get() = ChromeDriver(ChromeOptions().apply { addArguments(chromeHeadlessOptions) })
    public val firefox get() = FirefoxDriver(FirefoxOptions().apply { addArguments(headlessOption) })
}