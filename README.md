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

Code : 





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


