package com.sagarpaliwal.learnAndroiddevelopement.model

object ExpandableListDataPump {
    val data: HashMap<String, List<String>>
        get() {
            val hashMap: HashMap<String, List<String>> = HashMap()
            val arrayList = ArrayList<String>()
            arrayList.add("Android is an open-source, Linux-based operating system used in mobiles, tablets, televisions, etc.")
            hashMap["📝 What is Android?"] = arrayList
            val arrayList2 = ArrayList<String>()
            arrayList2.add("Andy Rubin.")
            hashMap["📝  Who is the founder of Android?"] = arrayList2
            val arrayList3 = ArrayList<String>()
            arrayList3.add(
                "Following is a list of components of Android application architecture:\n" +
                        "\n" +
                        "Services: Used to perform background functionalities.\n" +
                        "Intent: Used to perform the interconnection between activities and the data passing mechanism.\n" +
                        "Resource Externalization: strings and graphics.\n" +
                        "Notification: light, sound, icon, notification, dialog box and toast.\n" +
                        "Content Providers: It will share the data between applications."
            )
            hashMap["📝 Explain the Android application Architecture."] = arrayList3
            val arrayList4 = ArrayList<String>()
            arrayList4.add(
                "Aestro\n" +
                        "Blender\n" +
                        "Cupcake\n" +
                        "Donut\n" +
                        "Eclair\n" +
                        "Froyo\n" +
                        "Gingerbread\n" +
                        "Honeycomb\n" +
                        "Ice Cream Sandwich\n" +
                        "Jelly Bean\n" +
                        "KitKat\n" +
                        "Lollipop\n" +
                        "Marshmallow"
            )
            hashMap["📝 What are the code names of android?"] = arrayList4
            val arrayList5 = ArrayList<String>()
            arrayList5.add(
                "Open-source: It means no license, distribution and development fee.\n" +
                        "\n" +
                        "Platform-independent: It supports Windows, Mac, and Linux platforms.\n" +
                        "\n" +
                        "Supports various technologies: It supports camera, Bluetooth, wifi, speech, EDGE etc. technologies.\n" +
                        "\n" +
                        "Highly optimized Virtual Machine: Android uses a highly optimized virtual machine for mobile devices, called DVM (Dalvik Virtual Machine).\n" +
                        "\n"
            )
            hashMap["📝 What are the advantages of Android?"] = arrayList5
            val arrayList6 = ArrayList<String>()
            arrayList6.add(
                "Yes, an android app can be developed in C/C++ also using android NDK (Native Development Kit). It makes the performance faster. It should be used with Android SDK.\n" +
                        "\n"
            )
            hashMap["📝 Does android support other languages than java?"] = arrayList6
            val arrayList7 = ArrayList<String>()
            arrayList7.add(
                "The core building blocks of Android are:\n" +
                        "\n" +
                        "Activity\n" +
                        "View\n" +
                        "Intent\n" +
                        "Service\n" +
                        "Content Provider\n" +
                        "Fragment etc."
            )
            hashMap["📝 What are the core building blocks of android?"] = arrayList7
            val arrayList8 = ArrayList<String>()
            arrayList8.add(
                "Activity is like a frame or window in java that represents GUI. It represents one screen of android.\n" +
                        "\n"
            )
            hashMap["📝 What is activity in Android?"] = arrayList8
            val arrayList9 = ArrayList<String>()
            arrayList9.add(
                "There are 7 life-cycle methods of activity. They are as follows:\n" +
                        "\n" +
                        "onCreate()\n" +
                        "onStart()\n" +
                        "onResume()\n" +
                        "onPause()\n" +
                        "onStop()\n" +
                        "onRestart()\n" +
                        "onDestroy()"
            )
            val arrayList10 = ArrayList<String>()
            arrayList10.add(
                "It is a kind of message or information that is passed to the components. It is used to launch an activity, display a web page, send SMS, send email, etc. There are two types of intents in android:\n" +
                        "\n" +
                        "Implicit Intent\n" +
                        "Explicit Intent\n"
            )
            val arrayList11 = ArrayList<String>()
            arrayList11.add(
                " View elements can be identified using the keyword findViewById.\n" +
                        "\n"
            )
            val arrayList12 = ArrayList<String>()
            arrayList12.add(
                "An android toast provides feedback to the users about the operation being performed by them. It displays the message regarding the status of operation initiated by the user.\n" +
                        "\n"
            )
            val arrayList13 = ArrayList<String>()
            arrayList13.add(
                "The following folders are declared as impotent in android:\n" +
                        "\n" +
                        "AndroidManifest.xml\n" +
                        "build.xml\n" +
                        "bin/\n" +
                        "src/\n" +
                        "res/\n" +
                        "assets/"
            )
            val arrayList14 = ArrayList<String>()
            arrayList14.add(
                "We use bundles to pass the required data to various subfolders.\n" +
                        "\n"
            )
            hashMap["📝 What are the life cycle methods of android activity?"] = arrayList9
            hashMap["📝 Explain the use of 'bundle' in android?"] = arrayList14
            val arrayList15 = ArrayList<String>()
            arrayList15.add(
                " The files which can be injected for the building up of a process are called as application resource file.\n" +
                        "\n"
            )
            hashMap["📝 What is intent?"] = arrayList10
            hashMap["📝 What is an application resource file?"] = arrayList15
            val arrayList16 = ArrayList<String>()
            arrayList16.add(
                "A unique Linux ID is assigned to each application in android. It is used for the tracking of a process.\n" +
                        "\n"
            )
            hashMap["📝 How are view elements identified in the android program?"] = arrayList11
            hashMap["📝 Define Android toast."] = arrayList12
            val arrayList17 = ArrayList<String>()
            arrayList17.add("No")
            val arrayList18 = ArrayList<String>()
            arrayList18.add(
                "Shared Preferences\n" +
                        "Internal Storage\n" +
                        "External Storage\n" +
                        "SQLite Databases\n" +
                        "Network Connection"
            )
            val arrayList19 = ArrayList<String>()
            arrayList19.add(
                "Layouts in Android are placed as XML files.\n" +
                        "\n"
            )
            val arrayList20 = ArrayList<String>()
            arrayList20.add(
                "Layouts in Android are placed in the layout folder.\n" +
                        "\n"
            )
            hashMap["📝 Can the bytecode be written in java be run on android?"] = arrayList17
            hashMap["📝 List the various storages that are provided by Android."] = arrayList18
            val arrayList21 = ArrayList<String>()
            arrayList21.add(
                "The Implicit intent is used to invoke the system components.\n" +
                        "\n"
            )
            hashMap["📝 Where are layouts placed in Android?"] = arrayList20
            hashMap["📝 What is the implicit intent in android?"] = arrayList21
            val arrayList22 = ArrayList<String>()
            hashMap["📝 What is the use of LINUX ID in android?"] = arrayList16
            arrayList22.add(
                "An explicit intent is used to invoke the activity class.\n" +
                        "\n"
            )
            hashMap["📝 How are layouts placed in Android?"] = arrayList19
            hashMap["📝 What is explicit intent in android?"] = arrayList22
            val arrayList23 = ArrayList<String>()
            hashMap["📝 Give a list of impotent folders in android"] = arrayList13
            arrayList23.add(
                "Intent i = new Intent(getApplicationContext(), ActivityTwo.class);  \n" +
                        "startActivity(i); "
            )
            hashMap["📝 How to call another activity in android?"] = arrayList23
            val arrayList24 = ArrayList<String>()
            arrayList24.add(
                "A service is a component that runs in the background. It is used to play music, handle network transaction, etc.\n" +
                        "\n"
            )
            hashMap["📝 What is service in android?"] = arrayList24
            val arrayList25 = ArrayList<String>()
            arrayList25.add(
                "SQLite: An opensource and lightweight relational database for mobile devices.\n" +
                        "\n"
            )
            hashMap["📝 What is the name of the database used in android?"] = arrayList25
            val arrayList26 = ArrayList<String>()
            arrayList26.add(
                "AAPT is an acronym for android asset packaging tool. It handles the packaging process.\n" +
                        "\n "
            )
            hashMap["📝 What is AAPT?"] = arrayList26
            val arrayList27 = ArrayList<String>()
            arrayList27.add(
                "A content provider is used to share information between Android applications.\n" +
                        "\n"
            )
            hashMap["📝 What is a content provider?"] = arrayList27
            val arrayList28 = ArrayList<String>()
            arrayList28.add(
                "The fragment is a part of Activity by which we can display multiple screens on one activity.\n" +
                        "\n"
            )
            hashMap["📝 What is fragment?"] = arrayList28
            val arrayList29 = ArrayList<String>()
            arrayList29.add(
                "ADB stands for Android Debug Bridge. It is a command line tool that is used to communicate with the emulator instance.\n" +
                        "\n"
            )
            hashMap["📝 What is ADB?\n"] = arrayList29
            val arrayList30 = ArrayList<String>()
            arrayList30.add(
                "NDK stands for Native Development Kit. By using NDK, you can develop a part of an app using native language such as C/C++ to boost the performance.\n" +
                        "\n"
            )
            hashMap["📝 What is NDK?"] = arrayList30
            val arrayList31 = ArrayList<String>()
            arrayList31.add(
                "ANR stands for Application Not Responding. It is a dialog box that appears if the application is no longer responding.\n" +
                        "\n"
            )
            hashMap["📝 What is ANR?"] = arrayList31
            val arrayList32 = ArrayList<String>()
            arrayList32.add(
                " The Google Android SDK is a toolset which is used by developers to write apps on Android-enabled devices. It contains a graphical interface that emulates an Android-driven handheld environment and allows them to test and debug their codes.\n" +
                        "\n"
            )
            hashMap["📝 What is the Google Android SDK?\n"] = arrayList32
            val arrayList33 = ArrayList<String>()
            arrayList33.add(
                " APK is a short form stands for Android Packaging Key. It is a compressed key with classes, UI's, supportive assets and manifest. All files are compressed to a single file is called APK.\n" +
                        "\n"
            )
            hashMap["📝 What is an APK format?"] = arrayList33
            val arrayList34 = ArrayList<String>()
            arrayList34.add(
                " Android applications are written by using the java (Android SDK) and C/C++ (Android NDK).\n" +
                        "\n"
            )
            hashMap["📝 Which language does Android support to develop an application?\n"] =
                arrayList34
            val arrayList35 = ArrayList<String>()
            arrayList35.add(
                " ADT stands for Android Development Tool. It is used to develop the applications and test the applications.\n" +
                        "\n"
            )
            hashMap["📝 What is ADT in Android?"] = arrayList35
            val arrayList36 = ArrayList<String>()
            arrayList36.add(
                "View Group is a collection of views and other child views. It is an invisible part and the base class for layouts.\n" +
                        "\n"
            )
            hashMap["📝 What is View Group in Android?"] = arrayList36
            val arrayList37 = ArrayList<String>()
            arrayList37.add(
                "An adapter is used to create a child view to present the parent view items.\n" +
                        "\n"
            )
            hashMap["📝 What is the Adapter in Android?"] = arrayList37
            val arrayList38 = ArrayList<String>()
            arrayList38.add(
                "We can change bitmap images into nine sections with four corners, four edges, and an axis.\n" +
                        "\n"
            )
            hashMap["📝 What is nine-patch images tool in Android?\n"] = arrayList38
            val arrayList39 = ArrayList<String>()
            arrayList39.add(
                "Android is a customized Linux 3.6 kernel.\n" +
                        "\n"
            )
            hashMap["📝 Which kernel is used in Android?"] = arrayList39
            val arrayList40 = ArrayList<String>()
            arrayList40.add(
                "Application widgets are miniature application views that can be embedded in other applications and receive periodic updates.\n" +
                        "\n"
            )
            hashMap["📝 What is application Widgets in Android?\n"] = arrayList40
            val arrayList41 = ArrayList<String>()
            arrayList41.add(
                "Following are two types of flags to run an application in Android:\n" +
                        "\n" +
                        "FLAG_ACTIVITY_NEW_TASK\n" +
                        "FLAG_ACTIVITY_CLEAR_TOP\n"
            )
            hashMap["📝 Which types of flags are used to run an application on Android?"] =
                arrayList41
            val arrayList42 = ArrayList<String>()
            arrayList42.add(
                "A singleton class is a class which can create only an object that can be shared by all other classes.\n" +
                        "\n"
            )
            hashMap["📝 What is a singleton class in Android?"] = arrayList42
            val arrayList43 = ArrayList<String>()
            arrayList43.add(
                "In sleep mode, CPU is slept and doesn't accept any commands from android device except Radio interface layer and alarm.\n" +
                        "\n"
            )
            hashMap["📝 What is sleep mode in Android?"] = arrayList43
            val arrayList44 = ArrayList<String>()
            arrayList44.add(
                "In Android, a drawable folder is compiled a visual resource that can use as a background, banners, icons, splash screen, etc.\n" +
                        "\n"
            )
            hashMap["📝 What do you mean by a drawable folder in Android?"] = arrayList44
            val arrayList45 = ArrayList<String>()
            arrayList45.add(
                "DDMS stands for Dalvik Debug Monitor Server. It gives the wide array of debugging features:\n" +
                        "\n" +
                        "Port forwarding services\n" +
                        "Screen capture\n" +
                        "Thread and heap information\n" +
                        "Network traffic tracking\n" +
                        "Location data spoofing"
            )
            hashMap["📝 What is DDMS?"] = arrayList45
            val arrayList46 = ArrayList<String>()
            arrayList46.add(
                "The Android architecture consists of 4 components:\n" +
                        "\n" +
                        "Linux Kernal\n" +
                        "Libraries\n" +
                        "Android Framework\n" +
                        "Android Applications."
            )
            hashMap["📝 Define Android Architecture?"] = arrayList46
            val arrayList47 = ArrayList<String>()
            arrayList47.add(
                "The portable wi-fi hotspot is used to share internet connection to other wireless devices.\n" +
                        "\n"
            )
            hashMap["📝 What is a portable wi-fi hotspot?"] = arrayList47
            val arrayList48 = ArrayList<String>()
            arrayList48.add(
                "Alert Dialog\n" +
                        "Progress Dialog\n" +
                        "Date Picker Dialog\n" +
                        "Time picker Dialog"
            )
            hashMap["📝 Name the dialog box which is supported by Android?"] = arrayList48
            val arrayList49 = ArrayList<String>()
            arrayList49.add(
                "Inflate Exception\n" +
                        "Surface.OutOfResourceException\n" +
                        "SurfaceHolder.BadSurfaceTypeException\n" +
                        "WindowManager.BadTokenException"
            )
            hashMap["📝 Name some exceptions in Android?"] =
                arrayList49
            val arrayList50 = ArrayList<String>()
            arrayList50.add(
                "JDK\n" +
                        "Eclipse+ADT plugin\n" +
                        "SDK Tools"
            )
            hashMap["📝 What are the basic tools used to develop an Android app?"] = arrayList50
            return hashMap
        }
}

