package okhttp;

import com.google.gson.Gson;
import dto.AuthRequest;
import dto.AuthRespons;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;



public class LoginTests {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();

    OkHttpClient client = new OkHttpClient();

    @Test

    public void LoginPositive() throws IOException {
        AuthRequest requestDTO = AuthRequest.builder()
                .username("ref@gmail.com")
                .password("$Qwe1234")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            AuthRespons responseDTO = gson.fromJson(response.body().string(), AuthRespons.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is: " + response.code());
            Assert.assertTrue(response.isSuccessful());

        } else {
            System.out.println("Response code is: " + response.code());
            Error errorDTO = gson.fromJson(response.body().string(), Error.class);
            System.out.println(errorDTO.getCause() + " " + errorDTO.getMessage() + " " + errorDTO.getMessage());
            Assert.assertTrue(response.isSuccessful());
        }
    }
}
//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicmVmQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzEyNTc1MzI2LCJpYXQiOjE3MTE5NzUzMjZ9.zfo1qA9uKayF7m4kHnzZEWizDmoNhrD7T1K9F5YkcQ4