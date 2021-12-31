package com.example.finalproject22;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Character_Fragment extends Fragment {
 private List<ImagesResponce> imagesResponcesList=new ArrayList<>();
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView=view.findViewById(R.id.gridView);
        getAllImages();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(new Intent(getActivity(),ClickedActivity.class).putExtra("data",imagesResponcesList.get(position)));
            }
        });
    }

    public  void getAllImages(){
        Call<List<ImagesResponce>> imagesResponce = ApiClient.getInterface().getAllImages();
        imagesResponce.enqueue(new Callback<List<ImagesResponce>>() {
            @Override
            public void onResponse(Call<List<ImagesResponce>> call, Response<List<ImagesResponce>> response) {
                String message;
                if (response.isSuccessful()) {
                    message = "Successful request";
                    imagesResponcesList=response.body();
                    CustomAdapter customAdapter=new CustomAdapter(imagesResponcesList,getActivity());
                    gridView.setAdapter(customAdapter);

                }  else{
                    message = "An error occurred, try again later";

                }
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<List<ImagesResponce>> call, Throwable t) {
            String message=t.getLocalizedMessage();
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
}
public  class CustomAdapter extends BaseAdapter{
private List<ImagesResponce> imagesResponcesList;
private Context context;
private LayoutInflater layoutInflater;

    public CustomAdapter(List<ImagesResponce> imagesResponcesList, Context context) {
        this.imagesResponcesList = imagesResponcesList;
        this.context = context;
        this.layoutInflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return imagesResponcesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view== null){
            view =layoutInflater.inflate(R.layout.row_grid_items,viewGroup,false);
        }
        ImageView imageView=view.findViewById(R.id.imageView34);
        TextView textView= view.findViewById(R.id.sample);
        TextView textView1= view.findViewById(R.id.sample1);
        TextView textView2= view.findViewById(R.id.sample2);

        textView.setText(imagesResponcesList.get(position).getName());
        textView1.setText(imagesResponcesList.get(position).getBirthday());
        textView2.setText(imagesResponcesList.get(position).getStatus());
        GlideApp.with(context).load(imagesResponcesList
                .get(position)
                .getImg())
                .into(imageView);

        return view;
    }
}

}