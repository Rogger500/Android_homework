package com.bytedance.jstu.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bytedance.jstu.homework.databinding.FragmentLandscapeVideoPlayBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LandscapeVideoPlayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandscapeVideoPlayFragment : Fragment() {
    val sharedViewModel: VideoViewModel by activityViewModels()
    private var _binding: FragmentLandscapeVideoPlayBinding?=null
    protected val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landscape_video_play, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LandscapeVideoPlayFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}