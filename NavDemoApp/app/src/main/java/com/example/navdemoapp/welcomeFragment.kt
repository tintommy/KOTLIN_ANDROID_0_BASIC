package com.example.navdemoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.navdemoapp.databinding.FragmentWelcomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [welcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class welcomeFragment : Fragment() {

private lateinit var binding: FragmentWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val bundle=arguments
        val name= bundle?.getString("name")
        val email=bundle?.getString("email")
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        binding.apply {
            tvName.setText(name)
            tvEmail.setText(email)
            btnViewTerm.setOnClickListener {
                it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment)
            }
        }
        return binding.root
    }


}