package tr.com.harunkor.androidgifviewplayerkotlin

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import tr.com.harunkor.gifviewplayer.GifMovieView

class MainActivity : AppCompatActivity() {

    private var gifViewPlayer: GifMovieView? = null
    private var pauseBtn: Button? = null
    private var playBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pauseBtn = findViewById(R.id.pausebtn) as Button
        playBtn = findViewById(R.id.playbtn) as Button
        gifViewPlayer  = findViewById(R.id.gifViewPlayer) as GifMovieView



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2909)
            }
        }



              gifViewPlayer!!.setMovieResource(R.drawable.carkifelek)
//            gifViewPlayer?.setMovie(gifViewPlayer?.getMovie()!!);
//            gifViewPlayer?.setVisibility(View.INVISIBLE);
//            gifViewPlayer?.setVisibility(View.VISIBLE);
//            gifViewPlayer?.setMovieAssets("eat.gif");
//            var path:String = Environment.getExternalStorageDirectory().toString() + "/Download/danc.gif";
//            gifViewPlayer?.setMovieFile(path);
//              gifViewPlayer?.setMovieUrl("https://scdn.androidcommunity.com/wp-content/uploads/2014/10/androidify2.gif");




        pauseBtn?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "isPaused : " + gifViewPlayer!!.isPaused(), Toast.LENGTH_SHORT).show()
            gifViewPlayer!!.setPaused(true)
        })



        playBtn?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "isPlaying : " + gifViewPlayer?.isPlaying(), Toast.LENGTH_SHORT).show()
            gifViewPlayer?.setPaused(false)

        })











    }
}
