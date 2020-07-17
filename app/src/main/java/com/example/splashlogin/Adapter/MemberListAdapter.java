package com.example.splashlogin.Adapter;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashlogin.R;

import java.util.ArrayList;
import java.util.List;

public class MemberListAdapter  extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    Dialog myDialog;

    private List<BookAdapter> bookAdapterList;
    public MemberListAdapter() {
        bookAdapterList = new ArrayList<>();
    }
    private void add(BookAdapter item) {
        bookAdapterList.add(item);
        notifyItemInserted(bookAdapterList.size() - 1);
    }
    public void addAll(List<BookAdapter> bookAdapterList) {
        for (BookAdapter bookAdapter : bookAdapterList) {
            add(bookAdapter);
        }
    }
    @Override
    public MemberViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        final MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        myDialog = new Dialog(parent.getContext());
        myDialog.setContentView(R.layout.fragment_dialog);

        memberViewHolder.listbuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_judul_tv = myDialog.findViewById(R.id.judulview);
                TextView dialog_penulis_tv = myDialog.findViewById(R.id.penulisview);
               ImageView dialog_thumb_img = myDialog.findViewById(R.id.imageView2);
                TextView dialog_penerbit_tv = myDialog.findViewById(R.id.penerbitviewt);
                TextView dialog_tahun_tv = myDialog.findViewById(R.id.tahunview);
                TextView dialog_harga_tv = myDialog.findViewById(R.id.hargaview);

                dialog_judul_tv.setText(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getJudul());
                dialog_penulis_tv.setText(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getPenulis());
                dialog_penerbit_tv.setText(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getPenerbit());
                dialog_tahun_tv.setText(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getTahun());
                dialog_harga_tv.setText(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getHarga());
                dialog_thumb_img.setImageBitmap(getBitmap(bookAdapterList.get(memberViewHolder.getAdapterPosition()).getThumb()));

//                Toast.makeText(parent.getContext(),"Test Click" + String.valueOf(memberViewHolder.getAdapterPosition()),Toast.LENGTH_LONG).show();

                myDialog.show();
            }
        });

        return memberViewHolder;
    }


    private Bitmap getBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    @Override
    public void onBindViewHolder(MemberViewHolder holder, final int position) {
        final BookAdapter bookAdapter = bookAdapterList.get(position);
        Bitmap bitmap = getBitmap(bookAdapter.getThumb());
        holder.bookThumb.setImageBitmap(bitmap);
        holder.judul.setText(bookAdapter.getJudul());
        holder.penulis.setText(bookAdapter.getPenulis());
        holder.bookThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onBindViewHolder: " + bookAdapterList.get(position).getId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return bookAdapterList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
    static class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView bookThumb;
        TextView judul;
        TextView penulis;

        int id;
        CardView listbuku;
        public MemberViewHolder(View itemView) {
            super(itemView);
            bookThumb = itemView.findViewById(R.id.thumb);
            listbuku = itemView.findViewById(R.id.list_buku);
            judul = itemView.findViewById(R.id.judul);
            penulis = itemView.findViewById(R.id.penulis);
        }
    }
}