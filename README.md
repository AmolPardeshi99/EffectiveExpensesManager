

 ![GitHub Cards Preview](https://miro.medium.com/max/1400/1*oO1P4K9wVT_-yDDP2EBmPw.jpeg)


# Effective Expense Manager

Effective Expense Manager is application that helps to keep an accurate record of your money inflow and outflow.This is one of the best ways to get your expenses under control and bring some semblance of order to your finances.

<!-- 
# Links 

* Blog-Post :-  -->

# 🔗Open-Source Library

* [Glide](https://github.com/bumptech/glide)
* [Retrofit](https://square.github.io/retrofit/)
* [Jetpack](https://developer.android.com/jetpack)

# Things we used while making this application

* Hilt
* Kotlin
* Live data
* Coroutine
* Fragments
* Navigation
* Android Studio
* Recycler View 
* Room Database
* Retrofit Library
* MVVM Architechture

# Tech Stack ✨

* [Android Studio](https://developer.android.com/studio)
* [Kotlin](https://kotlinlang.org/)
* [Jetpack-  Navigation ](https://developer.android.com/guide/navigation?gclsrc=ds&gclsrc=ds&gclid=CM2tyaqst_MCFUM1jgod_LwE2g)

# Clone this Repo To Your System Using Android Studio✨

* Step 1: Open your Android Studio then go to the File > New > Project from Version Control as shown in the below image.
* Step 2: After clicking on the Project from Version Control a pop-up screen will arise like below. In the Version control choose Git from the drop-down menu.
* Step 3: Then at last paste the link in the URL and choose your Directory. Click on the Clone button and you are done.

# Clone this Repo To Your System Using GitBash✨

* Open Git Bash

* If Git is not already installed, it is super simple. Just go to the Git Download Folder and follow the instructions.

* Go to the current directory where you want the cloned directory to be added.

* To do this, input cd and add your folder location. You can add the folder location by dragging the folder to Git bash.

* Go to the page of the repository that you want to clone

* Click on “Clone or download” and copy the URL.

* Use the git clone command along with the copied URL from earlier. $ git clone https://github.com/AmolPardeshi99/EffectiveExpensesManager.git

* Press Enter. $ git clone https://github.com/AmolPardeshi99/EffectiveExpensesManager.git 
Cloning into Git … remote: Counting objects: 13, done. remote: Compressing objects: 100% (13/13), done. remove: Total 13 (delta 1), reused 0 (delta 1) Unpacking objects: 100% (13/13), done.

Congratulations, you have created your first local clone from your remote Github repository.

Open Android Studio. Go to File > New > Project From Version Control. Copy the link of this repositary. Paste the link in Url Box of Android Studio window and click on "Clone" button.

# Dependencies 

    // general
    def anko_version = '0.10.0'
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'


    //Anko
    implementation "org.jetbrains.anko:anko-commons:$anko_version"

    //Room
    implementation 'androidx.room:room-ktx:2.1.0'
    kapt 'androidx.room:room-compiler:2.1.0'

    // MVvP
    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    //Retrofit
    def retrofit2_version = "2.9.0"
    def okhttp3_version = "4.9.0"
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"

    //Okhttp3
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"

    // Navigation Graph
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    def nav_version = "2.3.5"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
<!-- 
# Lessons Learnt📚 -->
