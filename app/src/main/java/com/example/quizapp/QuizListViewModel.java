package com.example.quizapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskCompelete {
    private MutableLiveData<List<QuizListModel>> quizListModelData = new MutableLiveData<>();

    public LiveData<List<QuizListModel>> getQuizListModelData() {
        return quizListModelData;
    }


    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public QuizListViewModel(){
        firebaseRepository.getQuizData();
    }
    @Override
    public void quizListDataAdded(List<QuizListModel> quizListModelList) {
        quizListModelData.setValue(quizListModelList);
    }

    @Override
    public void onError(Exception e) {

    }
}
