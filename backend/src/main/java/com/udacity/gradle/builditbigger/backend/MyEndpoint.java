package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.legue.axel.jokeprovider.JokeProvider;

import java.util.ArrayList;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v2",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private int i = 0;

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        JokeProvider mJokeProvider = new JokeProvider();

        ArrayList<String> jokeList = mJokeProvider.getJokeList();
        int numberOfJokes = jokeList.size();
        if (i + 1 > numberOfJokes) {
            i = 0;
        }
        MyBean response = new MyBean();
        response.setData(jokeList.get(i));
        i++;
        return response;
    }

}
