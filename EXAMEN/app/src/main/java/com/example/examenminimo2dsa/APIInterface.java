package com.example.examenminimo2dsa;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/users/{username}")
    Call<GithubUserClass> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<GithubRepos>> getRepos(@Path("username") String username);



}
