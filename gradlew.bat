package com.sayed.cardiocare;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.SharedPreferences;
        import android.net.ConnectivityManager;
        import android.os.Build;
        import android.os.Handler;
        import android.support.design.widget.Snackbar;
        import android.support.v4.content.LocalBroadcastManager;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.CardView;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.NetworkResponse;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.ServerError;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.HttpHeaderParser;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.sayed.cardiocare.Models.LoggedpatientDetail;
        import com.sayed.cardiocare.app.AppController;
        import com.sayed.cardiocare.utils.CheckConnectivity;
        import com.sayed.cardiocare.utils.ConnectionChecker;
        import com.sayed.cardiocare.utils.Datas;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.UnsupportedEncodingException;
        import java.util.HashMap;
        import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView tv;
    CheckBox rememberLogin;

    EditText mobileEt, idEtReg;
    EditText passEt, idEt;
    String granType = "password";
    CardView logCv,regCv;
    private CheckConnectivity checkConnectivity;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate