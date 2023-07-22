package com.example.navdemoapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.navdemoapp.databinding.FragmentEmailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [emailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class emailFragment : Fragment() {

private lateinit var binding: FragmentEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEmailBinding.inflate(inflater,container,false)
        binding.apply {
            btnSubmit.setOnClickListener {
                if (TextUtils.isEmpty(etEmail.text.toString())) {
                    Toast.makeText(activity, "Hãy nhập email của bạn", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle: Bundle=Bundle()
                    val arg= arguments
                    bundle.putString("email", etEmail.text.toString())
                    bundle.putString("name",arg?.getString("name"))
                    it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle)
                }
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment emailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            emailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}