package models

import javax.inject._
import play.api.db.Database
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
import play.api.Logger

case class UpdateGroup(action: String, display_name: String, user_id: Int)

object UpdateGroup {
	val logger = Logger(this.getClass)

			// Method to update group data from database based on input group id and data.
			def updateFields(grp: UpdateGroup, group_id: String, db: Database) = {
					val action = grp.action
							val value_name = grp.display_name
							val value_id = grp.user_id

							val conn = db.getConnection()
							try {
								val stmt = conn.createStatement
										var query = ""
										if(action.equalsIgnoreCase("add")){
											val membership_type = "user"
													query = "INSERT into sys.scim_membership (group_id, membership_type, value_id, value_display) VALUES ("+group_id+",\""+membership_type+"\",\""+value_id+"\",\""+value_name+"\")"	
										}else	
											query = "DELETE FROM sys.scim_membership WHERE group_id = " + group_id + " and value_id = "  + value_id

											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"Updated group")
							} finally {
								conn.close()
							}
	}

	implicit val UpdateMemberWrites: Writes[UpdateGroup] = (
			(JsPath \ "action").write[String] and
			(JsPath \ "display_name").write[String] and
			(JsPath \ "user_id").write[Int]
			)(unlift(UpdateGroup.unapply))		

			implicit val UpdateMemberReads: Reads[UpdateGroup] = (
					(JsPath \ "action").read[String] and
					(JsPath \ "display_name").read[String] and
					(JsPath \ "user_id").read[Int]
					)(UpdateGroup.apply _)			
}
