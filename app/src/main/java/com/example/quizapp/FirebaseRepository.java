package com.example.quizapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {
    private OnFirestoreTaskCompelete onFirestoreTaskCompelete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference quizRef = firebaseFirestore.collection("QuizList");

    public  FirebaseRepository(OnFirestoreTaskCompelete onFirestoreTaskCompelete){
        this.onFirestoreTaskCompelete = onFirestoreTaskCompelete;
    }

    public void getQuizData(){
        quizRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    onFirestoreTaskCompelete.quizListDataAdded(task.getResult().toObjects(QuizListModel.class));
                }else {
                    onFirestoreTaskCompelete.onError(task.getException());
                }
            }
        });
    }
    public interface OnFirestoreTaskCompelete{
        void quizListDataAdded(List<QuizListModel> quizListModelList);
        void onError(Exception e);
    }
}
