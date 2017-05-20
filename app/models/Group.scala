package models

import javax.inject._
import play.api.db.Database
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
import play.api.Logger

case class Group(id: Int, display_name: String, members: List[GroupChildren], groups: List[GroupChildren], active: Int)
case class GroupChildren(id: Int, display_name: String)


object Group {
	val logger = Logger(this.getClass)

	    // Method to get group data from database based on input group id.
			def getGroup(gid: String, db: Database) : String= {
					var outString = ""
							val lookup = gid.toInt
							Logger.debug(s"lookup ==== $gid")

							val conn = db.getConnection()
							try {
								val stmt = conn.createStatement
										val membershipRs = stmt.executeQuery("SELECT * FROM sys.scim_membership where group_id ="+lookup)
										var memberListBuffer = new ListBuffer[GroupChildren]()
										var groupListBuffer = new ListBuffer[GroupChildren]()

										while(membershipRs.next()){
											val membership_type = membershipRs.getString("membership_type")
													val value_id = membershipRs.getInt("value_id")	
													val value_display = membershipRs.getString("value_display")	
													val group_child = GroupChildren(value_id, value_display)

													if(membership_type.equalsIgnoreCase("user"))
														memberListBuffer += group_child
														if(membership_type.equalsIgnoreCase("group"))
															groupListBuffer += group_child	 
										}

								val groupRs = stmt.executeQuery("SELECT * FROM sys.scim_group where group_id ="+lookup)
										while(groupRs.next()){
											val display_name = groupRs.getString("display_name")
													val active = groupRs.getInt("active")	
													val memberList = memberListBuffer.toList
													val groupList = groupListBuffer.toList
													val group = Group(lookup, display_name, memberList, groupList, active)
													Logger.debug(s"group ==== $group")

													val out = Json.toJson(group)
													outString = out.toString()
										}

							} finally {
								conn.close()
							}
					return outString
	}

	// Method to get group data from database based on input filter option.
	def filterSearch(countValue: Int, startIndexValue: Int, db: Database) : String = {
			var query = "SELECT * FROM sys.scim_membership"
					if(countValue != 0 && startIndexValue == -1){
						query = query + " LIMIT " + countValue
					}
			if(startIndexValue != -1 && countValue != 0){
				query = query + " LIMIT  " + startIndexValue + "," + countValue
			}	
			if(startIndexValue != -1 && countValue == 0){
				query = query + " LIMIT  " + countValue + "," + countValue
			}	
			var outString = ""
					var membershipListBuffer = new ListBuffer[String]()

					val conn = db.getConnection()
					try {
						val stmt = conn.createStatement
								val membershipRs = stmt.executeQuery(query)
								var memberListBuffer = new ListBuffer[GroupChildren]()
								var groupListBuffer = new ListBuffer[GroupChildren]()

								while(membershipRs.next()){
									val membership_type = membershipRs.getString("membership_type")
											val group_id = membershipRs.getInt("group_id")	
											val value_id = membershipRs.getInt("value_id")	
											val value_display = membershipRs.getString("value_display")	
											val group_child = GroupChildren(value_id, value_display)

											if(membership_type.equalsIgnoreCase("user"))
												memberListBuffer += group_child
												if(membership_type.equalsIgnoreCase("group"))
													groupListBuffer += group_child	 

													val grpStmt = conn.createStatement
													val groupRs = grpStmt.executeQuery("SELECT * FROM sys.scim_group where group_id ="+group_id)
													while(groupRs.next()){
														val display_name = groupRs.getString("display_name")
																val active = groupRs.getInt("active")	
																val memberList = memberListBuffer.toList
																val groupList = groupListBuffer.toList
																val group = Group(group_id, display_name, memberList, groupList, active)
																val out = Json.toJson(group)
																membershipListBuffer += out.toString()
													}				
								}
						val groupList = membershipListBuffer.toList
								val start = """{"Result":["""
								val end = """]}"""
								outString = groupList.mkString(start, ",", end)
								outString.toString()
					} finally {
						conn.close()
					}
	}

	implicit val GroupChildrenWrites: Writes[GroupChildren] = (
			(JsPath \ "id").write[Int] and
			(JsPath \ "display_name").write[String]
			)(unlift(GroupChildren.unapply))				


			implicit val GroupWrites: Writes[Group] = (
					(JsPath \ "id").write[Int] and
					(JsPath \ "display_name").write[String] and
					(JsPath \ "members").write[List[GroupChildren]] and
					(JsPath \ "groups").write[List[GroupChildren]] and
					(JsPath \ "active").write[Int]
					)(unlift(Group.unapply))
}
