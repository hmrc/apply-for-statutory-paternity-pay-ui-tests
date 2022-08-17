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

import java.time.LocalDate
import scala.util.Random

trait BasePage extends BrowserDriver with Matchers {
  val continueButton = "govuk-button"

  def findByID(id: String): WebElement               = driver.findElement(By.id(id))
  def click(id: String): Unit                        = findByID(id).click()
  def findByClassName(className: String): WebElement = driver.findElement(By.className(className))
  def enter(id: String, text: String): Unit          = findByID(id).sendKeys(text)

  val yesterdayDay: String   = LocalDate.now.minusDays(1).getDayOfMonth.toString
  val yesterdayMonth: String = LocalDate.now.minusDays(1).getMonthValue.toString
  val yesterdayYear: String  = LocalDate.now.minusDays(1).getYear.toString

  val tomorrowDay: String   = LocalDate.now.plusDays(1).getDayOfMonth.toString
  val tomorrowMonth: String = LocalDate.now.plusDays(1).getMonthValue.toString
  val tomorrowYear: String  = LocalDate.now.plusDays(1).getYear.toString

  val todayDay: String   = LocalDate.now.getDayOfMonth.toString
  val todayMonth: String = LocalDate.now.getMonthValue.toString
  val todayYear: String  = LocalDate.now.getYear.toString

  def enterDOB(): Unit = {
    findByID("value.day").sendKeys(yesterdayDay)
    findByID("value.month").sendKeys(yesterdayMonth)
    findByID("value.year").sendKeys(yesterdayYear)
  }
  def enterDate(): Unit = {
    findByID("value.day").sendKeys(todayDay)
    findByID("value.month").sendKeys(todayMonth)
    findByID("value.year").sendKeys(todayYear)
  }

  def enterTomorrowDate(): Unit = {
    findByID("value.day").sendKeys(tomorrowDay)
    findByID("value.month").sendKeys(tomorrowMonth)
    findByID("value.year").sendKeys(tomorrowYear)
  }
  val random = new Random

  def submitPage(): Unit =
    driver.findElement(By.className(continueButton)).click()

  def onPage(pageTitle: String, section: Option[String] = None): Unit = {
    val expectedTitle = pageTitle + section
      .map(" - " + _)
      .getOrElse("") + " - Ask your employer for Statutory Paternity Pay or Paternity Leave or both - GOV.UK"
    driver.getTitle shouldBe expectedTitle
  }
}

case class PageNotFoundException(s: String) extends Exception(s)
