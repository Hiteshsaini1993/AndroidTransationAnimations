package transation.animation.com.transationanimations;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    RecyclerView rv_main;
    int[] images;
    String[] names;
    ArrayList<DataModel> itemLIst=new ArrayList<>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context            = this;
        rv_main            = findViewById(R.id.rv_main);

        images = new int[]{R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,R.drawable.img_7,R.drawable.img_8,R.drawable.img_9,R.drawable.img_10};
        names = new String[]{"India","Barbie","Mountain","The Royal Bike","Nature","Birds","Sky","Sea","Happy New Year","Dream House"};

        for (int i=0; i<images.length;i++){
            DataModel dataModel = new DataModel();
            dataModel.setName(names[i]);
            dataModel.setImage_drawable(images[i]);
            itemLIst.add(dataModel);
        }

        adapter =  new MainAdapter(itemLIst,context);
        rv_main.setAdapter(adapter);
    }
}
