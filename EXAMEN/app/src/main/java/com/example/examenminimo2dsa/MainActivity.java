package com.example.examenminimo2dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIInterface APIIface;
    private String username;
    private GithubUserClass user;
    private List<GithubRepos> repos = new ArrayList<>();
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Intent getUser_intent = getIntent();
        username = getUser_intent.getStringExtra("username");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);


        APIIface = APIClient.getClient().create(APIInterface.class);

        getUser(username);
        getRepos(username);
    }


    public void getUser(final String username){
        Call<GithubUserClass> call = APIIface.getUser(username);
        call.enqueue(new Callback<GithubUserClass>() {
            @Override
            public void onResponse(Call<GithubUserClass> call, Response<GithubUserClass> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    TextView usernametext = findViewById(R.id.UsernameTextview);
                    TextView following = findViewById(R.id.FollowingTextview);
                    TextView followers = findViewById(R.id.FollowersTextview);
                    ImageView userImage = findViewById(R.id.imageView);

                    user = response.body();

                    String firstline = "Username:   " + user.getLogin();
                    String secondline = "Followers:  " + user.getFollowers();
                    String thirdline = "Following:  " + user.getFollowing();

                    usernametext.setText(firstline);
                    followers.setText(secondline);
                    following.setText(thirdline);
                    Picasso.get().load(user.getAvatar_url()).into(userImage);
                }
                if (!response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);


                }
            }
            @Override
            public void onFailure(Call<GithubUserClass> call, Throwable throwable) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "El usuario: ," + username + "no se puede encontrar", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void getRepos(final String username){
        Call<List<GithubRepos>> call = APIIface.getRepos(username);
        call.enqueue(new Callback<List<GithubRepos>>() {
            @Override
            public void onResponse(Call<List<GithubRepos>> call, Response<List<GithubRepos>> response) {
                if(!response.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "El usuario: ," + username + "no se puede encontrar", Toast.LENGTH_LONG).show();
                }
                if(response.isSuccessful())
                {

                    repos = response.body();

                    myAdapter = new MyRecyclerViewAdapter(repos);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepos>> call, Throwable throwable) {
                call.cancel();
                Log.d("minim2TAG", throwable.getMessage());
                Toast.makeText(getApplicationContext(), "El usuario: ," + username + "no se puede encontrar sus repos", Toast.LENGTH_LONG).show();
            }
        });



    }



}