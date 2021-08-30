package com.quickghy.qgdaksha.data.dash.home.repositories

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat
import com.quickghy.qgdaksha.data.PrefDataStore
import com.quickghy.qgdaksha.data.auth.network.AuthMainApis
import com.quickghy.qgdaksha.data.auth.network.Response.*
import com.quickghy.qgdaksha.data.auth.network.request.UserData
import com.quickghy.qgdaksha.data.auth.network.request.loginViaOtp
import com.quickghy.qgdaksha.data.auth.network.request.loginWithPass
import com.quickghy.qgdaksha.data.auth.network.request.signupRequest
import com.quickghy.qgdaksha.data.dash.profile.network.ProfileApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

/**
 * @Author: Sanjeev Kumar
 * @Date: 21-06-2021
 */

class HomeRepository(
    val context: Context,
    val APICALL:ProfileApi,
    val dataStore: PrefDataStore
) {

    val AL = ArrayList<String>()

    suspend fun saveDATAtoDS(uid: String, uname: String,uphone: String,email: String,isadmin:String,isserviceprovider: String,utoken: String)
    {
        dataStore.savedetailstoDS(uid,uname,uphone,email,isadmin,isserviceprovider,utoken)
    }



    suspend fun isuserlogin(): String{
        var token =""
        Log.d("tttttInside",token)
        PrefDataStore(context).Token.collect{ value ->
            token = value.toString()
            //getAndSaveData("Bearer ${value.toString()}")
            Log.d("tttttInside",value.toString())

        }
        Log.d("tttttOutside",token)

        return token
    }

    suspend fun getAndSaveData(token: String): Boolean {
        var resp = APICALL.GetProfile(token)
        if (resp.isSuccessful){
            Log.d("trueresp",resp.body().toString())
            saveDATAtoDS(resp.body()?.data?._id.toString(),
                resp.body()?.data?.name.toString(),
                resp.body()?.data?.phoneNo.toString(),
                resp.body()?.data?.email.toString(),
                resp.body()?.data?.isAdmin.toString(),
                resp.body()?.data?.isServiceProvider.toString(),
                token)
            return true
        }
        else{
            Log.d("trueresp", resp.body().toString())
            return false
        }

    }

}