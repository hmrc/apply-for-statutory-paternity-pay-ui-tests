/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.{By, WebElement}
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.driver.BrowserDriver

import scala.util.Random

trait BasePage extends BrowserDriver with Matchers {
  val continueButton = "govuk-button"

  def findByID(id: String): WebElement               = driver.findElement(By.id(id))
  def click(id: String): Unit                        = findByID(id).click()
  def findByClassName(className: String): WebElement = driver.findElement(By.className(className))
  def enter(id: String, text: String): Unit          = findByID(id).sendKeys(text)
  def enterDOB(): Unit = {
    findByID("value.day").sendKeys("1")
    findByID("value.month").sendKeys("1")
    findByID("value.year").sendKeys("2022")
  }
  def enterDate(): Unit = {
    findByID("value.day").sendKeys("2")
    findByID("value.month").sendKeys("1")
    findByID("value.year").sendKeys("2022")
  }
  val random                                         = new Random

  def submitPage(): Unit =
    driver.findElement(By.className(continueButton)).click()

  def onPage(pageTitle: String, section: Option[String] = None): Unit =
    if (
      driver.getTitle != pageTitle + section
        .map(" - " + _)
        .getOrElse("") + " - Ask your employer for Statutory Paternity Pay - GOV.UK"
    )
      throw PageNotFoundException(
        s"Expected '$pageTitle' page, but found '${driver.getTitle}' page."
      )
}

case class PageNotFoundException(s: String) extends Exception(s)
