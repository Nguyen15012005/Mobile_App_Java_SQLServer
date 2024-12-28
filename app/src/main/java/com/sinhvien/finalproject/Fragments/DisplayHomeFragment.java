package com.sinhvien.finalproject.Fragments;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.sinhvien.finalproject.Activities.AddCategoryActivity;
import com.sinhvien.finalproject.Activities.HomeActivity;
import com.sinhvien.finalproject.CustomAdapter.AdapterRecycleViewCategory;
import com.sinhvien.finalproject.CustomAdapter.AdapterRecycleViewStatistic;
import com.sinhvien.finalproject.DAO.DonDatDAO;
import com.sinhvien.finalproject.DAO.LoaiMonDAO;
import com.sinhvien.finalproject.DTO.DonDatDTO;
import com.sinhvien.finalproject.DTO.LoaiMonDTO;
import com.sinhvien.finalproject.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import androidx.appcompat.widget.Toolbar;


public class DisplayHomeFragment extends Fragment implements View.OnClickListener {

    RecyclerView rcv_displayhome_LoaiMon, rcv_displayhome_DonTrongNgay;
    RelativeLayout layout_displayhome_ThongKe,layout_displayhome_XemBan, layout_displayhome_XemMenu, layout_displayhome_XemNV;
    TextView txt_displayhome_ViewAllCategory, txt_displayhome_ViewAllStatistic, txt_displayhome_ThongKe,
            txt_displayhome_XemBan, txt_displayhome_XemMenu, txt_displayhome_XemNV;
    LoaiMonDAO loaiMonDAO;
    DonDatDAO donDatDAO;
    List<LoaiMonDTO> loaiMonDTOList;
    List<DonDatDTO> donDatDTOS;
    AdapterRecycleViewCategory adapterRecycleViewCategory;
    AdapterRecycleViewStatistic adapterRecycleViewStatistic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.displayhome_layout,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Danh mục chức năng</font>"));
        setHasOptionsMenu(true);

        //region Lấy dối tượng view
        rcv_displayhome_LoaiMon = (RecyclerView)view.findViewById(R.id.rcv_displayhome_LoaiMon);
        rcv_displayhome_DonTrongNgay = (RecyclerView)view.findViewById(R.id.rcv_displayhome_DonTrongNgay);
        layout_displayhome_ThongKe = (RelativeLayout)view.findViewById(R.id.layout_displayhome_ThongKe);
        layout_displayhome_XemBan = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemBan);
        layout_displayhome_XemMenu = (RelativeLayout)view.findViewById(R.id.layout_displayhome_XemMenu);
        txt_displayhome_ThongKe = (TextView) view.findViewById(R.id.txt_displayhome_ThongKe);
        txt_displayhome_XemBan = (TextView) view.findViewById(R.id.txt_displayhome_XemBan);
        txt_displayhome_XemMenu = (TextView) view.findViewById(R.id.txt_displayhome_XemMenu);
        txt_displayhome_ViewAllCategory = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllCategory);
        txt_displayhome_ViewAllStatistic = (TextView) view.findViewById(R.id.txt_displayhome_ViewAllStatistic);
        //endregion

        //khởi tạo kết nối
        loaiMonDAO = new LoaiMonDAO(getActivity());
        donDatDAO = new DonDatDAO(getActivity());

        HienThiDSLoai();
        HienThiDonTrongNgay();

        layout_displayhome_ThongKe.setOnClickListener(this);
        layout_displayhome_XemBan.setOnClickListener(this);
        layout_displayhome_XemMenu.setOnClickListener(this);
        txt_displayhome_ThongKe.setOnClickListener(this);
        txt_displayhome_XemBan.setOnClickListener(this);
        txt_displayhome_XemMenu.setOnClickListener(this);
        txt_displayhome_ViewAllCategory.setOnClickListener(this);
        txt_displayhome_ViewAllStatistic.setOnClickListener(this);

        return view;
    }

    private void HienThiDSLoai(){
        rcv_displayhome_LoaiMon.setHasFixedSize(true);
        rcv_displayhome_LoaiMon.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        loaiMonDTOList = loaiMonDAO.LayDSLoaiMon();
        adapterRecycleViewCategory = new AdapterRecycleViewCategory(getActivity(),R.layout.custom_layout_displaycategory,loaiMonDTOList);
        rcv_displayhome_LoaiMon.setAdapter(adapterRecycleViewCategory);
        adapterRecycleViewCategory.notifyDataSetChanged();
    }

    private void HienThiDonTrongNgay(){
        rcv_displayhome_DonTrongNgay.setHasFixedSize(true);
        rcv_displayhome_DonTrongNgay.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngaydat= dateFormat.format(calendar.getTime());

        donDatDTOS = donDatDAO.LayDSDonDatNgay(ngaydat);
        adapterRecycleViewStatistic = new AdapterRecycleViewStatistic(getActivity(),R.layout.custom_layout_displaystatistic,donDatDTOS);
        rcv_displayhome_DonTrongNgay.setAdapter(adapterRecycleViewStatistic);
        adapterRecycleViewCategory.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigation_view_trangchu);
        switch (id){
            case R.id.layout_displayhome_ThongKe:

            case R.id.txt_displayhome_ThongKe:

            case R.id.txt_displayhome_ViewAllStatistic:
                FragmentTransaction tranDisplayStatistic = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayStatistic.replace(R.id.contentView,new DisplayStatisticFragment());
                tranDisplayStatistic.addToBackStack(null);
                tranDisplayStatistic.commit();

                break;
            case R.id.txt_displayhome_XemBan:
            case R.id.layout_displayhome_XemBan:
                FragmentTransaction tranDisplayTable = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayTable.replace(R.id.contentView,new DisplayTableFragment());
                tranDisplayTable.addToBackStack(null);
                tranDisplayTable.commit();

                break;

            case R.id.txt_displayhome_XemMenu:

            case R.id.layout_displayhome_XemMenu:
                Intent iAddCategory = new Intent(getActivity(), AddCategoryActivity.class);
                startActivity(iAddCategory);

                break;


            case R.id.txt_displayhome_ViewAllCategory:
                FragmentTransaction tranDisplayCategory = getActivity().getSupportFragmentManager().beginTransaction();
                tranDisplayCategory.replace(R.id.contentView,new DisplayCategoryFragment());
                tranDisplayCategory.addToBackStack(null);
                tranDisplayCategory.commit();

                break;

        }
    }
}

