import sbt.*

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"       %% "ui-test-runner"     % "0.43.0"   % Test,
    "org.scalatestplus" %% "selenium-4-12"      % "3.2.17.0" % Test,
    "org.slf4j"          % "slf4j-simple"       % "1.7.36"   % Test,
    "org.mongodb.scala" %% "mongo-scala-driver" % "4.11.1",
    "uk.gov.hmrc"       %% "domain-play-30"     % "9.0.0"
  )
}
