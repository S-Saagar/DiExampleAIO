package com.ss.daggerhiltdi.ui.users

import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.ss.daggerhiltdi.base.BaseViewModel
import com.ss.daggerhiltdi.data.model.Users
import com.ss.daggerhiltdi.data.repository.UsersRepository
import com.ss.daggerhiltdi.utils.UseCaseResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job

class UsersViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    BaseViewModel() {

    private var job: Job? = null
    private val _users = MutableLiveData<Users?>()
    val users: LiveData<Users?> = _users

    fun getUsers(result: Int, refreshUser: Boolean = false) {
        if (refreshUser) {
            job?.cancel(CancellationException("Refresh data"))
            _users.value = null
        }
        loadingState.value = UseCaseResult.LoadingState.START
        val userApi = repository.getUsers(result)
        job = populateResult(userApi, _users, true)
    }
}

@BindingAdapter("imageUrl")
fun ShapeableImageView.loadImage(imageUrl: String?) {
    if (imageUrl == null) return
    val requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).circleCrop()

    Glide.with(this)
        .applyDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(viewModel: UsersViewModel?) {
    setOnRefreshListener {
        viewModel?.getUsers(20, true)
        isRefreshing = false
    }
}