@file:Suppress("DEPRECATION")

package tr.com.harunkor.gifviewplayer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.os.Build
import android.os.StrictMode
import android.util.AttributeSet
import android.view.View
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.zip.Deflater


class GifMovieView :View {

    private val DEFAULT_MOVIEW_DURATION = 1000

    private var mMovieResourceId: Int = 0
    private var mMovie: Movie? = null
    private var mMovieStart: Long = 0
    private var mCurrentAnimationTime = 0
    private var mWidth: Int = 0;
    private var mHeight:Int = 0;
    private var mPaused = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    fun setMovieResource(movieResId: Int) {
        this.mMovieResourceId = movieResId
        mMovie = Movie.decodeStream(resources.openRawResource(mMovieResourceId))
        mWidth = mMovie?.width()!!
        mHeight = mMovie?.height()!!
        requestLayout()
        invalidateView()
    }

    fun setMovieStream(inputstream: InputStream) {

        mMovie = Movie.decodeStream(inputstream)
        mWidth = mMovie!!.width()
        mHeight = mMovie!!.height()
        requestLayout()
        invalidateView()


    }

    fun setMovieAssets(assetfilename: String) {


        var inp: InputStream? = null
        try {
            inp = context.assets.open(assetfilename)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            setMovieStream(inp!!)
        }

    }


    fun setMovieFile(filename: String) {

        mMovie = Movie.decodeFile(filename)
        mWidth = mMovie?.width()!!
        mHeight = mMovie?.height()!!
        requestLayout()
        invalidateView()


    }


    fun setMovieUrl(sURL: String) {

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val outputStream = ByteArrayOutputStream()
        var url: URL? = null
        try {
            url = URL(sURL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } finally {


            try {
                val chunk = ByteArray(1024)
                val stream = url!!.openStream()


                while ( true) {
                    val bytess = stream.read(chunk)
                    if (bytess <= 0)
                        break
                    outputStream.write(chunk, 0, bytess)
                }
                outputStream.flush()


            } catch (e: IOException) {
                e.printStackTrace()

            } finally {


                mMovie = Movie.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().size)
                mWidth = mMovie?.width()!!
                mHeight = mMovie?.height()!!
                requestLayout()

            }


        }





    }


    fun setMovie(movie: Movie) {
        this.mMovie = movie
        requestLayout()
    }


    fun getMovie(): Movie {
        return mMovie!!
    }


    fun setPaused(paused: Boolean) {
        this.mPaused = paused

        if (!paused) {
            mMovieStart = android.os.SystemClock.uptimeMillis() - mCurrentAnimationTime
        }

        invalidateView()
    }


    fun isPaused(): Boolean {
        return this.mPaused
    }

    fun isPlaying(): Boolean {
        return !this.mPaused
    }


    override fun onDraw(canvas: Canvas?) {
        if (mMovie != null) {
            if (!mPaused) {
                updateAnimationTime()
                drawMovieFrame(canvas!!)
                invalidateView()
            } else {
                drawMovieFrame(canvas!!)
            }
        }
    }


    private fun invalidateView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            postInvalidateOnAnimation()
        } else {
            invalidate()
        }

    }


    private fun updateAnimationTime() {
        val now = android.os.SystemClock.uptimeMillis()

        if (mMovieStart == 0L) {
            mMovieStart = now
        }

        var dur = mMovie!!.duration()

        if (dur == 0) {
            dur = DEFAULT_MOVIEW_DURATION
        }

        mCurrentAnimationTime = ((now - mMovieStart) % dur).toInt()
    }




    private fun drawMovieFrame(canvas: Canvas) {

        mMovie!!.setTime(mCurrentAnimationTime)
        canvas.scale(
            this.width.toFloat() / mMovie!!.width().toFloat(),
            this.height.toFloat() / mMovie!!.height().toFloat()
        )
        mMovie!!.draw(canvas, 0f, 0f)


    }



}