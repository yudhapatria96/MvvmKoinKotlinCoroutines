package com.android.post.domain.usecase

import com.android.post.domain.model.Post
import com.android.post.domain.repository.PostsRepository
import com.android.post.domain.usecase.base.UseCase
import com.creatifdev.koin.network.exception.ApiErrorHandle

class GetPostsUseCase constructor(
    private val postsRepository: PostsRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<Post>, Any?>(apiErrorHandle) {

    override suspend fun run(params: Any?): List<Post> {
        return postsRepository.getPosts()
    }

}