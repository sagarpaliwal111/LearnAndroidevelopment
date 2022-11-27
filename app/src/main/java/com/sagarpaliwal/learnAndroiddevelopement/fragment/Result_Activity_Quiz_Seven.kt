package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentResultActivityQuizSevenBinding


class Result_Activity_Quiz_Seven : Fragment(), BillingProcessor.IBillingHandler {

    private val args: Result_Activity_Quiz_SevenArgs by navArgs()
    private lateinit var binding: FragmentResultActivityQuizSevenBinding
    private var totalScore = 0
    private var wrong = 0
    private var skip = 0

    private var bp: BillingProcessor? = null
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var purchaseInfo: PurchaseInfo
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /***************************  Night Mode Off- Orientation fix: Portrait -- Action bar  Text Change -- Binding *****************************************/

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "Android Development"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //  Inflate the layout for this fragment
        binding = FragmentResultActivityQuizSevenBinding.inflate(inflater, container, false)
        totalScore = args.data.toInt()
        wrong = args.datafirst.toInt()
        skip = args.datasecond.toInt()
        initializeViews()

        requireActivity().onBackPressedDispatcher.addCallback(this) {}

        /***************************  Shared Preferences: For Hide ads when user have premium *****************************************/

        preferences = requireActivity().getSharedPreferences("subs", Context.MODE_PRIVATE)
        editor = preferences.edit()

        /******************************Billing library initialize : For hiding Ads *********************************/

        bp = BillingProcessor(
            context,
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiXPFCXQ/JS4N4Kzkb+R8FIs8Pq/Rm5mueW+6Punn4/PZqy7lSLQDB4HOCB2p6Vr2uHuUax4DQmBAu05fyyPf9dzS0e1kcMIWzPr5Qcs706n+tof6tE7C5kyD751Z+T4HcsyZvfLXOZ2eWpsR3sw4qAw/5Nt6mmkGu1vTPiOf7gpYXPjpAg5qhcsMRHRna3mjWncrR5vu3d786mTp9vImkttAsIVwxOvxGLlhLavfsBgfhx2WPttkK78UUeFsXtugTQuvmslJaJzK28/Z0EP0o/Jopml8NmylZMb5cmRtoB1mfsUyNtWlRHDvBvGhehrg2sgHxZ1uaGZll9wtqAZmfwIDAQAB",
            this
        )
        bp?.initialize()

        /*************************** Interstitial ads code*******************************/

        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireContext(),
            "ca-app-pub-4280527336340292/2351782933",
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
//                    Log.i("ss", "onAdFailedToLoad: $adError")
//                    InterstitialAd.load(
//                        requireContext(),
//                        "ca-app-pub-4280527336340292/7292584973",
//                        adRequest,
//                        object : InterstitialAdLoadCallback() {},
//                    )
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }


            })


        /*************************** Interstitial ads code finish *******************************/

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initializeViews() {
        binding.tvScore.text = "Score: $totalScore  \uD83E\uDD48"
        binding.tvright.text = "Correct: $totalScore  ✅"
        binding.tvwrong.text = "Wrong: $wrong  ❌"
        binding.tvSkip.text = "Skip: $skip  ⏭"
        if (totalScore >= 6) {
            binding.emojiReactionImg.setImageResource(R.drawable.smile)
            Toast.makeText(context, "Wow Great", Toast.LENGTH_SHORT).show()
        } else {
            binding.emojiReactionImg.setImageResource(R.drawable.sad)
            Toast.makeText(context, "Need Improvement", Toast.LENGTH_SHORT).show()
        }

        binding.tvPlayAgain.setOnClickListener {


            if (!preferences.getBoolean("isPremium", false)) {


                if (mInterstitialAd != null)
                    mInterstitialAd!!.show(requireActivity())
                findNavController().navigate(R.id.java_Quiz_Seven)

            } else {


                findNavController().navigate(R.id.java_Quiz_Seven)


            }
        }
        binding.rateApp.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://play.google.com/store/apps/details?id=com.sagarpaliwal.learnAndroiddevelopement")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)

            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )

            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.sagarpaliwal.learnAndroiddevelopement")
                    )
                )
            }


        }

        binding.dashboardMove.setOnClickListener {

            /********************************* If User have premium Purchase then Ads is not shown else Interstitial ads Shown : On dashboard button Click *************************************/

            if (!preferences.getBoolean("isPremium", false)) {


                if (mInterstitialAd != null)
                    mInterstitialAd!!.show(requireActivity())
                findNavController().navigate(R.id.homeFragment)

            } else {


                findNavController().navigate(R.id.homeFragment)


            }
        }



    }
    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {


    }

    override fun onPurchaseHistoryRestored() {


    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {

    }

    override fun onBillingInitialized() {

        /******************************By Using Shared Preference Setting the value true  for : Premium User else False : Normal User *********************************/

        if (bp?.getSubscriptionPurchaseInfo("test") != null) {


            purchaseInfo = bp?.getSubscriptionPurchaseInfo("test")!!


            if (purchaseInfo.purchaseData != null) {
                editor.putBoolean("isPremium", true)
                editor.apply()

            } else {
                editor.putBoolean("isPremium", false)
                editor.apply()

            }
        }

    }

    /****************************** Billing library instant release(For better performance) *********************************/

    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }
}