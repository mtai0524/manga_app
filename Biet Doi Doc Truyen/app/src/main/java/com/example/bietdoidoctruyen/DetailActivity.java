package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bietdoidoctruyen.activity.LoginActivity;
import com.example.bietdoidoctruyen.adapter.ChapterAdapter;
import com.example.bietdoidoctruyen.dao.CommentDAO;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.Comment;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvItem;
    ImageView imgItemDetail;
    RecyclerView rcvChapterList;
    private List<Chapter> mChapters;
    private TextView tvDescription;
    private TextView tvComment;
    Manga manga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        manga = (Manga) bundle.get("object_user");
        tvItem = findViewById(R.id.tv_detail_item);
        imgItemDetail = findViewById(R.id.img_item_detail);
        tvDescription = findViewById(R.id.tv_descripion);
        tvComment = findViewById(R.id.tv_comment);
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupComment(Gravity.BOTTOM);
            }
        });

        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupLogo(Gravity.BOTTOM);
            }
        });
//
//        String storyText = "Cinderella\n\nOnce upon a time, in a faraway kingdom, " +
//                "there lived a kind-hearted girl named Cinderella. She was forced " +
//                "to work as a servant for her wicked stepmother and stepsisters. " +
//                "Despite her difficult circumstances, Cinderella remained gentle " +
//                "and kind to everyone she met.\n\nOne day, the king announced a " +
//                "grand ball at the palace. All the young ladies in the kingdom " +
//                "were invited, including Cinderella's stepsisters. With excitement " +
//                "in their hearts, they began to prepare for the ball, leaving " +
//                "Cinderella behind to tend to the household chores.\n\nFeeling " +
//                "lonely and sad, Cinderella's tears caught the attention of her " +
//                "fairy godmother, who appeared before her. With a wave of her wand, " +
//                "the fairy godmother transformed Cinderella's rags into a beautiful " +
//                "ballgown and turned a pumpkin into a magnificent carriage. " +
//                "However, she warned Cinderella that the magic would wear off at " +
//                "midnight.\n\nOverwhelmed with joy, Cinderella arrived at the " +
//                "palace and captured the prince's heart with her beauty and grace. " +
//                "But as the clock struck midnight, she had to flee, leaving behind " +
//                "her glass slipper.\n\nThe prince was determined to find the " +
//                "mysterious girl who had stolen his heart. He searched the kingdom, " +
//                "trying the glass slipper on every young lady until he reached " +
//                "Cinderella's home. As she tried on the slipper, it fit perfectly, " +
//                "revealing her true identity.\n\nThe prince and Cinderella were " +
//                "reunited, and they lived happily ever after, never forgetting " +
//                "the magical night that brought them together.";


//
//// Đoạn văn bản truyện chữ với tiêu đề và thụt đầu dòng
//        String storyText = "<h1>Tiêu đề truyện</h1>"
//                + "<p>&emsp;Trong một buổi sáng nắng đẹp, những tia nắng ấm áp len lỏi qua những cánh cửa sổ nhỏ vào trong căn phòng. Tiếng chim hót líu lo vang lên từ ngoài hiên, tạo nên bầu không khí thật tươi mát và thư giãn"
//                + "&emsp;Nàng công chúa đứng bên cửa sổ, nhìn ra ngoài cảnh vật tươi đẹp của vương quốc. Trong đôi mắt nàng tỏa sáng ánh sáng, hạnh phúc đong đầy trong từng nụ cười. Cuộc sống của nàng đầy hứa hẹn và niềm vui vẫn đang chờ đón nàng ở phía trước..</p>"
//                + "<p>&emsp;Quyền lực và trí tuệ của nàng sẽ được thử thách, nhưng nàng không còn sợ hãi. Trái tim nàng đập mạnh hơn bao giờ hết, bởi nàng biết rằng cô công chúa nhỏ bé kia đã trưởng thành thành một người phụ nữ mạnh mẽ, sẵn sàng đối đầu với bất cứ thử thách nào..</p>";
//
//// Chuyển đoạn văn bản có thẻ HTML thành chuỗi hiển thị được trong TextView
//        Spanned spannedText = Html.fromHtml(storyText);
//
//// Gán chuỗi đã chuyển đổi vào TextView
//
//
//
//        tvDescription.setText(spannedText);
//        // Cho phép người dùng có thể chọn và sao chép nội dung.
//        tvDescription.setTextIsSelectable(true);
//
//// Cho phép kích hoạt sự kiện khi người dùng nhấn giữ trên EditText (long click).
//        tvDescription.setLongClickable(true);
//
//// Ngăn việc thay đổi nội dung trong EditText.
//        tvDescription.setKeyListener(null);

        tvItem.setText(manga.getMangaName());
        String imageUrl = manga.getImage();

