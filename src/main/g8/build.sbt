lazy val compilerSettings = Seq(
  scalacOptions ++= Seq(
    "-language:higherKinds",
    "-Ypartial-unification",
    "-Yliteral-types",
    "-encoding", "UTF-8",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:existentials"
  ),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"),
  addCompilerPlugin("com.github.mpilquist" %% "simulacrum" % "0.10.0")
)

lazy val commonResolvers = Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.jcenterRepo
)

lazy val buildSettings = Seq(
  scalaOrganization := "org.typelevel",
  scalaVersion := "2.12.1",
  name := "$name$",
  version := "$version$-SNAPSHOT"
)

lazy val commonSettings = Seq(
  resolvers ++= commonResolvers,
  libraryDependencies ++= Seq(
  )
) ++ compilerSettings

lazy val core = project.in(file("core"))
  .settings(commonSettings)
  .settings(buildSettings)
  .settings(name := "$name$-core")

lazy val docs = project.in(file("docs"))
  .settings(buildSettings)
  .settings(commonSettings)
  .settings(name := "$name$-docs")
  .dependsOn(core)
  .enablePlugins(MicrositesPlugin)
  .settings(
    micrositeName             := "$name$",
    micrositeDescription      := "Add a description",
    micrositeAuthor           := "zainab-ali",
    micrositeGithubOwner      := "zainab-ali",
    micrositeGithubRepo       := "$name$",
    micrositeBaseUrl          := "/$name$",
    git.remoteRepo := "git@github.com:zainab-ali/$name$.git",
    autoAPIMappings := true)

lazy val root = (project in file(".")).settings(
  buildSettings,
  commonSettings
).aggregate(core, docs)
