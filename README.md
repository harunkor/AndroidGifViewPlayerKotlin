# AndroidGifViewPlayerKotlin
Library for playing gifs on Android Kotlin.
Simple android view to display gifs efficiently. You can start, pause and stop gifView. Example usages can be found in example project.


# Screen

![](https://github.com/harunkor/AndroidGifViewPlayerJava/blob/master/ss.gif?raw=true)

# Usage

Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.harunkor:AndroidGifViewPlayerKotlin:1.0.0'
	}
```

Layout : 

```
 <tr.com.harunkor.gifviewplayer.GifMovieView
        android:id="@+id/gifViewPlayer"
        android:layout_width="400dp"
        android:layout_height="400dp"/>
```

Code : 

```
      	//gif player layout variable.
           	var gifViewPlayer: GifMovieView?  = findViewById(R.id.gifViewPlayer) as GifMovieView 
	//gif animation file set  in drawable folder. 
              	gifViewPlayer!!.setMovieResource(R.drawable.carkifelek)
	//gif animation Movie callback and set 
            	gifViewPlayer?.setMovie(gifViewPlayer?.getMovie()!!);
	// gif animation hide. 
            	gifViewPlayer?.setVisibility(View.INVISIBLE);    
      	// gif animation  visible  
            	gifViewPlayer?.setVisibility(View.VISIBLE);
	// gif animation file set  in assets folder. 
            	gifViewPlayer?.setMovieAssets("eat.gif");
       		var path:String = Environment.getExternalStorageDirectory().toString() + "/Download/danc.gif";   
 	// gif animation file set in Android device. 
            	gifViewPlayer?.setMovieFile(path);
	// gif animation (URL stream) show link.  
              	gifViewPlayer?.setMovieUrl("https://scdn.androidcommunity.com/wp-content/uploads/2014/10/androidify2.gif");
   	// gif animation pause
     	    	gifViewPlayer?.setPaused(true);  
     	// gif animation play 
            	gifViewPlayer?.setPaused(false);
	          
```
 
# Manifest 
Permission : (dependent on use)
```
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
  <uses-permission android:name="android.permission.INTERNET"></uses-permission>
  
  
  
  
```
Necessary :

```
  <application
        android:hardwareAccelerated="false"
	.....
	....
	........
	...
    </application>
```

# WARNING! : compile sdk version should be 28.


