package com.sayed.russelipm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sayed.russelipm.adapter.SlideShowAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends Fragment implements View.OnClickListener {

    ViewPager viewPager;
    SlideShowAdapter adapter;
    CircleIndicator indicator;
    Handler handler;
    Timer timer;
    Runnable runnable;
    AutoCompleteTextView searchAutoCompleteTv;
    ArrayList<String> productList;

    LinearLayout biomaxLayout,biotrineLayout,ceranockLayout,fizimiteLayout,fytomiteLayout,
    rechargeLayout,yokosanLayout,zonatracLayout, antarioLayout;

    ImageView searchBtn;

    String productDetailUrl = "";
    String selectedProducted = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);

        searchAutoCompleteTv = (AutoCompleteTextView) view.findViewById(R.id.search_actv);
        searchBtn = view.findViewById(R.id.search_btn);

        biomaxLayout = view.findViewById(R.id.biomax_layout);
        biotrineLayout = view.findViewById(R.id.biotrine_layout);
        ceranockLayout = view.findViewById(R.id.ceranock_layout);
        fizimiteLayout = view.findViewById(R.id.fizimite_layout);
        fytomiteLayout = view.findViewById(R.id.fytomax_layout);
        rechargeLayout = view.findViewById(R.id.recharge_layout);
        yokosanLayout = view.findViewById(R.id.yokosan_layout);
        zonatracLayout = view.findViewById(R.id.zonatrac_layout);
        antarioLayout = view.findViewById(R.id.antario_layout);

        prepareProductList();

        /**Creating the instance of ArrayAdapter containing list of fruit names**/
        ArrayAdapter<String> adapterActv = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, productList);

        /**Getting the instance of AutoCompleteTextView**/
        searchAutoCompleteTv.setThreshold(1);    /**will start working from first character**/
        searchAutoCompleteTv.setAdapter(adapterActv);    /**setting the adapter data into the AutoCompleteTextView**/
        searchAutoCompleteTv.setTextColor(Color.BLUE);


        viewPager = (ViewPager) view.findViewById(R.id.bg_vp);

        indicator = (CircleIndicator) view.findViewById(R.id.circleIndicator_id);

        adapter = new SlideShowAdapter(getContext());

        viewPager.setAdapter(adapter);

        indicator.setViewPager(viewPager);

        String text = getArguments().getString("text", "");


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                int i = viewPager.getCurrentItem();

                if (i==adapter.images.length-1){

                    i=0;
                    viewPager.setCurrentItem(i,true);

                }else {

                    i++;
                    viewPager.setCurrentItem(i,true);
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        },2000,2000);


        searchAutoCompleteTv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    if(searchAutoCompleteTv.getText().toString().trim().equals("")){
                        return false;
                    }

                    if(!productList.contains(searchAutoCompleteTv.getText().toString().trim())){
                        Toast.makeText(getContext(), "Product not found", Toast.LENGTH_LONG).show();
                        return false;
                    }

                    int imID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim(), "drawable", getContext().getPackageName());
                    int strID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim()+"_des", "string", getContext().getPackageName());
                    int urlID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim()+"_url", "string", getContext().getPackageName());
                    productDetailUrl = getString(urlID);
                    dialogueShow(strID, imID);

                    selectedProducted = searchAutoCompleteTv.getText().toString().trim();

                    return true;
                }
                return false;
            }
        });

        searchBtn.setOnClickListener(this);

        biotrineLayout.setOnClickListener(this);
        biomaxLayout.setOnClickListener(this);
        ceranockLayout.setOnClickListener(this);
        fizimiteLayout.setOnClickListener(this);
        fytomiteLayout.setOnClickListener(this);
        rechargeLayout.setOnClickListener(this);
        yokosanLayout.setOnClickListener(this);
        zonatracLayout.setOnClickListener(this);
        antarioLayout.setOnClickListener(this);


        return view;
    }

    private void prepareProductList() {
        productList = new ArrayList<>();
        /**Add Items in List**/
        productList.add("yokosan");
        productList.add("zonatrac");
        productList.add("ceranock");
        productList.add("biomax");
        productList.add("biotrine");
        productList.add("antario");
        productList.add("fytomax");
        productList.add("fizimite");
        productList.add("recharge");

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_btn){

            if(searchAutoCompleteTv.getText().toString().trim().equals("")){
                return;
            }

            if(!productList.contains(searchAutoCompleteTv.getText().toString().trim())){
                Toast.makeText(getContext(), "Product not found", Toast.LENGTH_LONG).show();
                return;
            }

            int imID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim(), "drawable", getContext().getPackageName());
            int strID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim()+"_des", "string", getContext().getPackageName());
            int urlID = getResources().getIdentifier(searchAutoCompleteTv.getText().toString().trim()+"_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(strID, imID);
        }

        if (v.getId()==R.id.biotrine_layout){
            selectedProducted = "biotrine";
            int urlID = getResources().getIdentifier("biotrine_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.biotrine_des, R.drawable.biotrine);
        }
        if(v.getId() == R.id.biomax_layout){
            selectedProducted = "biomax";
            int urlID = getResources().getIdentifier("biomax_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.biomax_des, R.drawable.biomax);
        }
        if(v.getId() == R.id.ceranock_layout){
            selectedProducted = "ceranock";
            int urlID = getResources().getIdentifier("ceranock_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.ceranock_des, R.drawable.ceranock);
        }
        if(v.getId() == R.id.antario_layout){
            selectedProducted = "antario";
            int urlID = getResources().getIdentifier("antario_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.antario_des, R.drawable.antario);
        }
        if(v.getId() == R.id.fizimite_layout){
            selectedProducted = "fizimite";
            int urlID = getResources().getIdentifier("fizimite_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.fizimite_des, R.drawable.fizimite);
        }
        if(v.getId() == R.id.fytomax_layout){
            selectedProducted = "fytomax";
            int urlID = getResources().getIdentifier("fytomax_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.fytomax_des, R.drawable.fytomax);
        }
        if(v.getId() == R.id.recharge_layout){
            selectedProducted = "recharge";
            int urlID = getResources().getIdentifier("recharge_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.recharge_des, R.drawable.recharge);
        }
        if(v.getId() == R.id.yokosan_layout){
            selectedProducted = "yokosan";
            int urlID = getResources().getIdentifier("yokosan_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.Yokosan_des, R.drawable.yokosan);
        }
        if(v.getId() == R.id.zonatrac_layout){
            selectedProducted = "zonatrac";
            int urlID = getResources().getIdentifier("zonatrac_url", "string", getContext().getPackageName());
            productDetailUrl = getString(urlID);
            dialogueShow(R.string.zonatrac_des, R.drawable.zonatrac);
        }
    }

    public void dialogueShow(int desText, int img){
        View view = getLayoutInflater().inflate(R.layout.bottomsheet_layout, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();

        TextView descTv = view.findViewById(R.id.product_desc_tv);
        descTv.setText(desText);

        ImageView imageView = view.findViewById(R.id.product_detail_iv);
        imageView.setImageResource(img);

        ImageView moreInfoOfProduct = view.findViewById(R.id.more_info_iv);
        moreInfoOfProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WebActivity.class);
                i.putExtra("url",productDetailUrl);
                startActivity(i);
            }
        });

        ImageView inquiryProduct = view.findViewById(R.id.inquiry_iv);
        inquiryProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InquiryActivity initFrag = new InquiryActivity();
                Bundle bundle = new Bundle();
                bundle.putString("product_name", selectedProducted);
                initFrag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, initFrag);
                transaction.commit();
                dialog.dismiss();
            }
        });
    }
}