package models

import javax.inject._
import play.api.db.Database
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
import play.api.Logger

case class Member(display_name: String, user_name: String, user_type: String, title: String, active: Int, address: List[Address], 
		name: List[Name], phone: List[Phone], email: List[Email], groups: List[Int])
case class Address(address_type: String, street_address: String, city: String, 
		state: String, postal_code: Int, country: String, formatted_address: String, primary_address: Int)
case class Name(formatted_name: String, family_name: String, given_name: String, middle_name: String, prefix: String, suffix: String)
case class Phone(value: String, phone_type: String)
case class Email(value: String, email_type: String, primary_email: Int)


object Member {
	val logger = Logger(this.getClass)

	    // Method to get user data from database based on input user id
			def getMember(uid: String, db: Database) : String= {
					var outString = ""
							val lookup = uid.toInt
							Logger.debug(s"lookup ==== $lookup")

							val conn = db.getConnection()
							try {
								val stmt = conn.createStatement
										val addressRs = stmt.executeQuery("SELECT * FROM sys.scim_address where user_id ="+lookup)
										var addressListBuffer = new ListBuffer[Address]()
										var nameListBuffer = new ListBuffer[Name]()
										var phoneListBuffer = new ListBuffer[Phone]()
										var emailListBuffer = new ListBuffer[Email]()
										var membershipListBuffer = new ListBuffer[Int]()

										while(addressRs.next()){
											val address_type = addressRs.getString("type")
													val street_address = addressRs.getString("street_address")	
													val city = addressRs.getString("city")	
													val state = addressRs.getString("state")	
													val postal_code = addressRs.getInt("postal_code")	
													val country = addressRs.getString("country")	
													val formatted_address = addressRs.getString("formatted_address")	
													val primary_address = addressRs.getInt("primary_address")	
													val address = Address(address_type, street_address, city, state
															, postal_code, country, formatted_address, primary_address)
													addressListBuffer += address														
										}

								val nameRs = stmt.executeQuery("SELECT * FROM sys.scim_name where user_id ="+lookup)
										while(nameRs.next()){
											val formatted_name = nameRs.getString("formatted_name")
													val family_name = nameRs.getString("family_name")	
													val given_name = nameRs.getString("given_name")	
													val middle_name = nameRs.getString("middle_name")	
													val prefix = nameRs.getString("prefix")	
													val suffix = nameRs.getString("suffix")	
													val uname = Name(formatted_name, family_name, given_name, middle_name
															, prefix, suffix)
													nameListBuffer += uname
										}
								val phoneRs = stmt.executeQuery("SELECT * FROM sys.scim_phone_number where user_id ="+lookup)
										while(phoneRs.next()){
											val phone_value = phoneRs.getString("value")
													val phone_type = phoneRs.getString("type")	
													val phone = Phone(phone_value, phone_type)
													phoneListBuffer += phone
										}
								val emailRs = stmt.executeQuery("SELECT * FROM sys.scim_email where user_id ="+lookup)
										while(emailRs.next()){
											val email_value = emailRs.getString("value")
													val email_type = emailRs.getString("type")	
													val primary_email = emailRs.getInt("primary_email")	
													val email = Email(email_value, email_type, primary_email)
													emailListBuffer += email
										}

								val membershipRs = stmt.executeQuery("SELECT * FROM sys.scim_membership where value_id ="+lookup)
										while(membershipRs.next()){
											val group_id = membershipRs.getInt("group_id")
													membershipListBuffer += group_id
										}

								val memberRs = stmt.executeQuery("SELECT * FROM sys.scim_user where user_id ="+lookup)
										while(memberRs.next()){
											val user_id = memberRs.getInt("user_id")
													val user_name = memberRs.getString("user_name")
													val display_name = memberRs.getString("display_name")	
													val user_type = memberRs.getString("user_type")	
													val title = memberRs.getString("title")	
													val active = memberRs.getInt("active")	
													val addressList = addressListBuffer.toList
													val nameList = nameListBuffer.toList
													val phoneList = phoneListBuffer.toList
													val emailList = emailListBuffer.toList
													val membershipList = membershipListBuffer.toList

													val memb = Member(display_name, user_name,user_type,title,active, addressList, nameList, phoneList, emailList, membershipList)
													Logger.debug(s"memb ==== $memb")

													val out = Json.toJson(memb)
													outString = out.toString()
										}
							} finally {
								conn.close()
							}
					return outString
	}

