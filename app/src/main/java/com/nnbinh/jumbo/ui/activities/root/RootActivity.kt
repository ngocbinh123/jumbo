package com.nnbinh.jumbo.ui.activities.root

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.nnbinh.jumbo.BaseActivity
import com.nnbinh.jumbo.R
import com.nnbinh.jumbo.UserViewModel
import com.nnbinh.jumbo.databinding.ActivityRootBinding
import com.nnbinh.jumbo.db.SuperMarket
import com.nnbinh.jumbo.ui.fragments.account.AccountFrgm
import com.nnbinh.jumbo.ui.fragments.products.ProductsFrgm
import com.nnbinh.jumbo.ui.fragments.suppermarkets.SuperMarketsFrgm
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_root.nav_root
import javax.inject.Inject

class RootActivity : BaseActivity(), HasSupportFragmentInjector {
  companion object {
    fun start(context: Context) {
      val intent = Intent(context, RootActivity::class.java)
      context.startActivity(intent)
    }
  }

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return androidInjector
  }

  override fun initViewModel(): ViewModel {
    return ViewModelProviders.of(this, factory)[RootVM::class.java]
  }

  private val fragments: List<Fragment> by lazy {
    return@lazy arrayListOf<Fragment>(
        SuperMarketsFrgm.getInstance(),
        ProductsFrgm.getInstance(),
        AccountFrgm.getInstance()
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    val binding : ActivityRootBinding = DataBindingUtil.setContentView(this, R.layout.activity_root)
    binding.viewmodel = viewmodel as RootVM
    binding.lifecycleOwner = this

    setupObservers()
    loadFragment(fragments.first())
    setupNavigation()
  }

  private fun setupObservers() {
    (viewmodel as UserViewModel).command.observe(this, Observer { processCommand(it) })
  }

  private fun setupNavigation() {
    nav_root.setOnNavigationItemSelectedListener { item ->
      val position = when(item.itemId) {
        R.id.act_home -> 0
        R.id.act_products -> 1
        R.id.act_account -> 2
        else -> null
      }

      position?.let { pos ->
        loadFragment(fragments[pos])
        return@setOnNavigationItemSelectedListener true
      }
      return@setOnNavigationItemSelectedListener false
    }
  }

  private fun loadFragment(frgm: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.lout_holder, frgm, frgm::class.java.name)
        .commit()
  }

  fun onClickSuperMarket(superMarket: SuperMarket) {
    (fragments.first() as? SuperMarketsFrgm)?.onSelectedSuperMarket(superMarket)
  }

  fun onSubmitCheckInOutInfo() {
    (fragments.first() as? SuperMarketsFrgm)?.onSubmitWorkingTime()
  }
}