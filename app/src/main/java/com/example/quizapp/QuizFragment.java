package com.example.quizapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class QuizFragment extends Fragment {

    private static final String TAG = "QUIZ_FRAGMENT_LOG";
    private FirebaseFirestore firebaseFirestore;
    private String quizId;
    private TextView quizTitle;

    private List<QuestionsModel> allQuestions = new ArrayList<>();
    private long totalQuestionsToAnswer = 2;
    private List<QuestionsModel> questionsToAnswer = new ArrayList<>();


    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        quizTitle=view.findViewById(R.id.quizTitle);
        quizId = QuizFragmentArgs.fromBundle(getArguments()).getQuizid();
        totalQuestionsToAnswer = QuizFragmentArgs.fromBundle(getArguments()).getTotalQuestions();
        firebaseFirestore.collection("QuizList").document(quizId).collection("Questions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    allQuestions = task.getResult().toObjects(QuestionsModel.class);
                    pickQuestions();
                }
                else {
                    quizTitle.setText("Error loading Data");
                }
            }
        });
    }

    private void pickQuestions() {
        for (int i=0; i<totalQuestionsToAnswer;i++){
            int randomNumber = getRandomInteger(allQuestions.size(),0);
            questionsToAnswer.add(allQuestions.get(randomNumber));
            allQuestions.remove(randomNumber);
            Log.d(TAG,"Question: "+questionsToAnswer.get(i));
        }
    }
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum)))+minimum;
    }
}