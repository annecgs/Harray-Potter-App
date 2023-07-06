package com.example.frontend.ui.activity

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.frontend.R
import com.example.frontend.databinding.ActivityMainBinding
import com.example.frontend.ui.corvinalMembres.CorvinalFragment
import com.example.frontend.ui.favorites.FavoritesFragment
import com.example.frontend.ui.grifinoriaMembres.GrifinoriaFragment
import com.example.frontend.ui.lufalufaMembres.LufaLufaFragment
import com.example.frontend.ui.onboarding.OnboardingActivity.Companion.screen
import com.example.frontend.ui.sonserinaMembres.SonserinaFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.black)
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        /*if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.BLACK
        }*/

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_hogwarts,
                R.id.nav_griffindor,
                R.id.nav_corvinal,
                R.id.nav_lufalufa,
                R.id.nav_sonserina,
                R.id.nav_favoritos,
                R.id.nav_logout
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId

            if(id == R.id.nav_corvinal){
                replaceFragment(CorvinalFragment())
                closeDrawer()
            }

            if(id == R.id.nav_griffindor){
                replaceFragment(GrifinoriaFragment())
                closeDrawer()
            }

            if(id == R.id.nav_lufalufa){
                replaceFragment(LufaLufaFragment())
                closeDrawer()
            }

            if(id == R.id.nav_sonserina){
                replaceFragment(SonserinaFragment())
                closeDrawer()
            }

            if(id == R.id.nav_favoritos){
                replaceFragment(FavoritesFragment())
                closeDrawer()
            }

            if(id == R.id.nav_logout){
                //configAlertDiolog()
                finish()
            }

            true
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val transation = supportFragmentManager.beginTransaction()
        transation.replace(R.id.nav_host_fragment_content_main, fragment)
        transation.addToBackStack(null)
        transation.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun returnDetails(name: String){
        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        if(toolbar != null){
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(applicationContext.getColor(R.color.white))
            toolbar.title = name
            toolbar.setNavigationIcon(R.drawable.baseline_keyboard_arrow_left_24)
            toolbar.setNavigationOnClickListener(View.OnClickListener {
                onBackPressed()
                configMenu()
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun configMenu(){
        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        if(toolbar != null){
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(applicationContext.getColor(R.color.white))
            toolbar.setNavigationIcon(R.drawable.baseline_menu_24)
            when(screen){
                1 -> {
                    toolbar.setTitle(R.string.menu_home)
                }

                2 -> {
                    toolbar.setTitle(R.string.menu_griffindor)
                }

                3 -> {
                    toolbar.setTitle(R.string.menu_ravenclaw)
                }

                4 -> {
                    toolbar.setTitle(R.string.menu_hufflepuff)
                }

                5 -> {
                    toolbar.setTitle(R.string.menu_slytherin)
                }

                else -> {
                    toolbar.setTitle(R.string.menu_favoritos)
                }
            }
            toolbar.setNavigationOnClickListener(View.OnClickListener {openDrawer()})
        }
    }

    fun openDrawer(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawer(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawers()
    }

    private fun configAlertDiolog(){
        val alertDialog: AlertDialog? = applicationContext?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Sim",
                    DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
                setNegativeButton("Não",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            }
            builder.create()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
