package com.example.quizapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DetailsFragment extends Fragment implements View.OnClickListener {


    private NavController navController;
    private QuizListViewModel quizListViewModel;
    private int position;
    private ImageView detailsImage;
    private TextView detailsTitle, detailsDesc, detailsDiff, detailsQuestions;
    private Button detailsBtn;
    private String quizId;
    private long totalQuestions = 0;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        position = DetailsFragmentArgs.fromBundle(getArguments()).getPosition();

        detailsImage = view.findViewById(R.id.detailsImage);
        detailsTitle = view.findViewById(R.id.detailsTitle);
        detailsDesc = view.findViewById(R.id.detailsDesc);
        detailsDiff = view.findViewById(R.id.detailDeffText);
        detailsQuestions = view.findViewById(R.id.detailQuesText);
        detailsBtn = view.findViewById(R.id.detailBtn);

        detailsBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quizListViewModel = new ViewModelProvider(getActivity()).get(QuizListViewModel.class);
        quizListViewModel.getQuizListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModelList) {

                Glide.with(getContext()).load(quizListModelList.get(position).getImage()).centerCrop().placeholder(R.drawable.placeholder_image).into(detailsImage);
                detailsTitle.setText(quizListModelList.get(position).getName());
                detailsDesc.setText(quizListModelList.get(position).getDesc());
                detailsDiff.setText(quizListModelList.get(position).getLevel());
                detailsQuestions.setText(quizListModelList.get(position).getQuestions()+"");

                quizId = quizListModelList.get(position).getQuiz_id();
                totalQuestions = quizListModelList.get(position).getQuestions();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detailBtn:
                DetailsFragmentDirections.ActionDetailsFragmentToQuizFragment action = DetailsFragmentDirections.actionDetailsFragmentToQuizFragment();
                action.setTotalQuestions(totalQuestions);
                action.setQuizid(quizId);
                navController.navigate(action);
                break;
        }
    }
}