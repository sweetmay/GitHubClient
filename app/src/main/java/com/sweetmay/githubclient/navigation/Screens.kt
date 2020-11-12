package com.sweetmay.githubclient.navigation

import androidx.fragment.app.Fragment
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.view.fragments.PersonalFragment
import com.sweetmay.githubclient.view.fragments.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return UserFragment()
        }
    }
    class PersonalScreen(gitHubUser: GitHubUser) : SupportAppScreen() {
        val user = gitHubUser
        override fun getFragment(): Fragment? {
            return PersonalFragment.getInstance(user)
        }
    }
}