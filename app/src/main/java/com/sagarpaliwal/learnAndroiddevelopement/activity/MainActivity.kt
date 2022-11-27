package com.sagarpaliwal.learnAndroiddevelopement.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ShareCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***************************  Night Mode Off -- Action bar implementation *****************************************/
        supportActionBar?.show()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appbarmain.toolbar)

        /********************************************  Drawer Layout **********************************/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.fragmentContainerView)

        /*************************** given below code for hamburger icon *********************************/

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, binding.appbarmain.toolbar, 0, 0)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        /*************************************-- Navigation Drawer item set on click listener ******************************************/
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dashboard -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.homeFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.aboutus -> {
                    navController.navigate(R.id.aboutUs)
                    drawerLayout.closeDrawer(GravityCompat.START)

                    true
                }
                R.id.shareapp -> {
                    drawerLayout.closeDrawer(GravityCompat.START)

                    val share =
                        "Learn Android Development-Download the app from PlayStore: https://play.google.com/store/apps/details?id=com.sagarpaliwal.learnAndroiddevelopement"

                    val intent = ShareCompat.IntentBuilder.from(this).setType("text/plain")
                        .setText(share).intent

                    startActivity(Intent.createChooser(intent, "Share Via"))

                    true
                }
                R.id.rateus -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
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

                    true
                }
                R.id.PrivacyPolicy -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.privacyPolicy)
                    true
                }
                R.id.MoreApplication -> {
                    val intent = Intent("android.intent.action.VIEW")
                    intent.data = Uri.parse("market://search?q=pub: AppStraa")
                    startActivity(intent)
                    true
                }
                R.id.feedback -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    val emailIntent = Intent(
                        Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "appstraa@gmail.com", null
                        )
                    )
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                    startActivity(emailIntent)

                    true
                }
                else -> {
                    false
                }
            }
        }


    }
    /************************************ Menu item in action bar **********************************************/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)

        when (item.itemId) {

            R.id.TryProMenuIcon -> {
                navController.navigate(R.id.upgradeProFragment)
            }
        }
        return super.onOptionsItemSelected(item)



    }


}
