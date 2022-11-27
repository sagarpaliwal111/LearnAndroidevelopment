package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentUpgradeProBinding


class UpgradeProFragment : Fragment(), BillingProcessor.IBillingHandler {
    var bp: BillingProcessor? = null
    private lateinit var binding: FragmentUpgradeProBinding
    private lateinit var purchaseInfo: PurchaseInfo
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /***************************  Night Mode Off-- Binding *****************************************/

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = FragmentUpgradeProBinding.inflate(inflater, container, false)

        /***************************  Shared Preferences: For button Visibility *****************************************/

        preferences = requireActivity().getSharedPreferences("subs", Context.MODE_PRIVATE)
        editor = preferences.edit()

        /***************************  If user have Premium  : Get Certificate button Shown else purchase subscription *****************************************/

        if (!preferences.getBoolean("isPremium", false)) {
            binding.PurchaseSubscription.visibility = View.VISIBLE
        } else {
            binding.GetCertificateButton.visibility = View.VISIBLE
            binding.PurchaseSubscription.visibility = View.GONE


        }

        /******************************Billing library initialize : For hiding Ads *********************************/

        bp = BillingProcessor(
            context,
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArHByMJSKfPsve/60DuHRys9xzjoK7rquNTske5lKEGoRnYI5i9KQHVnyl88q9OoeK9QBNj4h1zGw7WhfaIZpUhxTlZG59rDe2+Hwva8893dFDXIy8GZZrqL/4lj+hi/2e+BTbC4XYfAjir8dKwFh0NZ8uaD+5MQzxVmPx/YYeDBOkcUvyJbIaMdsTevLcxu+DQsv6sTGptRKRrkpNhi71sfeBT1hGzeKiYe2L4/QVPVwc05U+Gwgx0vD030Db8SxGWmmTppZr3DCshu/29Jmh2gb+OkgT6BKDqRO5OgVo3rkZ0VK6peVYeenvMpPo1xRlFH7+EXdZCejMq/j8uJPHQIDAQAB",
            this
        )
        bp?.initialize()

        /****************************** Button Click Listener *********************************/

        binding.PurchaseSubscription.setOnClickListener {
            bp?.purchase(requireActivity(), "test")
        }


        binding.GetCertificateButton.setOnClickListener {
            findNavController().navigate(R.id.getCertificate)
        }

        binding.backButtonPro.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root

    }

    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    /****************************** Billing library  :  4- Functions *********************************/

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
        editor.putBoolean("isPremium", true)
        editor.apply()
    }

    override fun onPurchaseHistoryRestored() {

    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {

    }

    override fun onBillingInitialized() {
        bp?.loadOwnedPurchasesFromGoogleAsync(object : BillingProcessor.IPurchasesResponseListener {
            override fun onPurchasesSuccess() {


            }

            override fun onPurchasesError() {

            }

        })
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


}



