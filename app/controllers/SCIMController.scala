package controllers

import javax.inject._
import play.api.db.Database
import play.api.mvc._
import models.Member
import models.Group
import play.api.libs.json._
import models.UpdateMember
import models.UpdateGroup

class SCIMController @Inject() (db:Database) extends Controller {

	def users(filter:Option[String], count:Option[String], startIndex:Option[String]) = Action{
		//  Retrieve paginated User Objects
		//  Allow for an equals and startsWith filters on username
		val filterValue = filter getOrElse "default"
				val countValue = count getOrElse "0"
				val startIndexValue = startIndex getOrElse "-1"

				var outString = Member.filterSearch(filterValue, countValue.toInt, startIndexValue.toInt, db)
				Ok(outString.toString())
	}

	def user(uid:String) = Action {
		//  Retrieve a single Member Object by ID
		var outString = Member.getMember(uid, db)
				Ok(outString.toString())
	}

	def createUser() = Action(BodyParsers.parse.json) {request =>
	// Create a User Object with firstname and lastname metadata
	val memberResult = request.body.validate[Member]
			memberResult.fold(
					errors => {
						BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(errors)))
					},
					member => { 
						Member.saveMember(member, db)
						Ok(Json.obj("status" ->"OK", "message" -> ("Created member.") ))  
					}
					)	  
	}

	def updateUser(uid:String) = Action(BodyParsers.parse.json) {request =>
	// Update a User Object's firstname, lastname, and active status
	val updateResult = request.body.validate[UpdateMember]
			updateResult.fold(
					errors => {
						BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(errors)))
					},
					updateMember => { 
						UpdateMember.updateFields(updateMember, uid, db)
						Ok(Json.obj("status" ->"OK", "message" -> ("Updated user id: "+uid) ))  
					}
					)	  
	}

	def deleteUser(uid:String) = Action {
		// Delete a User Object by ID
		var outString = Member.deleteMember(uid, db)
				Ok(Json.obj("status" ->"OK", "message" -> ("Deleted user id: "+uid) ))  
	}

	def groups(count:Option[String], startIndex:Option[String]) = Action {
			// Retrieve paginated Group Objects
					val countValue = count getOrElse "0"
					val startIndexValue = startIndex getOrElse "-1"

					var outString = Group.filterSearch(countValue.toInt, startIndexValue.toInt, db)
					Ok(outString.toString())
	}

	def group(groupId:String) = Action {
		// Retrieve a single Group Object by ID
		var outString = Group.getGroup(groupId, db)
				Ok(outString.toString())
	}

	def patchGroup(groupId:String) = Action(BodyParsers.parse.json) {request =>
	// Patch a Group Object, modifying its members
	val updateResult = request.body.validate[UpdateGroup]
			updateResult.fold(
					errors => {
						BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(errors)))
					},
					updateGroup => { 
						UpdateGroup.updateFields(updateGroup, groupId, db)
						Ok(Json.obj("status" ->"OK", "message" -> ("Updated group id: "+groupId) ))  
					}
					)	 
	}


}
