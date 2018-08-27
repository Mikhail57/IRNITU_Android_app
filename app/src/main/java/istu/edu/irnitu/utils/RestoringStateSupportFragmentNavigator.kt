package istu.edu.irnitu.utils

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

abstract class RestoringStateSupportFragmentNavigator(
        private val fragmentManager: FragmentManager,
        @IdRes
        private val containerId: Int
) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun forward(command: Forward) {
        val fragment = fragmentManager.findFragmentByTag(command.screenKey)
                ?: createFragment(command.screenKey, command.transitionData)

        if (fragment == null) {
            unknownScreen(command)
            return
        }

        fragmentManager.backStackEntryCount

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransactionAnimation(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
        )

        fragmentTransaction
                .replace(containerId, fragment, command.screenKey)
                .addToBackStack(command.screenKey)
                .commit()

        localStackCopy.add(command.screenKey)
    }


    override fun replace(command: Replace) {
        val fragment = fragmentManager.findFragmentByTag(command.screenKey)
                ?: createFragment(command.screenKey, command.transitionData)

        if (fragment == null) {
            unknownScreen(command)
            return
        }

        if (localStackCopy.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy.pop()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransactionAnimation(
                    command,
                    fragmentManager.findFragmentById(containerId),
                    fragment,
                    fragmentTransaction
            )

            fragmentManager.primaryNavigationFragment

            fragmentTransaction
                    .replace(containerId, fragment)
                    .addToBackStack(command.getScreenKey())
                    .commit()
            localStackCopy.add(command.getScreenKey())

        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransactionAnimation(
                    command,
                    fragmentManager.findFragmentById(containerId),
                    fragment,
                    fragmentTransaction
            )

            fragmentTransaction
                    .replace(containerId, fragment, command.screenKey)
                    .commit()
        }
    }
}