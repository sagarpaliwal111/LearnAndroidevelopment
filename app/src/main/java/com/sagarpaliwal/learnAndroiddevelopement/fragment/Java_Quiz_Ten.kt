package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentJavaQuizTenBinding
import kotlinx.android.synthetic.main.fragment_java__quiz__first.*
import java.util.*
import java.util.concurrent.TimeUnit


class Java_Quiz_Ten : Fragment(), BillingProcessor.IBillingHandler  {
    lateinit var binding: FragmentJavaQuizTenBinding


    private var bp: BillingProcessor? = null
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var purchaseInfo: PurchaseInfo
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    //    timer
    private lateinit var countDownTimer: CountDownTimer
    private val countDownInMilliSecond: Long = 30000
    private val countDownInterval: Long = 1000
    private var timeLeftMilliSeconds: Long = 0
    private var defaultColor: ColorStateList? = null
    private var score = 0
    private var correct = 0
    private var wrong = 0
    private var skip = 0
    private var qIndex = 0
    private var updateQueNo = 1

    // create string for question, answer and options
    private var questions = arrayOf(
        "1. How to store heavy structured data in android?",
        "2. In order to use YouTubePlayerView directly, your activity needs to extend_______.",
        "3. What Android resource directory do we use store our .xlm layout file for smartphones?",
        "4. What resource directory qualifier do we use for creating layout-large?",
        "5. What class should your view extend so you can draw on it?",
        "6. An .xml file in layout-large can have the same name as an .xlm file in layout?",
        "7. What listener should your view implement for touch?",
        "8. What is fragment life cycle in android?",
        "9. What interface is used to listen for changes in the view's state?",
        "10. What Layout should you use in your XML to hold your game view?"
    )
    private var answer = arrayOf(
        "SQLite database",
        "YouTubeBaseActivity",
        "Layout",
        "Size",
        "SurfaceView",
        "True",
        "View.OnTouchListener",
        "OnAttach()->onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()",
        "SurfaceHolder.Callback",
        "FrameLayout"
    )
    private var options = arrayOf(
        "Shared Preferences",
        "Cursor",
        "SQLite database",
        "Not possible",
        "YoutubeApiServiceUtil",
        "YoutubeIntents",
        "YoutubePlayerActivity",
        "YoutubeBaseActivity",
        "Layout",
        "Layout-large",
        "layout-phone",
        "The name does not matter it can be called anything",
        "Orientation",
        "Size",
        "UI Mode",
        "Ratio",
        "CanvasView",
        "SurfaceView",
        "DrawingView",
        "PaintView",
        "True",
        "False",
        "Both",
        "None of these",
        "View.OnClickListener",
        "View.TouchListener",
        "View.OnTouchListener",
        "View.OnSwipeListener",
        "OnReceive()",
        "OnCreate()",
        "OnAttach()->onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()",
        "None of the above",
        "SurfaceHolder.Callback",
        "SurfaceListener.StateChange",
        "View.OnChangeListener",
        "SurfaceHolder.stateChange",
        "FrameLayout",
        "LinearLayout",
        "Relativelayout",
        "Tablelayout"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        /***************************  Night Mode Off- Orientation fix: Portrait -- Action bar  Text Change -- Binding *****************************************/

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "Android Development"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Inflate the layout for this fragment
        binding = FragmentJavaQuizTenBinding.inflate(inflater, container, false)
        initViews()

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


        /********************************* If User have premium Purchase then Ads is not shown else Interstitial ads Shown : On Back Click *************************************/

        if (!preferences.getBoolean("isPremium", false)) {
            requireActivity().onBackPressedDispatcher.addCallback(this) {

                if (mInterstitialAd != null)
                    mInterstitialAd!!.show(requireActivity())
                findNavController().navigate(R.id.javaMainQuiz)
            }
        } else {

            requireActivity().onBackPressedDispatcher.addCallback(this) {
                findNavController().navigate(R.id.javaMainQuiz)
            }

        }




        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        binding.tvQuestion.text = questions[qIndex]
        binding.radioButton1.text = options[0]
        binding.radioButton2.text = options[1]
        binding.radioButton3.text = options[2]
        binding.radioButton4.text = options[3]
        // check options selected or not
        // if selected then selected option correct or wrong
        binding.nextQuestionBtn.setOnClickListener {
            if (radiogrp.checkedRadioButtonId == -1) {
                Toast.makeText(
                    context,
                    "Please select an options",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                showNextQuestion()
            }
        }
        binding.tvNoOfQues.text = "$updateQueNo/10"
        binding.tvQuestion.text = questions[qIndex]
        defaultColor = binding.quizTimer.textColors
        timeLeftMilliSeconds = countDownInMilliSecond
        statCountDownTimer()
    }

    private fun statCountDownTimer() {
        try {
            countDownTimer = object : CountDownTimer(timeLeftMilliSeconds, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {

                    timeLeftMilliSeconds = millisUntilFinished
                    val second = TimeUnit.MILLISECONDS.toSeconds(timeLeftMilliSeconds).toInt()
                    // %02d format the integer with 2 digit
                    val timer = String.format(Locale.getDefault(), "Remaining Time: %02d", second)
                    binding.quizTimer.text = timer
                    if (timeLeftMilliSeconds < 10000) {
                        binding.quizTimer.setTextColor(Color.RED)
                    } else {
                        binding.quizTimer.setTextColor(defaultColor)
                    }

                }

                override fun onFinish() {
                    showNextQuestion()
                }
            }.start()
        } catch (e: Exception) {
            Log.i("mmm", "showNextQuestion: ${e.message}")


        }
    }

    @SuppressLint("SetTextI18n")
    private fun correctAlertDialog() {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_layout_correct_ans, null)
        builder.setView(view)
        val tvScore = view.findViewById<TextView>(R.id.tvDialog_score)
        val correctOkBtn = view.findViewById<Button>(R.id.correct_ok)
        tvScore.text = "Score : $correct"
        val alertDialog = builder.create()
        correctOkBtn.setOnClickListener {
            timeLeftMilliSeconds = countDownInMilliSecond
            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun wrongAlertDialog() {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_layout_wrong_ans, null)
        builder.setView(view)
        val tvWrongDialogCorrectAns = view.findViewById<TextView>(R.id.tv_wrongDialog_correctAns)
        val wrongOk = view.findViewById<Button>(R.id.wrong_ok)
        tvWrongDialogCorrectAns.text = "Correct Answer : " + answer[qIndex]
        val alertDialog = builder.create()
        wrongOk.setOnClickListener {
            timeLeftMilliSeconds =
                countDownInMilliSecond
            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun timeOverAlertDialog() {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_layout_time_over, null)
        builder.setView(view)
        val timeOverOk = view.findViewById<Button>(R.id.timeOver_ok)
        val alertDialog = builder.create()
        timeOverOk.setOnClickListener {
            timeLeftMilliSeconds = countDownInMilliSecond
            statCountDownTimer()
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun showNextQuestion() {

        try {
            checkAnswer()
            if (updateQueNo < 10) {
                binding.tvNoOfQues.text = "${updateQueNo + 1}/10"
                updateQueNo++
            }
            if (qIndex <= questions.size - 1) {
                binding.tvQuestion.text = questions[qIndex]
                radioButton1.text = options[qIndex * 4] // 2*4=8
                radioButton2.text = options[qIndex * 4 + 1] //  2*4+1=9
                radioButton3.text = options[qIndex * 4 + 2] //  2*4+2=10
                radioButton4.text = options[qIndex * 4 + 3] //  2*4+3=11
            } else {
                score = correct
                val action = Java_Quiz_TenDirections.actionJavaQuizTenToResultActivityQuizTen(
                    correct.toString(),
                    wrong.toString(),
                    skip.toString()
                )
                findNavController().navigate(action)

            }
            binding.radiogrp.check(R.id.radioButton1)

            binding.radiogrp.clearCheck()
        } catch (e: Exception) {
            Log.i("mmm", "showNextQuestion: ${e.message}")
        }

    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer() {

        try {
            if (radiogrp.checkedRadioButtonId == -1) {
                skip++
                timeOverAlertDialog()

            } else {

                val checkRadioButton =
                    requireView().findViewById<RadioButton>(radiogrp.checkedRadioButtonId)

                val check = checkRadioButton?.text.toString()
                if (check == answer[qIndex]) {
                    correct++
                    binding.txtPlayScore.text = "Score : $correct"
                    correctAlertDialog()
                    countDownTimer.cancel()
                } else {
                    wrong++
                    wrongAlertDialog()
                    countDownTimer.cancel()
                }
            }
            qIndex++
        } catch (e: Exception) {
            Log.i("mmm", "showNextQuestion: ${e.message}")
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
