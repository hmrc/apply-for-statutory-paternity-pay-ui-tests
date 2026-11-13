import sbt.*

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"       %% "ui-test-runner"      % "0.58.0"   % Test,
    "org.scalatestplus" %% "selenium-4-12"       % "3.2.17.0" % Test,
    "org.slf4j"         % "slf4j-simple"         % "2.0.17"   % Test,
    "org.mongodb.scala" %% "mongo-scala-driver"  % "5.14.0",
    "uk.gov.hmrc"       %% "domain-test-play-30" % "13.0.0" % Test
  )
}
