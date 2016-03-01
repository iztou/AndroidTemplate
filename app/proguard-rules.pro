# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/guomenglong/programfiles/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保存第三方jar
-dontwarn javax.annotation.**
-dontwarn javax.lang.model.**
-dontwarn javax.lang.model.type.**
-dontwarn javax.lang.model.element.**
-dontwarn javax.lang.model.util.**
-dontwarn retrofit2.**
-dontwarn okio.**
-dontwarn org.junit.**
-dontwarn rx.**
-dontwarn com.squareup.okhttp.**
-dontwarn android.test.**
-dontwarn butterknife.internal.**
-keepattributes Signature
-keepattributes Exceptions

-keep class android.support.** {*;}
-keep interface android.support.v4.** {*;}
-keep interface android.support.v7.widget.** {*;}
-keep class bolts.** {*;}
-keep class butterknife.** {*;}
-keep class **$$ViewBinder { *; }
-keep class org.apache.http.** {*;}
-keep class retrofit2.** {*;}
-keep class retrofit2.** { *; }
-keep class com.facebook.** {*;}
-keep class de.greenrobot.dao.** {*;}
-keep class com.google.gson.** {*;}
-keep class org.eclipse.mat.** {*;}
-keep class org.hamcrest.** {*;}
-keep class com.fasterxml.jackson.core.** {*;}
-keep class javax.annotation.** {*;}
-keep class junit.** {*;}
-keep class org.junit.** {*;}
-keep class com.squareup.** {*;}
-keep class com.norbsoft.typefacehelper.** {*;}
-keep class com.nineoldandroids.** {*;}
-keep class okhttp3.** {*;}
-keep class okio.** {*;}
-keep class com.astuetz.** {*;}
-keep class rx.** {*;}
-keep class int.srain.cube.views.ptr.** {*;}

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
