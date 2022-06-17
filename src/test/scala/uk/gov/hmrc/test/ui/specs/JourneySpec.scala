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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.CheckYourAnswers.confirmAnswers
import uk.gov.hmrc.test.ui.pages.Confirmation.result
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class JourneySpec extends BaseSpec {

  Feature("Journeys for completing form and generating PDF") {

    Scenario(
      "Father with responsibility for child who has been born applying for SPP not on the day it was born",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectYes
      ResponsibilityForChild.selectYes
      TimeOffWork.selectYes.enterName.enterNino
      HasBabyBeenBornYet.selectYes.enterBabyDOB
      DoYouWantPayToStartOnDOB.selectNo
      DateYouWantSPToStart.enterStartDate.select1Week

      Then("I confirm my answers and will be given the option to download the form")
      confirmAnswers
      result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Partner supporting the mother applying for SPP for unborn baby",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectNo
      MarriageCivilPartnershipWithMother.selectNo
      EnduringFamilyRelationship.selectYes
      ResponsibilityForChild.selectYes
      TimeOffWork.selectNo
      SupportChildsMother.selectYes.enterName.enterNino
      HasBabyBeenBornYet.selectNo.enterBabyDueDate
      DoYouWantPayToStartOnDueDate.selectNo.enterStartDateDue.select1Week

      Then("I confirm my answers and will be given the option to download the form")
      confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

  }

  Feature("Kickout Journeys") {
    Scenario("Applicant is becoming adoptive or parental order parents", ZapTests) {

      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I select yes to becoming adoptive or parental order parents")
      AreYouPartnerOrAdoptingChild.selectYes

      Then("I should be instructed to use a different form")
      UseDifferentForm.result should be("You must use a different form to claim Statutory Paternity Pay")
    }

    Scenario("Applicant is not eligible for Statutory Paternity Pay", ZapTests) {

      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I answer that I will not have responsibility for caring for the child")
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectYes
      ResponsibilityForChild.selectNo

      Then("I will be told I am not eligible for Statutory Paternity Pay ")
      Ineligible.result should be("You are not eligible for Statutory Paternity Pay")
    }
  }

}
