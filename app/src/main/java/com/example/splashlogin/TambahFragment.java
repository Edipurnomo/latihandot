package com.example.splashlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.splashlogin.API.BookApiService;
import com.example.splashlogin.model.Book;
import com.example.splashlogin.model.LoginResult;
import com.example.splashlogin.rest.AppService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TambahFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int PICK_IMAGE = 1;
    private String base64Image = "";
    private View view;
    private String TAG = "TambahFragment";
    private EditText judulbuku, penulis, penerbit, tahun, harga;
    private ImageView imageView;
    private String bas64Image;
    private Retrofit retrofit;
    Button addimage;
    Button send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tambah, container, false);
        judulbuku = view.findViewById(R.id.judul);
        penulis = view.findViewById(R.id.penulis);
        penerbit = view.findViewById(R.id.penerbit);
        tahun = view.findViewById(R.id.tahun);
        harga = view.findViewById(R.id.harga);
        addimage = view.findViewById(R.id.addimage);
        send = view.findViewById(R.id.send);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Add Text", Toast.LENGTH_SHORT).show();
                imageChooser();
            }
        });


        return view;
    }


    private void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }



    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();

        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
    return encImage;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();
        InputStream imageStream;
        String encodeImage = "";

        try {
            imageStream = getContext().getContentResolver().openInputStream(uri);
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            encodeImage = encodeImage(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        base64Image = encodeImage;

    }

    private void sendData (String judul, String penulis, String penerbit, String tahun, String harga) {
        Book book = new Book();
        book.setHarga(Integer.valueOf(harga));
        book.setJudul(judul);
        book.setPenulis(penulis);
        book.setPenerbit(penerbit);
        book.setTahun(Integer.valueOf(tahun));
        book.setThumb(base64Image);

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<LoginResult> result = apiService.insertNewBook(AppService.getToken(), book);
        result.enqueue(new Callback<LoginResult>()  {

            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.body().isSuccess()) {
                    Log.e("TAG", "Berhasil Tambah buku baru ");
                } else

                { Log.e("TAG", "gagal add buku baru "); }
        }
                @Override
                public void onFailure (Call <LoginResult> call, Throwable t){
               t.printStackTrace();
                }
            });
        }





    // TODO: Rename and change types of parameters


    public TambahFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static TambahFragment newInstance(String param1, String param2) {
        TambahFragment fragment = new TambahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

}