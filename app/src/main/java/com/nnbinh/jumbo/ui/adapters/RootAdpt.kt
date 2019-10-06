package com.nnbinh.jumbo.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.nnbinh.jumbo.ui.fragments.account.AccountFrgm
import com.nnbinh.jumbo.ui.fragments.products.ProductsFrgm
import com.nnbinh.jumbo.ui.fragments.suppermarkets.SuperMarketsFrgm

class RootAdpt(activity: AppCompatActivity): FragmentPagerAdapter(activity.supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  private val fragments: List<Fragment> by lazy {
    return@lazy arrayListOf<Fragment>(
        SuperMarketsFrgm.getInstance(),
        ProductsFrgm.getInstance(),
        AccountFrgm.getInstance()
    )
  }

  override fun getItem(position: Int): Fragment {
    return fragments[position]
  }

  override fun getCount(): Int = fragments.size
}