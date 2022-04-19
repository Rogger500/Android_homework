package com.bytedance.jstu.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bytedance.jstu.homework.databinding.FragmentImageItemBinding

private const val ARG_POSITION = "position"

class ImageItemFragment : Fragment() {
    private var uri: String? = null
    private var _binding: FragmentImageItemBinding ? =null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uri = srcList[ it.getInt(ARG_POSITION) ]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImageItemBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(uri)
            .into(binding.imageViewItem)
    }

    companion object {
        val srcList:List<String> = listOf(
            "https://tse1-mm.cn.bing.net/th/id/R-C.f44a1d524f8c933942629c263483249b?rik=APjiFKhBkY%2bS4Q&riu=http%3a%2f%2fwww.yutudou.com%2fuploads%2fallimg%2f180105%2f1-1P105195531-50.jpg&ehk=640PHJ6kZbuA1EynUeKgUKgL69MG3vtpBj4PSYLbTc4%3d&risl=&pid=ImgRaw&r=0",
            "https://tse1-mm.cn.bing.net/th/id/R-C.a6c91ca4d498059c7f58e0ccaec83716?rik=aphHvLB1GK4%2bRg&riu=http%3a%2f%2fwww.qubiaoqing.cn%2fpic%2f2020%2f11%2f28%2faw1q0blmshj.jpg&ehk=OD%2bRXOCwBwtLVhSFmMefk0gQDYgynb2eHr%2fRB8Ylxy8%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1",
            "https://tse1-mm.cn.bing.net/th/id/R-C.540797e87a467d19c17619c9593d0141?rik=9HpALIqAWf4Wsw&riu=http%3a%2f%2fwww.biaoqingb.com%2fuploads%2fimg1%2f20200204%2ff2f4a08893c940e088cfd1d7fffe19e7.jpg&ehk=FzdMAnVMlFh3OlySG9e2lv5SBHRPIvrQqWxQDd9B47s%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1"
        )

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: Int) =
            ImageItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }

        fun srcSize():Int = srcList.size

    }
}