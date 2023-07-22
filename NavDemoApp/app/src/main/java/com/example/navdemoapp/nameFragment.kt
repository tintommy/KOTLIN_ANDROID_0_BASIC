package com.example.navdemoapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.navdemoapp.databinding.FragmentNameBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [nameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class nameFragment : Fragment() {

    private lateinit var binding: FragmentNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNameBinding.inflate(layoutInflater)

        binding.apply {
            btnNext.setOnClickListener {
                if (TextUtils.isEmpty(etName.text.toString())) {
                    Toast.makeText(activity, "Hãy nhập tên của bạn", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle: Bundle=Bundle()
                    bundle.putString("name", etName.text.toString())

                    it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment,bundle)
                }
            }
        }


        return binding.root
    }


}