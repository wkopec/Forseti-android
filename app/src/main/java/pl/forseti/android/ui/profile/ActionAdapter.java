package pl.forseti.android.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.forseti.android.R;
import pl.forseti.android.models.ProfileAction;
import pl.forseti.android.ui.MainActivity;
import pl.forseti.android.utils.ParseDate;

import static pl.forseti.android.utils.Constants.LAST_ACCOUNT_NUMBER;
import static pl.forseti.android.utils.Constants.PREFS;
import static pl.forseti.android.utils.ParseDate.DATE_PATTERN_READABLE;

public class ActionAdapter  extends RecyclerView.Adapter<ActionAdapter.ViewHolder>{

    private List<ProfileAction> profileActions;
    private MainActivity activity;

    public ActionAdapter(List<ProfileAction> profileActions, MainActivity activity) {
        this.profileActions = profileActions;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_action, parent, false);
        return new ActionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.date.setText(ParseDate.dateToStringDate(profileActions.get(position).getDate(), DATE_PATTERN_READABLE));
        switch (profileActions.get(position).getType()) {
            case "COMMENT":
                holder.description.setText(String.format(activity.getString(R.string.profile_comment_description), profileActions.get(position).getAccountNumber()));
                holder.icon.setBackground(activity.getDrawable(R.drawable.ic_comments));
                break;
            case "UP":
                holder.description.setText(String.format(activity.getString(R.string.profile_thumb_up_description), profileActions.get(position).getAccountNumber()));
                holder.icon.setBackground(activity.getDrawable(R.drawable.ic_thumb_up));
                break;
            case "DOWN":
                holder.description.setText(String.format(activity.getString(R.string.profile_thumb_down_description), profileActions.get(position).getAccountNumber()));
                holder.icon.setBackground(activity.getDrawable(R.drawable.ic_thumb_down));
                break;
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LAST_ACCOUNT_NUMBER, profileActions.get(position).getAccountNumber());
                editor.apply();
                activity.onBackPressed();
            }
        });


    }

    @Override
    public int getItemCount() {
        return profileActions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item)
        ConstraintLayout item;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.icon)
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
