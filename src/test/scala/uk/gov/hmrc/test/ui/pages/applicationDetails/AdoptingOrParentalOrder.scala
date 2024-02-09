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

package uk.gov.hmrc.test.ui.pages.applicationDetails

import uk.gov.hmrc.test.ui.pages.BasePage
import uk.gov.hmrc.test.ui.pages.relationshipWithChild.BiologicalFather

object AdoptingOrParentalOrder extends BasePage {

  val title = "Are you or your partner adopting a child or becoming parental order parents?"

  def selectNo: BiologicalFather.type = {
    onPage(title)
    click("value-no")
    submitPage()
    BiologicalFather
  }

  def selectYes: ApplyingForStatutoryAdoptionPay.type = {
    onPage(title)
    click("value")
    submitPage()
    ApplyingForStatutoryAdoptionPay
  }

}
