import com.google.gson.annotations.SerializedName




data class CatagoryList (

	@SerializedName("id") val id : Int,
	@SerializedName("category_name") val category_name : String,
	@SerializedName("position") val position : Int,
	@SerializedName("status") val status : Boolean,
	@SerializedName("image") val image : String
)