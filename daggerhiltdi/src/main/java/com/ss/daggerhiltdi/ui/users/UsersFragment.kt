package com.ss.daggerhiltdi.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ss.daggerhiltdi.databinding.FragmentUsersBinding
import com.ss.daggerhiltdi.utils.UseCaseResult.LoadingState.END
import com.ss.daggerhiltdi.utils.UseCaseResult.LoadingState.START
import com.ss.daggerhiltdi.utils.fadeIn
import com.ss.daggerhiltdi.utils.fadeOut
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()

    private val usersAdapter by lazy {
        UsersAdapter {
            Toast.makeText(requireContext(), "${it?.name?.first}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        binding.rvUserList.adapter = usersAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadingState.observe(viewLifecycleOwner, {
            when (it.name) {
                START.name -> {
                    binding.shimmerView.fadeIn()
                }
                END.name -> {
                    binding.shimmerView.fadeOut()
                }
            }
        })

        viewModel.users.observe(viewLifecycleOwner, {
            it?.let {
                usersAdapter.users.addAll(it.results)
                usersAdapter.notifyItemRangeChanged(0, usersAdapter.itemCount)
                return@observe
            }
            usersAdapter.users.clear()
            usersAdapter.notifyDataSetChanged()
        })

        viewModel.onError.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "${it?.message}", Toast.LENGTH_SHORT).show()
        })

        if (viewModel.users.value == null) {
            viewModel.getUsers(20)
        }
    }
}