// Sử dụng Glide để tải hình ảnh từ URL và hiển thị lên ImageView
        Glide.with(DetailActivity.this)
                .load(imageUrl)
                .into(imgItemDetail);


        mChapters = (List<Chapter>) bundle.getSerializable("object_chapters");
        if (mChapters != null) {
            mChapters.forEach(chapter -> Log.d("Chapter", chapter.getChapterName()));
        }


        rcvChapterList = findViewById(R.id.rcv_chapter_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvChapterList.setLayoutManager(linearLayoutManager);
        ChapterAdapter chapterAdapter = new ChapterAdapter(this);
        chapterAdapter.setData(mChapters);
        rcvChapterList.setAdapter(chapterAdapter);
    }

    private void updateDialogContent() {
        List<Comment> commentList = commentDAO.getCommentByMangaId(manga.getIdManga());
        StringBuilder stringBuilder = new StringBuilder();
        RegisterDAO registerDAO = new RegisterDAO(DetailActivity.this);

        for (Comment comment : commentList) {
            String name = registerDAO.getUsernameByUserId(comment.getUserId());
            String myName = "tôi";
            String nameString = name;

            if (LoginActivity.getUserId() == comment.getUserId()) {
                nameString = myName;
            }

            SpannableString spannableName = new SpannableString(nameString);
            spannableName.setSpan(new RelativeSizeSpan(0.8f), 0, nameString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            stringBuilder.append(spannableName).append(" hiện lên và nói:\n");
            stringBuilder.append(comment.getComment()).append("\n\n");
        }

        String resultString = stringBuilder.toString();
        tvContent.setText(resultString);
    }
    TextView tvContent;
    CommentDAO commentDAO;
    TextView tvUserCmt;
    RegisterDAO registerDAO = new RegisterDAO(DetailActivity.this);
    @SuppressLint("SetTextI18n")
    private void turnOnPopupComment(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_comment);
        tvContent = dialog.findViewById(R.id.tvContent);
        tvUserCmt = dialog.findViewById(R.id.tv_user_cmt);

        commentDAO = new CommentDAO(DetailActivity.this);

        updateDialogContent();

        tvUserCmt.setText("Bình luận\n" + registerDAO.getUsernameByUserId(LoginActivity.getUserId()));

// Convert the StringBuilder to a String



        String storyText = "<h1>Tiêu đề truyện</h1>"
                + "<p>&emsp;Trong một buổi sáng nắng đẹp, những tia nắng ấm áp len lỏi qua những cánh cửa sổ nhỏ vào trong căn phòng. Tiếng chim hót líu lo vang lên từ ngoài hiên, tạo nên bầu không khí thật tươi mát và thư giãn"
                + "<p>&emsp;Quyền lực và trí tuệ của nàng sẽ được thử thách, nhưng nàng không còn sợ hãi. Trái tim nàng đập mạnh hơn bao giờ hết, bởi nàng biết rằng cô công chúa nhỏ bé kia đã trưởng thành thành một người phụ nữ mạnh mẽ, sẵn sàng đối đầu với bất cứ thử thách nào..</p>";

// Chuyển đoạn văn bản có thẻ HTML thành chuỗi hiển thị được trong TextView
        Spanned spannedText = Html.fromHtml(storyText);

// Gán chuỗi đã chuyển đổi vào TextView




//        tvContent.setText(spannedText);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        EditText etCmt = dialog.findViewById(R.id.et_cmt);
        Button btCmt = dialog.findViewById(R.id.btn_cmt);
        btCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt = etCmt.getText().toString();
                commentDAO.addComment(LoginActivity.getUserId(), manga.getIdManga(), cmt);
                Toast.makeText(DetailActivity.this, "da binh luan", Toast.LENGTH_SHORT).show();
                updateDialogContent();
                etCmt.setText("");
                etCmt.setFocusable(true);
            }
        });
        dialog.show();
    }


    private void turnOnPopupLogo(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_popup);
        TextView tvContent = dialog.findViewById(R.id.tvContent);



        String storyText = "<h1>Tiêu đề truyện</h1>"
                + "<p>&emsp;Trong một buổi sáng nắng đẹp, những tia nắng ấm áp len lỏi qua những cánh cửa sổ nhỏ vào trong căn phòng. Tiếng chim hót líu lo vang lên từ ngoài hiên, tạo nên bầu không khí thật tươi mát và thư giãn"
                + "<p>&emsp;Quyền lực và trí tuệ của nàng sẽ được thử thách, nhưng nàng không còn sợ hãi. Trái tim nàng đập mạnh hơn bao giờ hết, bởi nàng biết rằng cô công chúa nhỏ bé kia đã trưởng thành thành một người phụ nữ mạnh mẽ, sẵn sàng đối đầu với bất cứ thử thách nào..</p>";