	// Method to create a new user in database based on input user details
	def saveMember(mbr: Member, db: Database) = {
			val display_name = mbr.display_name
					val user_name = mbr.user_name
					val addressList = mbr.address
					val user_type = mbr.user_type
					val title = mbr.title
					val nameList = mbr.name
					val phoneList = mbr.phone
					val emailList = mbr.email
					val membershipList = mbr.groups
					val conn = db.getConnection()

					try{
						val stmt = conn.createStatement

								var query = "INSERT into sys.scim_user (user_name, display_name,user_type,title,active) VALUES (\""+user_name+"\",\""+display_name+"\",\"" +user_type+"\",\""+title+"\","+ 1+")"
								Logger.debug(s"query ==== $query")

								stmt.executeUpdate(query)
								Logger.debug(s"INSERTED user")

								val memberRs = stmt.executeQuery("SELECT * FROM sys.scim_user where user_name =\""+user_name+"\"")
								var user_id = -1
								while(memberRs.next()){
									user_id = memberRs.getInt("user_id")
								}

						var formatted_name = ""
								var family_name = "" 
								var given_name = "" 
								var middle_name = "" 
								var prefix = "" 
								var suffix= "" 
								for (name <- nameList){
									formatted_name =  name.formatted_name
											family_name =  name.family_name
											given_name =  name.given_name
											middle_name =  name.middle_name
											prefix =  name.prefix
											suffix =  name.suffix
											query = "INSERT into sys.scim_name (user_id, formatted_name, family_name,given_name,middle_name,prefix, suffix) VALUES (\""+user_id+"\",\""+formatted_name+"\",\""+family_name+"\",\"" +given_name+"\",\""+middle_name+"\",\""+prefix+"\",\""+suffix+"\")"
											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"INSERTED name")
								}


						var address_type = ""
								var street_address = "" 
								var city = "" 
								var state = "" 
								var postal_code = -1; 
						var country= ""
								var formatted_address= "" 
								var primary_address= 1 

								for (adrs <- addressList){
									address_type =  adrs.address_type
											street_address =  adrs.street_address
											city =  adrs.city
											state =  adrs.state
											postal_code =  adrs.postal_code
											country =  adrs.country
											formatted_address =  adrs.formatted_address
											primary_address =  adrs.primary_address
											query = "INSERT into sys.scim_address (user_id, type, street_address, city,state,postal_code,country, formatted_address,primary_address) VALUES (\""+user_id+"\",\""+address_type+"\",\""+street_address+"\",\""+city+"\",\"" +state+"\",\""+postal_code+"\",\""+country+"\",\""+formatted_address+"\",\""+primary_address+"\")"
											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"INSERTED address")
								}

