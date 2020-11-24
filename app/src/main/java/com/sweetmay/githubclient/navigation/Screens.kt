package com.sweetmay.githubclient.navigation

import androidx.fragment.app.Fragment
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.view.fragments.ForkFragment
import com.sweetmay.githubclient.view.fragments.RepoFragment
import com.sweetmay.githubclient.view.fragments.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UserFragment()
        }
    }
    class RepoScreen(private val gitHubUser: GitHubUser) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RepoFragment.getInstance(gitHubUser)
        }
    }
    class ForkScreen(val repo: UsersRepo) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ForkFragment.getInstance(repo)
        }
    }
}