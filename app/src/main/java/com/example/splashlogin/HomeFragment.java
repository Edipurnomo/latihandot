package com.example.splashlogin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splashlogin.API.BookApiService;
import com.example.splashlogin.Adapter.BookAdapter;
import com.example.splashlogin.Adapter.MemberListAdapter;
import com.example.splashlogin.model.Book;
import com.example.splashlogin.rest.AppService;
import com.example.splashlogin.rest.DialogUtility;
import com.example.splashlogin.rest.RetrofitUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Retrofit retrofit;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView listMember;
    private LinearLayoutManager linearLayoutManager;
    private MemberListAdapter memberListAdapter;
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_1, container, false);
        initRetrofit();
        getAllBookData();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }
    private void initRecyclerView() {
        listMember = view.findViewById(R.id.listMember);
        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter(context, this);
        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);
    }
    private void initRetrofit() {
        retrofit = RetrofitUtility.initialieRetrofit();
    }

    private void getAllBookData() {
        DialogUtility.showDialog(R.raw.plane, "Loading : Get Data Buku", getContext());
        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<List<Book>> result = apiService.getAllBuku(AppService.getToken());
        result.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<com.example.splashlogin.model.Book>> call, Response<List<com.example.splashlogin.model.Book>> response) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    DialogUtility.closeAllDialog();
                    addData(response.body());
                }, 1000);
            }

            @Override
            public void onFailure(Call<List<com.example.splashlogin.model.Book>> call, Throwable t) {
                t.printStackTrace();
                DialogUtility.closeAllDialog();
                DialogUtility.showCustomDialog(R.raw.error, "Error : " + t.getMessage(), getContext());
            }
        });
    }

    private void addData(List<Book> data) {
        List<BookAdapter> bookAdapterList = new ArrayList<>();
        BookAdapter bookAdapter;
        for (Book books : data) {
            bookAdapter = new BookAdapter();
            bookAdapter.setId(books.getId());
            bookAdapter.setJudul(books.getJudul());
            bookAdapter.setPenulis(books.getPenulis());
            bookAdapter.setPenerbit(books.getPenerbit());
            bookAdapter.setTahun(String.valueOf(books.getTahun()));
            bookAdapter.setHarga(String.valueOf(books.getHarga()));
            bookAdapter.setThumb(books.getThumb());
            bookAdapterList.add(bookAdapter);
        }
        memberListAdapter.addAll(bookAdapterList);
    }

    public void openFragmentView(int id) {
        ((BookActivity)getActivity()).openViewFragment();
    }
}