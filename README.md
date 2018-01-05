# AndroidTransationAnimations
Activity transation with animation and floating action button animate to alert dialg.

# Requirements
 - minSdkVersion 21
 
# Preview
![alt text](https://github.com/Pixelpoint-Technology/AndroidTransationAnimations/blob/master/preview_image.gif)
 
# ActivityA

      In XML
            <ImageView
                android:id="@+id/image"
                android:layout_width="150dp"
                android:layout_height="100dp"
                android:transitionName="simple_activity_transition"  // Make sure transitionName must be shame in activityB where you want to share the view
                tools:ignore="UnusedAttribute"
                android:scaleType="fitXY"
                android:padding="5dp" />

            <TextView
                android:id="@+id/name"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textStyle="bold"
                android:layout_marginLeft="10dp"
                android:layout_gravity="center"
                android:textColor="#333333"
                android:transitionName="simple_activity_text"
                tools:ignore="UnusedAttribute"
                android:text="Name"
                android:textSize="16sp" />
    In Java
      Intent intent = new Intent(context, DetailActivity.class);
             intent.putExtra("image",itemList.getImage_drawable());
             intent.putExtra("name",itemList.getName());
             ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, Pair.create((View)holder.iv_image, ViewCompat.getTransitionName(holder.iv_image)),Pair.create((View)holder.tv_name, ViewCompat.getTransitionName(holder.tv_name)));
             context.startActivity(intent, options.toBundle());

You can share more than one view at a time to ActivityB

# ActivityB
        if (getIntent().getExtras() != null) {
            Picasso.with(context).load(getIntent().getIntExtra("image", R.drawable.img_1)).error(R.drawable.img_1).into(iv_image);
            tv_name.setText(getIntent().getStringExtra("name"));
        }
