Compile / PB.targets += (PB.gens.java -> ((Compile / sourceManaged).value / "scalapb"))
libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.25.3" % "protobuf"
addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.7")