// Chuyển đoạn văn bản có thẻ HTML thành chuỗi hiển thị được trong TextView
        Spanned spannedText = Html.fromHtml(storyText);

// Gán chuỗi đã chuyển đổi vào TextView




        tvContent.setText(spannedText);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        dialog.show();
    }

//    private List<Chapter> listChapter() {
//        List<Chapter> chapterList = new ArrayList<>();
//        chapterList.add(new Chapter("Chapter 1"));
//        chapterList.add(new Chapter("Chapter 2"));
//        chapterList.add(new Chapter("Chapter 3"));
//        if(category.getMangaName().equals("banh bao chien")){
//            chapterList.add(new Chapter("Chapter 3"));
//        }
//        return chapterList;
//    }

//    public List<Chapter> showOnChaptersList(){
//        List<Chapter> chapters = new ArrayList<>();
//        if (category.getMangaName().equals("banh bao chien")) {
//            Chapter chapter1 = new Chapter("Chapter 1.1 bbc taiusoica");
//            chapters.add(chapter1);
//            Chapter chapter2 = new Chapter("Chapter 1.2 bbc");
//            chapters.add(chapter2);
//        } else if (category.getMangaName().equals("ngo khong")) {
//            Chapter chapter3 = new Chapter("Chapter 2.1 met moi");
//            chapters.add(chapter3);
//            Chapter chapter4 = new Chapter("Chapter 2.2");
//            chapters.add(chapter4);
//            Chapter chapter5 = new Chapter("Chapter 2.2");
//            chapters.add(chapter5);
//            Chapter chapter6 = new Chapter("Chapter 2.2");
//            chapters.add(chapter6);
//            Chapter chapter7 = new Chapter("Chapter 2.2");
//            chapters.add(chapter7);
//            Chapter chapter8 = new Chapter("Chapter 2.2");
//            chapters.add(chapter8);
//            Chapter chapter9 = new Chapter("Chapter 2.2");
//            chapters.add(chapter9);
//            Chapter chapter10 = new Chapter("Chapter 2.2");
//            chapters.add(chapter10);
//            Chapter chapter11 = new Chapter("Chapter 2.2");
//            chapters.add(chapter11);
//        }
//        return chapters;
//    }
}