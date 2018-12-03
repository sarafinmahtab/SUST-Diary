package edu.sust.diary.viewholders;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.sust.diary.R;
import edu.sust.diary.expandablelist.ParentViewHolder;
import edu.sust.diary.models.realm.Schools;

public class SchoolViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    private LinearLayout schoolLayout;
    private TextView schoolNameTextView;
    private final ImageView arrowExpandImageView;

    private Context context;

    public SchoolViewHolder(Context context, View itemView) {
        super(itemView);

        this.context = context;

        schoolLayout = itemView.findViewById(R.id.school_layout);

        schoolNameTextView = itemView.findViewById(R.id.school_name_text);
        arrowExpandImageView = itemView.findViewById(R.id.school_image_view);
    }

    public void bind(Schools schools) {
        schoolNameTextView.setText(schools.getName());
    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);

        if (expanded) {
            arrowExpandImageView.setRotation(ROTATED_POSITION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                schoolLayout.setBackgroundColor(context.getResources().getColor(R.color.orange, null));
                schoolNameTextView.setTextColor(context.getResources().getColor(R.color.white, null));
                arrowExpandImageView.setColorFilter(context.getResources().getColor(R.color.white, null));
            } else {
                schoolLayout.setBackgroundColor(context.getResources().getColor(R.color.orange));
                schoolNameTextView.setTextColor(context.getResources().getColor(R.color.white));
                arrowExpandImageView.setColorFilter(context.getResources().getColor(R.color.white));
            }

        } else {
            arrowExpandImageView.setRotation(INITIAL_POSITION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                schoolLayout.setBackgroundColor(context.getResources().getColor(R.color.transparent, null));
                schoolNameTextView.setTextColor(context.getResources().getColor(R.color.textColor, null));
                arrowExpandImageView.setColorFilter(context.getResources().getColor(R.color.textColor, null));
            } else {
                schoolLayout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                schoolNameTextView.setTextColor(context.getResources().getColor(R.color.textColor));
                arrowExpandImageView.setColorFilter(context.getResources().getColor(R.color.textColor));
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);

        RotateAnimation rotateAnimation;
        if (expanded) { // rotate clockwise
            rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                    INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        } else { // rotate counterclockwise
            rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                    INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        }

        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        arrowExpandImageView.startAnimation(rotateAnimation);
    }
}
