package com.example.applicationrociosieiro.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationrociosieiro.R
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

class SplashActivity : AppCompatActivity() {

    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // Set the connection parameters
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams,
            object : Connector.ConnectionListener {
                override fun onFailure(throwable: Throwable?) {
                    Log.e("MyActivity", throwable?.message, throwable)

                    // Something went wrong when attempting to connect! Handle errors here
                }

                override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("SplashActivity", "Connected! Yay!")

                    // Now you can start interacting with App Remote
                    //connected()
                }
            })
    }


    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    companion object {
        private const val CLIENT_ID = "0109fcc33ca147edbe1fe1e28dd05ffe"
        private const val REDIRECT_URI = "com.example.applicationrociosieiro://callback"
    }
}