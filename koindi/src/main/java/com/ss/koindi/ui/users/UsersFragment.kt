package com.ss.koindi.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ss.koindi.base.BaseFragment
import com.ss.koindi.databinding.FragmentUsersBinding
import com.ss.koindi.utils.UseCaseResult
import com.ss.koindi.utils.fadeIn
import com.ss.koindi.utils.fadeOut
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel = viewModel<UsersViewModel>()

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
        binding.viewModel = viewModel.value
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        binding.rvUserList.adapter = usersAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.value.loadingState.observe(viewLifecycleOwner, {
            when (it.name) {
                UseCaseResult.LoadingState.START.name -> {
                    binding.shimmerView.fadeIn()
                }
                UseCaseResult.LoadingState.END.name -> {
                    binding.shimmerView.fadeOut()
                }
            }
        })

        viewModel.value.users.observe(viewLifecycleOwner, {
            it?.let {
                usersAdapter.users.addAll(it.results)
                usersAdapter.notifyItemRangeChanged(0, usersAdapter.itemCount)
                return@observe
            }
            usersAdapter.users.clear()
            usersAdapter.notifyDataSetChanged()
        })

        viewModel.value.onError.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "${it?.message}", Toast.LENGTH_SHORT).show()
        })

        if (viewModel.value.users.value == null) {
            viewModel.value.getUsers(20)
        }
    }
}