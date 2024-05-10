scalaVersion := "3.3.3"

Compile / PB.targets += (PB.gens.java -> ((Compile / sourceManaged).value / "proto"))

Compile / PB.targets += MyProtocPlugin -> (Compile / sourceManaged).value

libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.25.3" % "protobuf"
