package com.example.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizListAdapter  extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private List<QuizListModel> quizListModels;

    public void setQuizListModel(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.listTitle.setText(quizListModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
       if(quizListModels == null){
           return 0;
       }
       else {
           return quizListModels.size();
       }
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private ImageView listImage;
        private TextView listTitle, listDesc, listLevel;
        private Button listBtn;
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.listImage);
            listTitle= itemView.findViewById(R.id.listTitle);
            listDesc = itemView.findViewById(R.id.listDesc);
            listLevel = itemView.findViewById(R.id.listDiff);
            listBtn = itemView.findViewById(R.id.listBtn);
        }
    }
}