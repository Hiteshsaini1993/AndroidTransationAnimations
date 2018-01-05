package transation.animation.com.transationanimations;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    private ArrayList<DataModel> reportModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_image;
        TextView tv_name;
        CardView cv_main;

        public MyViewHolder(View view) {
            super(view);

            tv_name            = (TextView) view.findViewById(R.id.name);
            iv_image           = (ImageView) view.findViewById(R.id.image);
            cv_main            =  view.findViewById(R.id.cv_main);

        }
    }

    public MainAdapter(ArrayList<DataModel> reportModelList, Context context) {
        this.reportModelList = reportModelList;
        this.context         = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataModel itemList = reportModelList.get(position);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.tv_name.setText(Html.fromHtml(itemList.getName(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tv_name.setText(Html.fromHtml(itemList.getName()));
        }

      //  holder.iv_image.setImageResource(itemList.getImage_drawable());

        Picasso.with(context).load(itemList.getImage_drawable()).error(R.drawable.img_1).resize(200,200).centerInside().into(holder.iv_image);

        holder.cv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image",itemList.getImage_drawable());
                intent.putExtra("name",itemList.getName());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, Pair.create((View)holder.iv_image, ViewCompat.getTransitionName(holder.iv_image)),Pair.create((View)holder.tv_name, ViewCompat.getTransitionName(holder.tv_name)));
                context.startActivity(intent, options.toBundle());


            }
        });



    }

    @Override
    public int getItemCount() {
        return reportModelList.size();
    }

}
