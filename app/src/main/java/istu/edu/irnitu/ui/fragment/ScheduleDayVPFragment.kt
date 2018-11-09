package istu.edu.irnitu.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

import istu.edu.irnitu.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_DAY_PARAM = "day"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ScheduleDayVPFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ScheduleDayVPFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ScheduleDayVPFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var day: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getInt(ARG_DAY_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_day, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

//        disposable.add()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onNextDayClick()
        fun onPrevDayClick()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param schedule Schedule for a day.
         * @return A new instance of fragment ScheduleDayVPFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(day: Int) =
            ScheduleDayVPFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAY_PARAM, day)
                }
            }
    }
}
