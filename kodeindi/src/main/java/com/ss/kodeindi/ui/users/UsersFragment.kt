package com.ss.kodeindi.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ss.kodeindi.base.BaseFragment
import com.ss.kodeindi.databinding.FragmentUsersBinding
import com.ss.kodeindi.utils.UseCaseResult
import com.ss.kodeindi.utils.fadeIn
import com.ss.kodeindi.utils.fadeOut
import com.ss.kodeindi.utils.kodeinViewModel

class UsersFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by kodeinViewModel()

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
                UseCaseResult.LoadingState.START.name -> {
                    binding.shimmerView.fadeIn()
                }
                UseCaseResult.LoadingState.END.name -> {
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