package com.ss.daggerhiltdi.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Users(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: ArrayList<Result?>
) : Parcelable {

    @SuppressLint("ParcelCreator")
    @Parcelize
    @Keep
    data class Info(
        @SerializedName("page")
        val page: Int?, // 1
        @SerializedName("results")
        val results: Int?, // 10
        @SerializedName("seed")
        val seed: String?, // ac285a4459f0a912
        @SerializedName("version")
        val version: String? // 1.3
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    @Keep
    data class Result(
        @SerializedName("cell")
        val cell: String?, // 0412-869-537
        @SerializedName("dob")
        val dob: Dob?,
        @SerializedName("email")
        val email: String?, // connor.pena@example.com
        @SerializedName("gender")
        val gender: String?, // male
        @SerializedName("id")
        val id: Id?,
        @SerializedName("location")
        val location: Location?,
        @SerializedName("login")
        val login: Login?,
        @SerializedName("name")
        val name: Name?,
        @SerializedName("nat")
        val nat: String?, // AU
        @SerializedName("phone")
        val phone: String?, // 00-4098-7382
        @SerializedName("picture")
        val picture: Picture?,
        @SerializedName("registered")
        val registered: Registered?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Dob(
            @SerializedName("age")
            val age: Int?, // 67
            @SerializedName("date")
            val date: String? // 1953-02-07T15:44:43.202Z
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Id(
            @SerializedName("name")
            val name: String?, // TFN
            @SerializedName("value")
            val value: String? // 699568620
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Location(
            @SerializedName("city")
            val city: String?, // Maitland
            @SerializedName("coordinates")
            val coordinates: Coordinates?,
            @SerializedName("country")
            val country: String?, // Australia
            @SerializedName("postcode")
            val postcode: String?, // null
            @SerializedName("state")
            val state: String?, // Queensland
            @SerializedName("street")
            val street: Street?,
            @SerializedName("timezone")
            val timezone: Timezone?
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            @Keep
            data class Coordinates(
                @SerializedName("latitude")
                val latitude: String?, // -81.5748
                @SerializedName("longitude")
                val longitude: String? // -134.3842
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            @Keep
            data class Street(
                @SerializedName("name")
                val name: String?, // Rolling Green Rd
                @SerializedName("number")
                val number: Int? // 1142
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            @Keep
            data class Timezone(
                @SerializedName("description")
                val description: String?, // Brazil, Buenos Aires, Georgetown
                @SerializedName("offset")
                val offset: String? // -3:00
            ) : Parcelable
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Login(
            @SerializedName("md5")
            val md5: String?, // 653b3ce07630ad23187db91d2e17ab8f
            @SerializedName("password")
            val password: String?, // komodo
            @SerializedName("salt")
            val salt: String?, // jtxrbXpm
            @SerializedName("sha1")
            val sha1: String?, // 663e29a6f05ee7210bbc61c65b41c5e45101b5bd
            @SerializedName("sha256")
            val sha256: String?, // ad3f6663fe9f563a016cb0f38b7ad8fcd5f607877fe4f4ef0412de2cb2d22214
            @SerializedName("username")
            val username: String?, // purplecat658
            @SerializedName("uuid")
            val uuid: String? // 3b51b87e-2454-40c3-b8a2-92b1060edb5c
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Name(
            @SerializedName("first")
            val first: String?, // Connor
            @SerializedName("last")
            val last: String?, // Pena
            @SerializedName("title")
            val title: String? // Mr
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Picture(
            @SerializedName("large")
            val large: String?, // https://randomuser.me/api/portraits/men/10.jpg
            @SerializedName("medium")
            val medium: String?, // https://randomuser.me/api/portraits/med/men/10.jpg
            @SerializedName("thumbnail")
            val thumbnail: String? // https://randomuser.me/api/portraits/thumb/men/10.jpg
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Registered(
            @SerializedName("age")
            val age: Int?, // 5
            @SerializedName("date")
            val date: String? // 2015-04-29T09:16:28.083Z
        ) : Parcelable
    }
}