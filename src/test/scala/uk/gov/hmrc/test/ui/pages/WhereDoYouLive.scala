/*
 * Copyright 2024 HM Revenue & Customs
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

object WhereDoYouLive extends BasePage {

  val title = "Where do you live?"

  def selectEngland: AreYouPartnerOrAdoptingChild.type = {
    onPage(title)
    click("value_0")
    submitPage()
    AreYouPartnerOrAdoptingChild
  }

  def selectNorthernIreland: AreYouPartnerOrAdoptingChild.type = {
    onPage(title)
    click("value_3")
    submitPage()
    AreYouPartnerOrAdoptingChild
  }

}
