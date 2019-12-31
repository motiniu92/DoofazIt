package com.zhomprass.zhomprasslimited.ApiClient

import CatagoryList
import LoginResponse
import com.zhomprass.zhomprasslimited.Models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("login_api.php")
    fun login(
        @Query("user_name") email:String,
        @Query("password") password:String
    ):Call<List<UserInfo>>


    @GET("category_list.php")
    fun getCatagory():Call<List<CatagoryList>>


    @GET("category_list_sub.php")
    fun getSubCategory(@Query("cat_id") id:Int):Call<List<SubCategoryList>>

    @GET("category_list_third.php")
    fun getThiredCategory(@Query("sub_cat_id") id:Int):Call<List<ThiredCategoryList>>

    @GET("product.php")
    fun getOffers(@Query("offer") id: Int):Call<List<Offers>>


    @GET("product.php")
    fun getRecentProduct(@Query("recent") id: Int):Call<List<RecentProduct>>


    @GET("product.php")
    fun getSingleProduct(@Query("id") id: Int):Call<List<SingleProduct>>



    @GET("product.php")
    fun getMultiProduct(@Query("third_cat_id") id: Int):Call<List<MultiProducts>>


    @GET("product.php")
    fun getMultiProductByBrand(@Query("brand_id") id: Int):Call<List<MultiProducts>>


    @GET("product.php")
    fun getRelatedProduct(@Query("third_cat_id") id: Int):Call<List<RelatedProduct>>


    @GET("brand_list.php?")
    fun getBrands():Call<List<BrandList>>


    @GET("brand_list.php")
    fun getBrandsWithSub(@Query("cat_id") id: Int) :Call<List<SubBrandList>>

    @GET("brand_list.php")
    fun getBrandsWithThired(@Query("sub_cat_id") id: Int) :Call<List<ThiredBrandList>>

    @GET("slider.php?limit_from=0&limit=5")
    fun getSliderImage() :Call<List<SliderImage>>


    @GET("product.php")
    fun getScrolledProduct(@Query("limit_from") start: Int,
                           @Query("limit") end: Int):Call<List<RecentProduct>>




    @GET("total_member.php")
    fun getTotalZpl(@Query("type") zpl: String):Call<List<Members>>

    @GET("total_member.php")
    fun getTotalFree(@Query("type") free: String):Call<List<Members>>



    @GET("division.php")
    fun getDivision():Call<List<Division>>


    @GET("district.php")
    fun getDistrict(@Query("division_id") id:Int):Call<List<District>>

    @GET("thana.php")
    fun getThana(@Query("district_id") id: Int):Call<List<Thana>>

    @GET("bazar.php")
    fun getBazar(@Query("thana_id") id: Int):Call<List<Bazar>>

    @GET("shop.php")
    fun getShop(@Query("bazar_id") id: Int):Call<List<ShopList>>


    @GET("user_short_info.php")
    fun getUserInfo(@Query("customer_id") id: Int):Call<List<UserShortInfo>>

  @GET("shop_product.php")
    fun getShopProduct(@Query("shop_id") id: Int,
                       @Query("limit_from")limit:Int,
                       @Query("limit")end:Int):Call<List<MultiProducts>>





}