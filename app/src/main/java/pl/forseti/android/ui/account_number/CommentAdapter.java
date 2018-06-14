package pl.forseti.android.ui.account_number;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.forseti.android.R;
import pl.forseti.android.models.Comment;
import pl.forseti.android.utils.ParseDate;

import static pl.forseti.android.utils.ParseDate.DATE_PATTERN_READABLE;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> comments;
    private Context context;

    public CommentAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.username.setText(comments.get(position).getUsername());
        holder.date.setText(ParseDate.dateToStringDate(comments.get(position).getTimeStamp(), DATE_PATTERN_READABLE));
        holder.comment.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.comment)
        TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
