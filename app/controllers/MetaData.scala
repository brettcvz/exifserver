import java.io._
import com.drew.imaging._
import scala.collections.JavaConversions._

object Exif {

  def convert(f: File) = {
    val metadata = ImageMetadataReader.readMetadata(f)
    for  {
      d <- metadata.getDirectories().toList
      t <- d.getTags
    }
    yield (t.getTagName -> t.toString())
  }
 
  def main(args: String) = {
    val f = new File(args(0))
    val ret = convert(f)
    println(ret.mkString(","))
  }
}