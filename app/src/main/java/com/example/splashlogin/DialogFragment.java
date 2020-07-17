package com.example.splashlogin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashlogin.API.BookApiService;
import com.example.splashlogin.Adapter.BookAdapter;
import com.example.splashlogin.Adapter.MemberListAdapter;
import com.example.splashlogin.model.Book;
import com.example.splashlogin.rest.AppService;
import com.example.splashlogin.rest.RetrofitUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DialogFragment extends Fragment {
    private CardView item_buku;
    ImageView bookThumb;
    TextView judul;
    TextView penulis;
    TextView penerbit;
    TextView tahun;
    TextView harga;

    private String TAG = "DialogFragment";

    private RecyclerView listMember;
    private LinearLayoutManager linearLayoutManager;
    private MemberListAdapter memberListAdapter;
    protected Context context;

    Button dialogdelete, dialogedit;

    Uri uri;

    private View view;
    private Retrofit retrofit;
    private String base64Image = "";
    private int id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        MemberListAdapter adapter = new MemberListAdapter(this);

        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        item_buku = view.findViewById(R.id.list_buku);
        bookThumb = view.findViewById(R.id.thumb);
        judul = view.findViewById(R.id.judulview);
        penulis = view.findViewById(R.id.penulisview);
        penerbit = view.findViewById(R.id.penerbitviewt);
        tahun = view.findViewById(R.id.tahunview);
        harga = view.findViewById(R.id.hargaview);

        initRetrofit();
        return view;

    }

    private void initRetrofit() {
        listMember = view.findViewById(R.id.listMember);
        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter();
        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);

        retrofit =RetrofitUtility.initialieRetrofit();
    }

    private void addData(List<Book> data) {
        List<BookAdapter> bookAdapterList = new ArrayList<>();
        BookAdapter bookAdapter;


        for (Book books : data) {
            Log.e(TAG, "addData: "+ books.getJudul() );
            bookAdapter = new BookAdapter();
            bookAdapter.setId(books.getId());
            bookAdapter.setJudul(books.getJudul());
            bookAdapter.setPenulis(books.getPenulis());
            bookAdapter.setThumb(books.getThumb());
            bookAdapterList.add(bookAdapter);
        }

        memberListAdapter.addAll(bookAdapterList);
    }

    private void getAllBookData() {
        BookApiService apiservice = retrofit.create(BookApiService.class);
        Call<List<com.example.splashlogin.model.Book>> result = apiservice.getAllBuku(AppService.getToken());
        result.enqueue(new Callback<List<com.example.splashlogin.model.Book>>() {
            @Override
            public void onResponse(Call<List<com.example.splashlogin.model.Book>> call, Response<List<com.example.splashlogin.model.Book>> response) {
                addData(response.body());
            }

            @Override
            public void onFailure(Call<List<com.example.splashlogin.model.Book>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

//    public void viewData(String thumb, String judul, String penulis, String penerbit, String tahun, String harga) {
//        Book book = new Book();
//        book.setId(id);
//        book.setHarga(Integer.valueOf(harga));
//        book.setJudul(judul);
//        book.setPenulis(penulis);
//        book.setPenerbit(penerbit);
//        book.setTahun(tahun);
//        book.setThumb(base64Image);
//
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        Call<BookResult> result = apiInterface.getBookById(AppService.getToken(), id);
//        result.enqueue(new Callback<BookResult>() {
//            @Override
//            public void onResponse(Call<BookResult> call, Response<BookResult> response) {
//
//                if (response.body().isSuccess()) {
//                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    Fragment fragment = getFragmentManager().findFragmentByTag("id");
//                    if (fragment != null) {
//                        ft.remove(fragment);
//                    }
//                    ft.addToBackStack(null);
//                    Log.e("TAG", "Fetch Success");
//                } else {
//                    Log.e("TAG", "Fetch Gagal" + response.body().toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<BookResult> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//        });
//
//    }

}