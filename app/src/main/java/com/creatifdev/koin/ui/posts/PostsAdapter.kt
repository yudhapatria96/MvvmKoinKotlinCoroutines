package com.android.post.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.post.domain.model.Post
import com.creatifdev.koin.R
import com.creatifdev.koin.databinding.HolderPostBinding
import kotlin.properties.Delegates

class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mPostList: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_post, parent, false
        )
        return PostViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int {
        return if (mPostList.isNullOrEmpty()) 0 else mPostList.size
    }

    private fun getItem(position: Int): Post {
        return mPostList[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(getItem(position))
    }

    inner class PostViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(post: Post) {
            (viewDataBinding as HolderPostBinding).post = post
        }
    }

    companion object {
        private val TAG = PostsAdapter::class.java.simpleName
    }
}