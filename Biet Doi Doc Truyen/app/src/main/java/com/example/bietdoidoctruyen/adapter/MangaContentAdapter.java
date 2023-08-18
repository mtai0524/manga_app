package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.model.MangaContent;

import java.util.List;

public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MangaContentHolder> {
    private List<MangaContent> mangaContentList;
    private Context mContext;

    public MangaContentAdapter(List<MangaContent> mangaContentList, Context mContext) {
        this.mangaContentList = mangaContentList;
        this.mContext = mContext;
    }
    public MangaContentAdapter(Context context){
        this.mContext = context;
    }
    public MangaContentAdapter(){

    }

    public void setData(Context context, List<MangaContent> list){
        this.mContext = context;
        this.mangaContentList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MangaContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga_content, parent,false);
        return new MangaContentHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MangaContentHolder holder, int position) {
        MangaContent mangaContent = mangaContentList.get(position);


        if(mangaContent == null) return;


        // Cho phép JavaScript trong WebView (tuỳ chọn)
        WebSettings webSettings = holder.imgMangaContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setBuiltInZoomControls(true);




        String url = mangaContent.getImgContent(); // Thay đổi đường dẫn này thành trang web bạn muốn hiển thị
        holder.imgMangaContent.loadUrl(url);


//        if (url == null || url.isEmpty()) {
//            holder.imgMangaContent.setVisibility(View.GONE); // Ẩn ImageView nếu imgName rỗng
//        } else {
//
//            }
//            holder.imgMangaContent.setVisibility(View.VISIBLE); // Hiển thị ImageView nếu có imgName
//            holder.imgMangaContent.setImageResource(res);
//        }

        String mgTxt = mangaContent.getMangaTxt();
        Spanned spanned = Html.fromHtml(mgTxt);
        holder.tvMangaTxt.setText(spanned);

        holder.tvMangaTxt.setTextIsSelectable(true);

// Cho phép kích hoạt sự kiện khi người dùng nhấn giữ trên EditText (long click).
        holder.tvMangaTxt.setLongClickable(true);

// Ngăn việc thay đổi nội dung trong EditText.
        holder.tvMangaTxt.setKeyListener(null);

//        holder.imgMangaContent.setImageResource(mangaContent.getImageMangaId());
    }

    @Override
    public int getItemCount() {
        if(mangaContentList != null){
            return mangaContentList.size();
        }
        return 0;
    }

    public class MangaContentHolder extends RecyclerView.ViewHolder{
        private WebView imgMangaContent;
        private LinearLayout layoutItemMangaContent;
        private EditText tvMangaTxt;

        public MangaContentHolder(@NonNull View itemView) {
            super(itemView);
            imgMangaContent = itemView.findViewById(R.id.img_manga_content);
            layoutItemMangaContent = itemView.findViewById(R.id.layout_item_manga_content);
            tvMangaTxt = itemView.findViewById(R.id.tv_manga_text);

        }
    }
}
