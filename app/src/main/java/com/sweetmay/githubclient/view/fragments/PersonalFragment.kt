package com.sweetmay.githubclient.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.presenter.PersonalPresenter
import com.sweetmay.githubclient.view.PersonalView
import kotlinx.android.synthetic.main.fragment_personal.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PersonalFragment : MvpAppCompatFragment(R.layout.fragment_personal), PersonalView, BackButtonListener {

    companion object{
        const val USER_KEY = "user_key"
        fun getInstance(gitHubUser: GitHubUser): Fragment{
            val personalFragment = PersonalFragment()
            val bundle = Bundle()
            bundle.putParcelable(USER_KEY, gitHubUser)
            personalFragment.arguments = bundle
            return personalFragment
        }
    }


    private val presenter by moxyPresenter { PersonalPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }


    override fun renderData() {
        val user = arguments?.get(USER_KEY) as GitHubUser
        login.text = user.login
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}