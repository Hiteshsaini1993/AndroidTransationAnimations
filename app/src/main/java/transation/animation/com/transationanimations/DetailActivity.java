package transation.animation.com.transationanimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class DetailActivity extends AppCompatActivity {
    Context context;
    ImageView iv_image;
    TextView tv_name;
    FloatingActionButton fab;
    RelativeLayout rl_parent;
    AlertDialog dialog;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context         = this;
        iv_image        = findViewById(R.id.image);
        tv_name         = findViewById(R.id.name);
        fab             = findViewById(R.id.fab);
        rl_parent       = findViewById(R.id.rl_parent);

        final Animation animBounce = AnimationUtils.loadAnimation(this, R.anim.bounce_anim);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent().getExtras() != null) {
            Picasso.with(context).load(getIntent().getIntExtra("image", R.drawable.img_1)).error(R.drawable.img_1).into(iv_image);
            tv_name.setText(getIntent().getStringExtra("name"));
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fab.show();
            }
        }, 600);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animBounce);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fabAnimation(0);
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fab.hide();
                                alertDialog();
                            }
                        }, 200);
                    }
                }, 200);


            }
        });

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog();
            }
        });
    }

    public void showImageDialog(){
        final Dialog dialog = new Dialog(this,R.style.MyDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ImageView image = new ImageView(context);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Picasso.with(context).load(getIntent().getIntExtra("image", R.drawable.img_1)).error(R.drawable.img_1).into(image);
        dialog.setContentView(image);
        dialog.show();
    }

    public void alertDialog(){
        final View dialogView = View.inflate(context, R.layout.dialog_custom, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                revealShow(dialogView, true, null);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                fab.show();
                fabAnimation(1);
            }
        });
        dialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealShow(dialogView, false, dialog);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void revealShow(View rootView, boolean reveal, final AlertDialog dialog) {
        final View view = rootView.findViewById(R.id.reveal_view);
        int w = view.getWidth();
        int h = view.getHeight();
        float maxRadius = (float) Math.sqrt(w * w / 4 + h * h / 4);

        if(reveal){
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view,
                    w / 2, h / 2, 0, maxRadius);
            view.setVisibility(View.VISIBLE);
            revealAnimator.start();
        } else {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, w / 2, h / 2, maxRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);
                    fab.show();
                    fabAnimation(1);
                }
            });

            anim.start();
        }
    }

    public void fabAnimation(int position){
        float parentCenterX = (rl_parent.getWidth()/2)-(fab.getX()+(fab.getWidth()/2));
        float parentCenterY = (rl_parent.getHeight()/2)-getSupportActionBar().getHeight()/2-(fab.getY()+(fab.getHeight()/2));
        if (position ==1) {
            parentCenterX = 0;
            parentCenterY = 0;
        }
        fab.animate().translationX(parentCenterX).translationY(parentCenterY).setDuration(200);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        fab.hide();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAfterTransition();
            }
        }, 100);

    }
}
