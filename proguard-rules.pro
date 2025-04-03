# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepnames class cz.cvut.docta.core.presentation.navigation.MainScreens$*
-keepnames class cz.cvut.docta.course.presentation.navigation.CourseScreens*
-keepnames class cz.cvut.docta.lessonSession.presentation.navigation.LessonSessionScreens*
-keepnames class cz.cvut.docta.courseEditing.presentation.navigation.CourseManagementScreens*
-keepnames class cz.cvut.docta.achievement.presentation.navigation.AchievementScreens*
-keepnames class cz.cvut.docta.auth.presentation.navigation.AuthScreens*
