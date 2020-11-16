package com.creatifdev.koin.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.post.presentation.posts.PostsAdapter
import com.android.post.presentation.posts.PostsViewModel
import com.android.post.utils.isNetworkAvailable
import com.creatifdev.koin.R
import com.creatifdev.koin.databinding.ActivityPostsBinding
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class PostsActivity : AppCompatActivity() {
    private lateinit var activityPostsBinding: ActivityPostsBinding
    private var mAdapter: PostsAdapter? = null
    private val postViewModel: PostsViewModel by viewModel()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        mAdapter = PostsAdapter()
        activityPostsBinding.postsRecyclerView.adapter = mAdapter

        if (isNetworkAvailable()) {
            postViewModel.getPosts()
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(postViewModel) {
            postsData.observe(this@PostsActivity, Observer {
                activityPostsBinding.postsProgressBar.visibility = View.GONE
                mAdapter?.mPostList = it
            })

            messageData.observe(this@PostsActivity, Observer {
                Toast.makeText(this@PostsActivity, it, Toast.LENGTH_LONG).show()
            })

            showProgressbar.observe(this@PostsActivity, Observer { isVisible ->
                posts_progress_bar.visibility = if (isVisible) View.VISIBLE else View.GONE
            })
        }
    }

    companion object {
        private val TAG = PostsActivity::class.java.name
    }
}