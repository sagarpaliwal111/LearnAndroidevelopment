package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.google.android.gms.ads.*
import com.sagarpaliwal.learnAndroiddevelopement.adapter.TutorialItemClicked
import com.sagarpaliwal.learnAndroiddevelopement.adapter.TutorialListAdapter
import com.sagarpaliwal.learnAndroiddevelopement.arraylist.*
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentTutorialListBinding
import com.sagarpaliwal.learnAndroiddevelopement.model.Tutorials

class TutorialListFragment : Fragment(), TutorialItemClicked, BillingProcessor.IBillingHandler {
    lateinit var mAdView: AdView
    private lateinit var binding: FragmentTutorialListBinding
    private val args: TutorialListFragmentArgs by navArgs()
    private var list: MutableList<Tutorials> = mutableListOf()
    var bp: BillingProcessor? = null
    private lateinit var purchaseInfo: PurchaseInfo
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /***************************  Night Mode Off- Orientation fix: Portrait -- Action bar  Text Change -- Binding *****************************************/

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "Android Tutorials"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = FragmentTutorialListBinding.inflate(inflater, container, false)

        /***************************  Shared Preferences: For Hide ads when user have premium *****************************************/

        preferences = requireActivity().getSharedPreferences("subs", Context.MODE_PRIVATE)
        editor = preferences.edit()


        /***************************  If user have Premium  : Ads in Not Shown else Ads is shown *****************************************/

        if (!preferences.getBoolean("isPremium", false)) {
            MobileAds.initialize(
                requireContext()
            ) { }
            mAdView = binding.adView
            val adRequest: AdRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)

            mAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    super.onAdLoaded()
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                    super.onAdFailedToLoad(adError)
//                    mAdView.loadAd(adRequest)

                }


            }


        }
        /******************************Banner ads code finish *********************************/

        /***************************  Passing List with Action to the adapter *****************************************/

        list = when (args.list) {
            1f -> allItem()
            2f -> allAndroidArchitecture()
            3f -> allfirebase()
            4f -> allApi()
            5f -> allAndroidProject()
            else -> mutableListOf()
        }

        /****************************** Setting up Adapter *********************************/

        val adapter = TutorialListAdapter(this, list)
        binding.TutorialLisRecyclerView.adapter = adapter

        /******************************Billing library initialize : For hiding Ads *********************************/

        bp = BillingProcessor(
            context,
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiXPFCXQ/JS4N4Kzkb+R8FIs8Pq/Rm5mueW+6Punn4/PZqy7lSLQDB4HOCB2p6Vr2uHuUax4DQmBAu05fyyPf9dzS0e1kcMIWzPr5Qcs706n+tof6tE7C5kyD751Z+T4HcsyZvfLXOZ2eWpsR3sw4qAw/5Nt6mmkGu1vTPiOf7gpYXPjpAg5qhcsMRHRna3mjWncrR5vu3d786mTp9vImkttAsIVwxOvxGLlhLavfsBgfhx2WPttkK78UUeFsXtugTQuvmslJaJzK28/Z0EP0o/Jopml8NmylZMb5cmRtoB1mfsUyNtWlRHDvBvGhehrg2sgHxZ1uaGZll9wtqAZmfwIDAQAB",
            this
        )
        bp?.initialize()



        return binding.root
    }

    /******************************Click Listener For RecyclerView *********************************/

    override fun onItemClicked(item: Tutorials) {
        val action = TutorialListFragmentDirections.actionTutorialListFragmentToWebView(item.url)
        findNavController().navigate(action)
    }

    /******************************Billing library  :  4- Functions *********************************/

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
