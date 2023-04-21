/*
 * Copyright 2023 HM Revenue & Customs
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

import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object ApplyForSPPHomePage extends BasePage with Matchers {
  val url: String = TestConfiguration.url("apply-for-statutory-paternity-pay-frontend")
  val sppHomePage = "Ask your employer for Statutory Paternity Pay or Paternity Leave or both"

  def loadPage: this.type = {
    driver.navigate().to(url)
    onHomePage
    this
  }

  def startApplication: AreYouPartnerOrAdoptingChild.type = {
    click("start")
    AreYouPartnerOrAdoptingChild
  }

  def onHomePage: Unit =
    driver.getTitle shouldBe "Ask your employer for Statutory Paternity Pay or Paternity Leave or both - GOV.UK"
}
