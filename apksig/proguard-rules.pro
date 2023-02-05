# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\tools\adt-bundle-windows-x86_64-20131030\sdk/tools/proguard/proguard-android.txt
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
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-allowaccessmodification

-ignorewarnings
-dontwarn
-dontnote

-keepattributes *Annotation*
-keepattributes Signature
-keepattributes SourceFile, LineNumberTable

-renamesourcefileattribute SourceFile
-repackageclasses

-keepclassmembers class com.android.apksig.internal.** {
    static <fields>;
}

-keepclassmembers class * {
    @com.android.apksig.internal.asn1.Asn1Class *;
    @com.android.apksig.internal.asn1.Asn1Field *;
}

-dontwarn android.arch.**
-dontwarn android.lifecycle.**
-keep class android.arch.** { *; }
-keep class android.lifecycle.** { *; }

-dontwarn androidx.arch.**
-dontwarn androidx.lifecycle.**
-keep class androidx.arch.** { *; }
-keep class androidx.lifecycle.** { *; }
