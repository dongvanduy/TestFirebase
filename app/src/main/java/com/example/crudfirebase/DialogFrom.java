package com.example.crudfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogFrom extends DialogFragment {
    @Nullable
    private EditText edtName, edtChuyenNganh, edtHocKy, edtId;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private String name, chuyenganh, hocky, id;
    private Button btnAdd;
    public DialogFrom(String name, String chuyennganh, String hocky, String id){
        this.name = name;
        this.chuyenganh = chuyennganh;
        this.id = id;
        this.hocky = hocky;
    }

    public DialogFrom() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.input_form, container, false);
        edtName = v.findViewById(R.id.edt_name);
        edtChuyenNganh = v.findViewById(R.id.edt_chuyen_nganh);
        edtHocKy = v.findViewById(R.id.edt_hoc_ky);
        edtId = v.findViewById(R.id.edt_id);
        btnAdd = v.findViewById(R.id.btn_add);

        edtName.setText(name);
        edtChuyenNganh.setText(chuyenganh);
        edtId.setText(id);
        edtHocKy.setText(hocky);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = edtName.getText().toString();
                String _chuyennganh = edtChuyenNganh.getText().toString();
                String _id = edtId.getText().toString();
                String _hocky = edtHocKy.getText().toString();

                if(TextUtils.isEmpty(_name)){
                    input((EditText)edtName, "Họ Tên" );
                }else if(TextUtils.isEmpty(_chuyennganh)){
                    input((EditText)edtChuyenNganh, "Chuyên Ngành" );
                }else if(TextUtils.isEmpty(_name)){
                    input((EditText)edtId, "MSSV" );
                }else if(TextUtils.isEmpty(_name)){
                    input((EditText)edtHocKy, "Học Kỳ" );
                }else{
                    database.child("test").push().setValue(new Student(_name, _chuyennganh, _hocky, _id)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(v.getContext(),"Add Success!", Toast.LENGTH_SHORT).show();
                            dismiss();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), "Add Fail!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void input(EditText txt, String s){
        txt.setError(s + "Không nên để trống!!");
        txt.requestFocus();
    }
}
