package com.sweetmay.githubclient.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.App
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.presenter.RepoPresenter
import com.sweetmay.githubclient.view.ReposView
import com.sweetmay.githubclient.view.adapter.RepoAdapterRV
import com.sweetmay.utils.NetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(R.layout.fragment_repos), ReposView {

    lateinit var adapterRV: RepoAdapterRV

    private val currentUser: GitHubUser
    get() {
        return arguments?.get(USER_KEY) as GitHubUser
    }

    companion object{
        const val USER_KEY = "user_key"
        fun getInstance(gitHubUser: GitHubUser): Fragment{
            val personalFragment = RepoFragment()
            val bundle = Bundle()
            bundle.putParcelable(USER_KEY, gitHubUser)
            personalFragment.arguments = bundle
            return personalFragment
        }
    }

    private val presenter by moxyPresenter { RepoPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadRepos(currentUser)
    }

    override fun initRV() {
        repos_rv.layoutManager = LinearLayoutManager(context)
        adapterRV = RepoAdapterRV(presenter.repoListPresenter)
        repos_rv.adapter = adapterRV
    }

    override fun updateList() {
        adapterRV.notifyDataSetChanged()
    }

    override fun renderUserName() {
        activity?.title = currentUser.login
    }
}