import com.google.gson.annotations.SerializedName





data class User_info (

	@SerializedName("id") val id : Int,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : Int,
	@SerializedName("firstname") val firstname : String,
	@SerializedName("lastname") val lastname : String,
	@SerializedName("photo") val photo : String,
	@SerializedName("company_id") val company_id : Int
)