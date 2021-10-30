package com.example.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class QuizListAdapter  extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {

    private List<QuizListModel> quizListModels;
    private OnQuizListItemClicked onQuizListItemClicked;

    public QuizListAdapter(OnQuizListItemClicked onQuizListItemClicked){
        this.onQuizListItemClicked = onQuizListItemClicked;
    }

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
        String imageUrl = quizListModels.get(position).getImage();
        Glide.with(holder.itemView.getContext()).load(imageUrl).centerCrop().placeholder(R.drawable.placeholder_image).into(holder.listImage);

        String listDerscription = quizListModels.get(position).getDesc();
        if (listDerscription.length() > 150){
            listDerscription = listDerscription.substring(0, 150);
        }
        holder.listDesc.setText(listDerscription + "...");
        holder.listLevel.setText(quizListModels.get(position).getLevel());
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

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            listBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuizListItemClicked.onItemClicked(getAdapterPosition());
        }
    }
    public interface OnQuizListItemClicked{
        void onItemClicked(int position);
    }
}
