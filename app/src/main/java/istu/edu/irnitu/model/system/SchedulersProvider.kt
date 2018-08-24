package istu.edu.irnitu.model.system

import io.reactivex.Scheduler

/**
 * Created by mikhailmustakimov on 20.11.2017.
 */
interface SchedulersProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}