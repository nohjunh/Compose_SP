package com.nohjunh.jettrivia.repository

import com.nohjunh.jettrivia.data.DataOrException
import com.nohjunh.jettrivia.model.QuestionItem
import com.nohjunh.jettrivia.network.QuestionApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val api: QuestionApi
) : QuestionRepository {
    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    override suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) {
                // 무언가를 얻었다는 것이니까 loading은 끝났으므로 다시 false로
                dataOrException.loading = false
            }
        }catch (exception: Exception) {
            dataOrException.e = exception
            Timber.tag("getAllQuestion()").d(dataOrException.e!!.localizedMessage)
        }
        return dataOrException
    }


}