package com.creatifdev.koin.module

import com.android.post.presentation.posts.PostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PostModule = module {

    viewModel { PostsViewModel(get()) }

    single { createGetPostsUseCase(get(), createApiErrorHandle()) }

    // single instance of PostsRepository
    single { createPostRepository(get()) }
}