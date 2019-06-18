package com.sayed.russelipm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InquiryActivity extends Fragment implements View.OnClickListener {

    MultiAutoCompleteTextView multiAutoCompleteTextView;
    List<String> productListInquiry;
    Button proceedBtn;
    EditText phoneNumEt, queryEt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_inquiry, container, false);
        String text = getArguments().getString("product_name", "");
        TextView textView = (TextView) view.findViewById(R.id.fragText);
        textView.setText(text);

        prepareProductList();

        multiAutoCompleteTextView = view.findViewById(R.id.mulautoCom_tv);
        proceedBtn = view.findViewById(R.id.btn_proceed);
        phoneNumEt = view.findViewById(R.id.mobile_num_et);
        queryEt = view.findViewById(R.id.message_text_et);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, productListInquiry);

        multiAutoCompleteTextView.setThreshold(1);    /**will start working from first character**/
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());   /**Set a Tokenizer**/
        multiAutoCompleteTextView.setAdapter(adapter);    /**setting the adapter data into the MultiAutoCompleteTextView**/
        multiAutoCompleteTextView.setTextColor(Color.BLUE);

        multiAutoCompleteTextView.setText(text);

        proceedBtn.setOnClickListener(this);

        return view;
    }

    private void prepareProductList() {
        productListInquiry = new ArrayList<>();

        productListInquiry.add("yokosan");
        productListInquiry.add("zonatrac");
        productListInquiry.add("ceranock");
        productListInquiry.add("biomax");
        productListInquiry.add("biotrine");
        productListInquiry.add("antario");
        productListInquiry.add("fytomax");
        productListInquiry.add("fizimite");
        productListInquiry.add("recharge");

    }

    @Override
    public void onClick(View v) {
        boolean flag = true;
        if(queryEt.getText().toString().trim().equals("")){
            queryEt.setError("write your query please");
            flag = false;
        }
        if (phoneNumEt.getText().toString().trim().equals("")){
            phoneNumEt.setError("provide your cell number please");
            flag = false;
        }
        if (multiAutoCompleteTextView.getText().toString().trim().equals("")){
            multiAutoCompleteTextView.setError("write products name please");
            flag = false;
        }

        if (flag){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@russellipmbd.com"});
//            i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            i.putExtra(Intent.EXTRA_SUBJECT, ""+"let me know about "+multiAutoCompleteTextView.getText().toString().trim());
            i.putExtra(Intent.EXTRA_TEXT   , queryEt.getText().toString().trim()+"\n\n\n"+"" +
                    "Cell number: "+phoneNumEt.getText().toString().trim());
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}