						var phone_type = ""
								var phone_value = "" 
								for (phone <- phoneList){
									phone_type =  phone.phone_type
											phone_value =  phone.value

											query = "INSERT into sys.scim_phone_number (user_id, value, type) VALUES (\""+user_id+"\",\""+phone_value+"\",\""+phone_type+"\")"
											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"INSERTED phone")
								}

						var email_type = ""
								var email_value = "" 
								var primary_email = 1
								for (email <- emailList){
									email_type =  email.email_type
											email_value =  email.value
											primary_email = email.primary_email
											query = "INSERT into sys.scim_email (user_id, value, type, primary_email) VALUES (\""+user_id+"\",\""+email_value+"\",\""+email_type+"\",\""+primary_email+"\")"
											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"INSERTED email")
								}


						for (membership <- membershipList){
							val membership_type = "user"
									query = "INSERT into sys.scim_membership (group_id, membership_type, value_id, value_display) VALUES ("+membership+",\""+membership_type+"\",\""+user_id+"\",\""+display_name+"\")"
									Logger.debug(s"query ==== $query")
									stmt.executeUpdate(query)
									Logger.debug(s"INSERTED membership")
						}

					} finally {
						conn.close()
					}
	}

	// Method to delete a user from database based on input user id
	def deleteMember(uid: String, db: Database) = {
			val lookup = uid.toInt
					Logger.debug(s"lookup ==== $lookup")
					val tableList = List("sys.scim_name","sys.scim_email","sys.scim_phone_number", "sys.scim_address", "sys.scim_user")
					val conn = db.getConnection()
					try {
						val stmt = conn.createStatement
								for (table_name <- tableList){
									var query = "DELETE FROM "+table_name+" WHERE user_id = "+ lookup
											Logger.debug(s"query ==== $query")
											stmt.executeUpdate(query)
											Logger.debug(s"Deleted from $table_name")
								}
					} finally {
						conn.close()
					}
	}

	// Method to search user data from database based on input filter options
	def filterSearch(filterValue: String, countValue: Int, startIndexValue: Int, db: Database) : String = {
			var query = "SELECT * FROM sys.scim_user"
					if(!filterValue.equalsIgnoreCase("default")){
						query = query + " where user_name = \"" + filterValue + "\" OR user_name LIKE \"" + filterValue + "%\""
					}
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
					var memberListBuffer = new ListBuffer[String]()

					val conn = db.getConnection()
					try {
						val stmt = conn.createStatement
								val memberRs = stmt.executeQuery(query)
								while(memberRs.next()){
									val user_id = memberRs.getInt("user_id")
									val user_name = memberRs.getString("user_name")
									val display_name = memberRs.getString("display_name")	
									val user_type = memberRs.getString("user_type")	
									val title = memberRs.getString("title")	
									val active = memberRs.getInt("active")	

									val addStmt = conn.createStatement
									val addressRs = addStmt.executeQuery("SELECT * FROM sys.scim_address where user_id ="+user_id)
									var addressListBuffer = new ListBuffer[Address]()
									var nameListBuffer = new ListBuffer[Name]()
									var phoneListBuffer = new ListBuffer[Phone]()
									var emailListBuffer = new ListBuffer[Email]()
									var membershipListBuffer = new ListBuffer[Int]()

									while(addressRs.next()){
										val address_type = addressRs.getString("type")
												val street_address = addressRs.getString("street_address")	
												val city = addressRs.getString("city")	
												val state = addressRs.getString("state")	
												val postal_code = addressRs.getInt("postal_code")	
												val country = addressRs.getString("country")	
												val formatted_address = addressRs.getString("formatted_address")	
												val primary_address = addressRs.getInt("primary_address")	
												val address = Address(address_type, street_address, city, state
														, postal_code, country, formatted_address, primary_address)
												addressListBuffer += address														
									}

									val nameStmt = conn.createStatement
											val nameRs = nameStmt.executeQuery("SELECT * FROM sys.scim_name where user_id ="+user_id)
											while(nameRs.next()){
												val formatted_name = nameRs.getString("formatted_name")
														val family_name = nameRs.getString("family_name")	
														val given_name = nameRs.getString("given_name")	
														val middle_name = nameRs.getString("middle_name")	
														val prefix = nameRs.getString("prefix")	
														val suffix = nameRs.getString("suffix")	
														val uname = Name(formatted_name, family_name, given_name, middle_name
																, prefix, suffix)
														nameListBuffer += uname
											}

									val phoneStmt = conn.createStatement
											val phoneRs = phoneStmt.executeQuery("SELECT * FROM sys.scim_phone_number where user_id ="+user_id)
											while(phoneRs.next()){
												val phone_value = phoneRs.getString("value")
														val phone_type = phoneRs.getString("type")	
														val phone = Phone(phone_value, phone_type)
														phoneListBuffer += phone
											}

									val emailStmt = conn.createStatement
											val emailRs = emailStmt.executeQuery("SELECT * FROM sys.scim_email where user_id ="+user_id)
											while(emailRs.next()){
												val email_value = emailRs.getString("value")
														val email_type = emailRs.getString("type")	
														val primary_email = emailRs.getInt("primary_email")	
														val email = Email(email_value, email_type, primary_email)
														emailListBuffer += email
											}

									val membershipStmt = conn.createStatement
											val membershipRs = membershipStmt.executeQuery("SELECT * FROM sys.scim_membership where value_id ="+user_id)
											while(membershipRs.next()){
												val group_id = membershipRs.getInt("group_id")
														membershipListBuffer += group_id
											}

									val addressList = addressListBuffer.toList
											val nameList = nameListBuffer.toList
											val phoneList = phoneListBuffer.toList
											val emailList = emailListBuffer.toList
											val membershipList = membershipListBuffer.toList
											val memb = Member(display_name, user_name,user_type,title,active, addressList, nameList, phoneList, emailList, membershipList)
											val out = Json.toJson(memb)
											memberListBuffer += out.toString()
								}
						val memberList = memberListBuffer.toList
								val start = """{"Result":["""
								val end = """]}"""
								outString = memberList.mkString(start, ",", end)
								outString.toString()

					} finally {
						conn.close()
					}
	}

	implicit val NameWrites: Writes[Name] = (
			(JsPath \ "formatted_name").write[String] and
			(JsPath \ "family_name").write[String] and
			(JsPath \ "given_name").write[String] and
			(JsPath \ "middle_name").write[String] and
			(JsPath \ "prefix").write[String] and
			(JsPath \ "suffix").write[String] 
			)(unlift(Name.unapply))


			implicit val AddressWrites: Writes[Address] = (
					(JsPath \ "address_type").write[String] and
					(JsPath \ "street_address").write[String] and
					(JsPath \ "city").write[String] and
					(JsPath \ "state").write[String] and
					(JsPath \ "postal_code").write[Int] and
					(JsPath \ "country").write[String] and
					(JsPath \ "formatted_address").write[String] and
					(JsPath \ "primary_address").write[Int]
					)(unlift(Address.unapply))

			implicit val PhoneWrites: Writes[Phone] = (
					(JsPath \ "phone_type").write[String] and
					(JsPath \ "phone_numer").write[String]
					)(unlift(Phone.unapply))	

			implicit val EmailWrites: Writes[Email] = (
					(JsPath \ "email_type").write[String] and
					(JsPath \ "email_address").write[String] and
					(JsPath \ "primary_email").write[Int]
					)(unlift(Email.unapply))				


			implicit val MemberWrites: Writes[Member] = (
					(JsPath \ "user_name").write[String] and
					(JsPath \ "display_name").write[String] and
					(JsPath \ "user_type").write[String] and
					(JsPath \ "title").write[String] and
					(JsPath \ "active").write[Int] and
					(JsPath \ "user_address").write[List[Address]] and
					(JsPath \ "name").write[List[Name]] and
					(JsPath \ "user_phone").write[List[Phone]] and
					(JsPath \ "user_email").write[List[Email]] and
					(JsPath \ "groups").write[List[Int]]
					)(unlift(Member.unapply))

			implicit val EmailReads: Reads[Email] = (
					(JsPath \ "email_type").read[String] and
					(JsPath \ "email_address").read[String] and
					(JsPath \ "primary_email").read[Int]
					)(Email.apply _)		

			implicit val PhoneReads: Reads[Phone] = (
					(JsPath \ "phone_type").read[String] and
					(JsPath \ "phone_numer").read[String]
					)(Phone.apply _)		

			implicit val NameReads: Reads[Name] = (
					(JsPath \ "formatted_name").read[String] and
					(JsPath \ "family_name").read[String] and
					(JsPath \ "given_name").read[String] and
					(JsPath \ "middle_name").read[String] and
					(JsPath \ "prefix").read[String] and
					(JsPath \ "suffix").read[String] 
					)(Name.apply _)		


			implicit val AddressReads: Reads[Address] = (
					(JsPath \ "address_type").read[String] and
					(JsPath \ "street_address").read[String] and
					(JsPath \ "city").read[String] and
					(JsPath \ "state").read[String] and
					(JsPath \ "postal_code").read[Int] and
					(JsPath \ "country").read[String] and
					(JsPath \ "formatted_address").read[String] and
					(JsPath \ "primary_address").read[Int]
					)(Address.apply _)			

			implicit val MemberReads: Reads[Member] = (
					(JsPath \ "user_name").read[String] and
					(JsPath \ "display_name").read[String] and
					(JsPath \ "user_type").read[String] and
					(JsPath \ "title").read[String] and
					(JsPath \ "active").read[Int] and
					(JsPath \ "user_address").read[List[Address]] and
					(JsPath \ "name").read[List[Name]] and
					(JsPath \ "user_phone").read[List[Phone]] and
					(JsPath \ "user_email").read[List[Email]] and
					(JsPath \ "groups").read[List[Int]]
					)(Member.apply _)			

}
