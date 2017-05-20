package models

import javax.inject._
import play.api.db.Database
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
import play.api.Logger

case class UpdateMember(given_name: String, family_name: String, active: Int)

object UpdateMember {
	val logger = Logger(this.getClass)

			// Method to update user data from database based on input user id and data.
			def updateFields(mbr: UpdateMember, uid: String, db: Database) = {
					val lookup = uid.toInt
							val given_name = mbr.given_name
							val family_name = mbr.family_name
							val active = mbr.active

							Logger.debug(s"lookup ==== $lookup")
							val conn = db.getConnection()
							try {
								val stmt = conn.createStatement
										var query = "UPDATE sys.scim_name SET given_name = \""+given_name+"\", family_name = \""+family_name+"\" WHERE user_id = "+ lookup
										Logger.debug(s"query ==== $query")
										stmt.executeUpdate(query)
										Logger.debug(s"Updated names")

										query = "UPDATE sys.scim_user SET active = "+active+" WHERE user_id = "+ lookup
										Logger.debug(s"query ==== $query")
										stmt.executeUpdate(query)
										Logger.debug(s"Updated active")
							} finally {
								conn.close()
							}
	}
	implicit val UpdateMemberWrites: Writes[UpdateMember] = (
			(JsPath \ "given_name").write[String] and
			(JsPath \ "family_name").write[String] and
			(JsPath \ "active").write[Int]
			)(unlift(UpdateMember.unapply))		

			implicit val UpdateMemberReads: Reads[UpdateMember] = (
					(JsPath \ "given_name").read[String] and
					(JsPath \ "family_name").read[String] and
					(JsPath \ "active").read[Int]
					)(UpdateMember.apply _)			
}
