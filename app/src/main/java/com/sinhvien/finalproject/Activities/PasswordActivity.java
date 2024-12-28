package com.sinhvien.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sinhvien.finalproject.DAO.NhanVienDAO;
import com.sinhvien.finalproject.DTO.NhanVienDTO;
import com.sinhvien.finalproject.R;

import java.util.regex.Pattern;

public class PasswordActivity extends AppCompatActivity {

    TextInputLayout TXTL_REPASSS_TenDN, TXTL_REPASSS_MATKHAU;
    private Button BTN_REPASS_XACNHAN;
    long check = 0 ;
    NhanVienDAO nhanVienDAO;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        TXTL_REPASSS_TenDN = (TextInputLayout)findViewById(R.id.txtl_repass_TenDN);
        TXTL_REPASSS_MATKHAU = (TextInputLayout)findViewById(R.id.txtl_repass_matkhau);

        BTN_REPASS_XACNHAN = findViewById(R.id.btn_repass_xacnhan);

        nhanVienDAO = new NhanVienDAO(this);


        BTN_REPASS_XACNHAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateUserName() | !validatePassWord()) {
                    return;
                }

                String tenDN = TXTL_REPASSS_TenDN.getEditText().getText().toString();
                String matKhau = TXTL_REPASSS_MATKHAU.getEditText().getText().toString();

                int ktra = nhanVienDAO.KiemTraUser(tenDN, matKhau);

                if (ktra != 0){
                    NhanVienDTO nhanVienDTO = nhanVienDAO.LayNVTheoMa(ktra);
                    TXTL_REPASSS_MATKHAU.getEditText().setText(nhanVienDTO.getMATKHAU());

                    nhanVienDTO.setMATKHAU(matKhau);
                    check = nhanVienDAO.CapNhapMatKhau(nhanVienDTO,ktra);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("ketquaktra",check);
                    setResult(RESULT_OK,intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Cập Nhật Mật Khẩu Thành Công !", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Tên Đăng Nhập Không Tồn Tại!", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private boolean validateUserName(){
        String val = TXTL_REPASSS_TenDN.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            TXTL_REPASSS_TenDN.setError(getResources().getString(R.string.not_empty));
            return false;
        }else {
            TXTL_REPASSS_TenDN.setError(null);
            TXTL_REPASSS_TenDN.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassWord(){
        String val = TXTL_REPASSS_MATKHAU.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            TXTL_REPASSS_MATKHAU.setError(getResources().getString(R.string.not_empty));
            return false;
        }else if(!PASSWORD_PATTERN.matcher(val).matches()){
            TXTL_REPASSS_MATKHAU.setError("Mật khẩu ít nhất 6 ký tự!");
            return false;
        }
        else {
            TXTL_REPASSS_MATKHAU.setError(null);
            TXTL_REPASSS_MATKHAU.setErrorEnabled(false);
            return true;
        }
    }
}
