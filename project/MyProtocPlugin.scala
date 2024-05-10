import com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse
import protocbridge.ProtocCodeGenerator
import scala.jdk.CollectionConverters.*

object MyProtocPlugin extends ProtocCodeGenerator {
  override def run(request: Array[Byte]): Array[Byte] = {
    run0(CodeGeneratorRequest.parseFrom(request)).toByteArray
  }

  private def run0(req: CodeGeneratorRequest): CodeGeneratorResponse = {
    val toGenerate = req.getFileToGenerateList.asScala.toSet
    val files = req.getProtoFileList.asScala.filter(p => toGenerate.contains(p.getName))
    files.foreach { file =>
      file.getMessageTypeList.asScala.foreach { msg =>
        msg.getFieldList.asScala.foreach {
          field =>
            field
              .getOptions
              .getUnknownFields
              .getField(example.MyOption.FIELD_FIELD_NUMBER)
              .getLengthDelimitedList
              .asScala
              .foreach{ b =>
                if (example.MyOption.ExampleFieldOptions.parseFrom(b).getFoo) {
                  println(s"${msg.getName}.${field.getName} has foo")
                }
              }
        }
      }
    }
    CodeGeneratorResponse.newBuilder().build()
  }
}
