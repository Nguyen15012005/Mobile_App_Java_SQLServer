package com.sinhvien.finalproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.sinhvien.finalproject.Activities.HomeActivity;
import com.sinhvien.finalproject.Activities.WelcomeActivity;
import com.sinhvien.finalproject.DAO.NhanVienDAO;
import com.sinhvien.finalproject.DAO.QuyenDAO;
import com.sinhvien.finalproject.DTO.NhanVienDTO;
import com.sinhvien.finalproject.DTO.QuyenDTO;
import com.sinhvien.finalproject.R;

public class DisplayInformationFragment extends Fragment {
    TextView i4_Hoten, i4_Birth, i4_Gender, i4_Email, i4_Phonenum, i4_Logout, i4_ChucVu;
    FragmentManager fragmentManager;
    NhanVienDAO nhanVienDAO;
    int manv;
    QuyenDAO quyenDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.displayinformation_layout,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Thông tin</font>"));
        setHasOptionsMenu(false);

        i4_Hoten = view.findViewById(R.id.i4_hoten);
        i4_Birth = view.findViewById(R.id.i4_birth);
        i4_Gender = view.findViewById(R.id.i4_gender);
        i4_Email = view.findViewById(R.id.i4_mail);
        i4_Phonenum = view.findViewById(R.id.i4_phonenum);
        i4_Logout = view.findViewById(R.id.i4_logout);
        i4_ChucVu = view.findViewById(R.id.i4_chucvu);


        nhanVienDAO = new NhanVienDAO(getActivity());
        quyenDAO = new QuyenDAO(getActivity());
        Intent intent = getActivity().getIntent();
        manv = intent.getIntExtra("manv",0);
        NhanVienDTO nhanVienDTO = nhanVienDAO.LayNVTheoMa(manv);
        int maQuyen = nhanVienDAO.LayQuyenNV(manv);
        String tenQuyen = quyenDAO.LayTenQuyenTheoMa(maQuyen);
        i4_Hoten.setText(nhanVienDTO.getHOTENNV());
        i4_Birth.setText(nhanVienDTO.getNGAYSINH());
        i4_Gender.setText(nhanVienDTO.getGIOITINH());
        i4_ChucVu.setText(tenQuyen);
        i4_Email.setText(nhanVienDTO.getEMAIL());
        i4_Phonenum.setText(nhanVienDTO.getSDT());
        i4_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });



        return  view;
    }


}
