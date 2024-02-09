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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class JourneySpec extends BaseSpec {

  Feature("Journeys for completing form and generating PDF") {

    Scenario(
      "Biological parent in England " +
        "whose baby has been born, " +
        "who will be using leave to care for their child.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectYes
      ResponsibilityForChild.selectYes
      TimeOffToCareForChild.selectYes
      WhatIsYourName.enterName
      WhatIsYourNino.enterNino
      HasBabyBeenBornYet.selectYes
      WhenWasBabyBorn.enterBabyDOB
      DoYouWantPayToStartOnDOB.selectNo
      DateYouWantSPToStart.enterStartDate
      WhenWasBabyDue.enterBabyDueDate
      HowLongForPaternityLeave.select1Week

      Then("I confirm my answers and will be given the option to download the form")
      CheckYourAnswers.confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Biological parent in England " +
        "in an enduring family relationship, " +
        "whose baby has not yet been born, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectNo
      MarriageCivilPartnershipWithMother.selectNo
      EnduringFamilyRelationship.selectYes
      ResponsibilityForChild.selectYes
      TimeOffToCareForChild.selectNo
      TimeOffToSupportOtherParent.selectYes
      WhatIsYourName.enterName
      WhatIsYourNino.enterNino
      HasBabyBeenBornYet.selectNo
      WhenIsBabyDue.enterBabyDueDate
      DoYouWantPayToStartOnDueDate.selectNo
      DateYouWantSPToStart.enterStartDateDue
      HowLongForPaternityLeave.select1Week

      Then("I confirm my answers and will be given the option to download the form")
      CheckYourAnswers.confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Adoptive parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectAdopting
      MarriageCivilPartnershipAdopting.selectNo
      EnduringFamilyRelationshipAdopting.selectYes
      ResponsibilityForChild.selectYes
      TimeOffToCareForChild.selectYes

      Then("I will reach the 'What is your name' page")
      WhatIsYourName.onPage(WhatIsYourName.title)
    }

    Scenario(
      "Supporting adoptive parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectSupportingAdopting
      MarriageCivilPartnershipSupportingAdopting.selectNo
      EnduringFamilyRelationshipSupportingAdopting.selectYes
      ResponsibilityForChild.selectYes
      TimeOffToCareForChild.selectYes

      Then("I will reach the 'What is your name' page")
      WhatIsYourName.onPage(WhatIsYourName.title)
    }

    Scenario(
      "Parental order parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectParentalOrder
      MarriageCivilPartnershipParentalOrder.selectNo
      EnduringFamilyRelationshipParentalOrder.selectYes
      ResponsibilityForChild.selectYes
      TimeOffToCareForChild.selectYes

      Then("I will reach the 'What is your name' page")
      WhatIsYourName.onPage(WhatIsYourName.title)
    }

  }

  Feature("Kickout Journeys") {

    Scenario("Applicant is not eligible for Statutory Paternity Pay", ZapTests) {

      Given("I am on the Apply for SSP Home Page")
      ApplyForSPPHomePage.loadPage.startApplication

      When("I answer that I will not have responsibility for caring for the child")
      WhereDoYouLive.selectEngland
      AreYouPartnerOrAdoptingChild.selectNo
      AreYouBiologicalFather.selectYes
      ResponsibilityForChild.selectNo

      Then("I will be told I am not eligible for Statutory Paternity Pay ")
      Ineligible.result should be("You are not eligible for Statutory Paternity Pay and Statutory Paternity Leave")
    }
  }

}
