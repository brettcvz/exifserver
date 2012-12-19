package controllers
import java.io._
import com.drew.imaging._
import scala.collection.JavaConversions._
import java.net._
import play.api.lib.json._

object MetaData {
  def convertUrl(urlstr: String) = {
    val conn = new URL(urlstr) openConnection
    val strm = new BufferedInputStream(conn.openStream())
    val metadata = ImageMetadataReader.readMetadata(strm, true)
    println("Number of directories" + metadata.getDirectories().toList)
    val lst = for  {
      d <- metadata.getDirectories().toList
      t <- d.getTags
    }
    yield (t.getTagName -> t.toString())
    Json.toJson(lst)
  }
}
