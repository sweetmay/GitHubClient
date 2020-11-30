package com.sweetmay.githubclient.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sweetmay.App
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.presenter.ForkPresenter
import com.sweetmay.githubclient.view.ForkView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_fork.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ForkFragment: MvpAppCompatFragment(R.layout.fragment_fork), ForkView {


    private val currentUser: UsersRepo
        get() {
            return arguments?.get(REPO_KEY) as UsersRepo
        }

    private val presenter by moxyPresenter { ForkPresenter() }

    companion object{
        const val REPO_KEY = "repo_key"
        fun getInstance(repo: UsersRepo): Fragment {
            val forkFragment = ForkFragment()
            val bundle = Bundle()
            bundle.putParcelable(REPO_KEY, repo)
            forkFragment.arguments = bundle
            return forkFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fork, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadForks(currentUser.forks_url)
    }

    override fun showForks(numberOfForks: String) {
        forks.text = numberOfForks
    }

}