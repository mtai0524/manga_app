package com.example.bietdoidoctruyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
//    private static final String TABLE_NAME = "REGISTER"; // neu dinh nghia thi phai dung String format

    /**
     * @return: xin loi anh chiu hong noi
     * @param context
     */
    public DbHelper(@Nullable Context context) {
        super(context, "bietgikhong.db", null, 179);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE REGISTER(userId INTEGER PRIMARY KEY autoincrement ,Username text, Password text, avatar TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar) VALUES ('minh tai', 'admin123', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230724_084059476.jpg')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar) VALUES ('crush my friend', '123', '')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar) VALUES ('thinhngu', '123', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/image:126.jpg')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar) VALUES ('met moi that su', '1', '')";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE Comment(cmtId INTEGER PRIMARY KEY autoincrement ,userId INTERGER, mangaId INTERGER, comment TEXT, FOREIGN KEY (userId) REFERENCES REGISTER(userId), FOREIGN KEY (mangaId) REFERENCES Manga(mangaId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (1, 1, 'truyen cung dui')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (2, 1, 'nhu cai qq , do vay cung doc')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (4, 1, 'binh thuong, co thi doc')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (1, 1, 'ngu thi nin')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (3, 1, 'doc truyen zui ze!!')";
        sqLiteDatabase.execSQL(sql);



        sql = "CREATE TABLE History (historyId INTEGER PRIMARY KEY autoincrement, userId INTERGER, mangaId INTERGER, FOREIGN KEY (userId) REFERENCES REGISTER(Username), FOREIGN KEY (mangaId) REFERENCES Manga(mangaId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO History ( userId, mangaId) VALUES (1, 1)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO History ( userId, mangaId) VALUES (1, 2)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO History ( userId, mangaId) VALUES (1, 3)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO History ( userId, mangaId) VALUES (2, 1)";
        sqLiteDatabase.execSQL(sql);



        sql = "CREATE TABLE Category (categoryId INTEGER PRIMARY KEY autoincrement, categoryName TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category ( categoryName) VALUES ('phiêu lưu')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName) VALUES ('trinh thám')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName) VALUES ('truyện tranh màu')";
        sqLiteDatabase.execSQL(sql);
//
//        sql = "INSERT INTO Category ( categoryName) VALUES ('truyện em pé')";
//        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE MANGA(mangaId integer primary key autoincrement, mangaName text, image text, description TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Attack on Titan', 'https://flxt.tmsimg.com/assets/p10701949_b_v9_ah.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('One Piece', 'https://m.media-amazon.com/images/M/MV5BODcwNWE3OTMtMDc3MS00NDFjLWE1OTAtNDU3NjgxODMxY2UyXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_FMjpg_UX1000_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('League of Legends', 'https://cdn.marvel.com/u/prod/marvel/i/mg/c/90/5e4c16dfc6b56/clean.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Demon Slayer', 'https://cdn.vnreview.vn/524288_70849781053950_1632413989994496?wt=a81fc22c3ab2d4f89d7680f47a2c281e&rt=04af1758ec6ffc66e44de4b5ecd9fab3&width=1080', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('One-Punch Man', 'https://m.media-amazon.com/images/M/MV5BZjJlNzE5YzEtYzQwYS00NTBjLTk5YzAtYzUwOWQyM2E3OGI2XkEyXkFqcGdeQXVyNTgyNTA4MjM@._V1_FMjpg_UX1000_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Chainsaw Man', 'https://cdn0.fahasa.com/media/catalog/product/_/c/_chainsaw_man_vol_01_b_a_o_m_t_2_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Dectective Conan', 'https://nhasachquangloi.vn/pub/media/catalog/product/cache/3bd4b739bad1f096e12e3a82b40e551a/c/o/conan100-limited.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Tam Mao lưu lạc ký', 'https://i7.bumcheo.info/manga/27/27018/img202105050000.thumb_500x.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Sherlock Homes', 'https://m.media-amazon.com/images/I/81tNnqcHxlL._AC_UF1000,1000_QL80_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE Category_Manga (categoryId INTEGER, mangaId INTEGER, PRIMARY KEY (mangaId, categoryId), FOREIGN KEY (mangaId) REFERENCES MANGA(mangaId), FOREIGN KEY (categoryId) REFERENCES Category(categoryId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (1, 1)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (1, 2)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (1, 5)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (1, 8)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (3, 3)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (3, 8)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (2, 9)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (4, 8)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (4, 9)";
        sqLiteDatabase.execSQL(sql);


        sql = "CREATE TABLE Chapter (chapterId INTEGER PRIMARY KEY autoincrement, mangaId INTEGER, chapterName TEXT, FOREIGN KEY (mangaId) REFERENCES MANGA(mangaId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 1')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 2')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 3')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 4')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 5')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 6')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 7')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 8')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (1, 'Chapter 9')";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (2, 'Chapter 2.1')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (2, 'Chapter 2.2')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (2, 'Chapter 2.3')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (2, 'Chapter 2.4')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (2, 'Chapter 2.5')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (3, 'Chapter 1: Dấu ấn tử thần')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (3, 'Chapter 2: Tìm Shen - Mắt hoàng hôn')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (8, 'Chapter 1: Kì án thân thành')";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (9, 'Chapter 1: HỒI ỨC CỦA BÁC SĨ JOHN H. WATSON')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Chapter (mangaId, chapterName) VALUES (9, 'Chapter 2: KHOA HỌC SUY LUẬN')";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE Content (contentId INTEGER PRIMARY KEY autoincrement, chapterContentId INTEGER, imgContent TEXT, mangaTxt TEXT ,FOREIGN KEY (chapterContentId) REFERENCES MANGA(chapterId))";
        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES " +
//                "(1, 'tt1', ''), " +
//                "(1, 'tt2', ''), " +
//                "(1, 'tt3', ''), " +
//                "(1, 'tt4', ''), " +
//                "(1, 'tt5', ''), " +
//                "(1, 'tt6', ''), " +
//                "(1, 'tt7', ''), " +
//                "(1, 'tt8', ''), " +
//                "(1, 'tt9', ''), " +
//                "(1, 'tt10', ''), " +
//                "(1, 'tt11', '')";
//        sqLiteDatabase.execSQL(sql);

//
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (10, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-1.html', '')";
        sqLiteDatabase.execSQL(sql);
//
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (12, '', '<h1>Tiêu đề truyện</h1>\n" +
//                "        <p>&emsp;Trong một buổi sáng nắng đẹp, những tia nắng ấm áp len lỏi qua những cánh cửa sổ nhỏ vào trong căn phòng. Tiếng chim hót líu lo vang lên từ ngoài hiên, tạo nên bầu không khí thật tươi mát và thư giãn</p>\n" +
//                "        <p>&emsp;Nàng công chúa đứng bên cửa sổ, nhìn ra ngoài cảnh vật tươi đẹp của vương quốc. Trong đôi mắt nàng tỏa sáng ánh sáng, hạnh phúc đong đầy trong từng nụ cười. Cuộc sống của nàng đầy hứa hẹn và niềm vui vẫn đang chờ đón nàng ở phía trước.</p>\n" +
//                "        <p>&emsp;Quyền lực và trí tuệ của nàng sẽ được thử thách, nhưng nàng không còn sợ hãi. Trái tim nàng đập mạnh hơn bao giờ hết, bởi nàng biết rằng cô công chúa nhỏ bé kia đã trưởng thành thành một người phụ nữ mạnh mẽ, sẵn sàng đối đầu với bất cứ thử thách nào.</p>\"')";
//        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-2.html', '')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (12, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-3.html', '')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (13, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-4.html', '')";
        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op2', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op3', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op4', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op5', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op6', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op7', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'op8', '')";
//        sqLiteDatabase.execSQL(sql);
//
//
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (9, 'conan1', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (9, 'conan2', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (9, 'conan3', '')";
//        sqLiteDatabase.execSQL(sql);
//
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed1', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed2', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed3', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed4', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed5', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed6', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed6', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed7', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed8', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed9', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed10', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed11', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed12', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed13', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (15, 'zed14', '')";
//        sqLiteDatabase.execSQL(sql);
//
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zedd27', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed28', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed29', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed30', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed31', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed32', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed33', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed34', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed35', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed36', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (16, 'zed37', '')";
//        sqLiteDatabase.execSQL(sql);
//
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm1', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm2', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm3', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm4', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm5', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm6', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm7', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm8', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm9', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm10', '')";
//        sqLiteDatabase.execSQL(sql);
//        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (17, 'tm11', '')";
//        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (18, '', '<h1>HỒI ỨC CỦA BÁC SĨ JOHN H. WATSON</h1>\n" +
                "<p>&emsp;Tôi lấy bằng bác sĩ ở Đại học London vào năm 1878 rồi chuyển đến Netley để theo khóa đào tạo chỉ định cho bác sĩ ngoại khoa trong quân đội. Học ở đây xong, tôi được chính thức điều về Trung đoàn 5 Bộ binh Northumberland làm quân y phó. Lúc đó trung đoàn này đóng ở Ấn Độ, và tôi chưa kịp trình diện thì cuộc chiến tranh Afghanistan lần hai đã nổ ra. Khi xuống tàu ở Bombay, tôi hay tin quân đoàn của mình đã vượt đèo đi trước và hiện đã tiến sâu vào lãnh thổ của quân địch. Tôi cùng với nhiều sĩ quan khác đồng cảnh ngộ liền đi theo và rồi cũng an toàn tới được Kandahar, nơi tôi tìm được trung đoàn của mình và lập tức bắt đầu nhiệm vụ mới.</p>\n" +
                "<p>&emsp;Chiến dịch này mang lại công trạng và sự thăng tiến cho nhiều người, nhưng với tôi nó chẳng mang lại gì ngoài rủi ro và tai họa. Tôi phải rời lữ đoàn để gia nhập Trung đoàn Berkshire và tôi đã cùng họ tham gia trận chiến sống mái ở Maiwand. Ở trận này tôi bị một viên đạn hỏa mai bắn trúng vai, làm vỡ xương và sượt qua động mạch dưới đòn. Lẽ ra tôi đã sa vào tay quân Ghazi tàn bạo nếu không nhờ lòng tận tuy và can trường của Murray, phụ tá của tôi. Anh ta đã quẳng tôi lên lưng một con ngựa thồ rồi đưa tôi an toàn về đến phòng tuyến quân Anh.</p>\n" +
                "<p>&emsp;Sức cùng lực kiệt vì đau đớn và vì những gian khổ kéo dài đã kinh qua, tôi được chuyển về bệnh viện hậu cứ ở Peshawar cùng với một đoàn thương binh đông đảo. Tại đây, khi tôi hồi phục dần và đã khá tốt để đi lại quanh bệnh xá, thậm chí còn ra được tới hàng hiên sưởi nắng chút ít, thì lại bị quật ngã vì sốt thương hàn - tai ương của thuộc địa Ấn. Suốt mấy tháng, tôi sống trong tuyệt vọng, và cuối cùng khi đã bình tâm và bắt đầu dưỡng bệnh, thì tôi ốm yếu, hốc hác đến nỗi một hội đồng y khoa phải quyết định cho tôi về Anh quốc ngay, không được chậm trễ một ngày nào. Vì thế, tôi được gửi theo tàu chở quân Orontes, và một tháng sau tôi đã bước chân xuống cảng Portsmouth với sức khỏe suy sụp không thể cứu vãn, bù lại tôi được chính quyền mẫu quốc cho phép nghỉ luôn chín tháng để phục hồi.</p>\n" +
                "<p>&emsp;Tôi chẳng có họ hàng thân thích gì ở Anh quốc cho nên tự do như gió trời - hay tự do trong giới hạn mà mức thu nhập mười một đồng shilling và sáu xu một ngày cho phép. Trong hoàn cảnh như thế, tôi đương nhiên phải đi tới London, cái hầm cầu vĩ đại mà mọi kẻ lười nhác và ăn không ngồi rồi khắp cả nước này đều bị hút vào không sao cưỡng lại được. Ở đây, tôi trọ một thời gian tại một khách sạn trên phố Strand, sống một cuộc sống thiếu tiện nghi, vô nghĩa, và vung tay tiêu xài số tiền mình có mà lẽ ra phải dè sẻn hơn nhiều. Tình trạng tài chính đã trở nên đáng ngại tới mức tôi sớm nhận ra rằng: Hoặc là tôi phải bỏ chốn đô thành này mà rút về sống đâu đó ở vùng quê, hoặc là phải thay đổi hoàn toàn lối sống của mình. Chọn cách thứ hai, tôi bắt đầu bằng cách quyết tâm sẽ rời khách sạn, và tá túc ở một chỗ nào đó bớt phô trương và đỡ tốn kém hơn.</p>\n')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (19, '', '<h1>KHOA HỌC SUY LUẬN</h1>\n" +
                "<p>&emsp;Tôi lấy bằng bác sĩ ở Đại học London vào năm 1878 rồi chuyển đến Netley để theo khóa đào tạo chỉ định cho bác sĩ ngoại khoa trong quân đội. Học ở đây xong, tôi được chính thức điều về Trung đoàn 5 Bộ binh Northumberland làm quân y phó. Lúc đó trung đoàn này đóng ở Ấn Độ, và tôi chưa kịp trình diện thì cuộc chiến tranh Afghanistan lần hai đã nổ ra. Khi xuống tàu ở Bombay, tôi hay tin quân đoàn của mình đã vượt đèo đi trước và hiện đã tiến sâu vào lãnh thổ của quân địch. Tôi cùng với nhiều sĩ quan khác đồng cảnh ngộ liền đi theo và rồi cũng an toàn tới được Kandahar, nơi tôi tìm được trung đoàn của mình và lập tức bắt đầu nhiệm vụ mới.</p>\n" +
                "<p>&emsp;Chiến dịch này mang lại công trạng và sự thăng tiến cho nhiều người, nhưng với tôi nó chẳng mang lại gì ngoài rủi ro và tai họa. Tôi phải rời lữ đoàn để gia nhập Trung đoàn Berkshire và tôi đã cùng họ tham gia trận chiến sống mái ở Maiwand. Ở trận này tôi bị một viên đạn hỏa mai bắn trúng vai, làm vỡ xương và sượt qua động mạch dưới đòn. Lẽ ra tôi đã sa vào tay quân Ghazi tàn bạo nếu không nhờ lòng tận tuy và can trường của Murray, phụ tá của tôi. Anh ta đã quẳng tôi lên lưng một con ngựa thồ rồi đưa tôi an toàn về đến phòng tuyến quân Anh.</p>\n" +
                "<p>&emsp;Sức cùng lực kiệt vì đau đớn và vì những gian khổ kéo dài đã kinh qua, tôi được chuyển về bệnh viện hậu cứ ở Peshawar cùng với một đoàn thương binh đông đảo. Tại đây, khi tôi hồi phục dần và đã khá tốt để đi lại quanh bệnh xá, thậm chí còn ra được tới hàng hiên sưởi nắng chút ít, thì lại bị quật ngã vì sốt thương hàn - tai ương của thuộc địa Ấn. Suốt mấy tháng, tôi sống trong tuyệt vọng, và cuối cùng khi đã bình tâm và bắt đầu dưỡng bệnh, thì tôi ốm yếu, hốc hác đến nỗi một hội đồng y khoa phải quyết định cho tôi về Anh quốc ngay, không được chậm trễ một ngày nào. Vì thế, tôi được gửi theo tàu chở quân Orontes, và một tháng sau tôi đã bước chân xuống cảng Portsmouth với sức khỏe suy sụp không thể cứu vãn, bù lại tôi được chính quyền mẫu quốc cho phép nghỉ luôn chín tháng để phục hồi.</p>\n" +
                "<p>&emsp;Tôi chẳng có họ hàng thân thích gì ở Anh quốc cho nên tự do như gió trời - hay tự do trong giới hạn mà mức thu nhập mười một đồng shilling và sáu xu một ngày cho phép. Trong hoàn cảnh như thế, tôi đương nhiên phải đi tới London, cái hầm cầu vĩ đại mà mọi kẻ lười nhác và ăn không ngồi rồi khắp cả nước này đều bị hút vào không sao cưỡng lại được. Ở đây, tôi trọ một thời gian tại một khách sạn trên phố Strand, sống một cuộc sống thiếu tiện nghi, vô nghĩa, và vung tay tiêu xài số tiền mình có mà lẽ ra phải dè sẻn hơn nhiều. Tình trạng tài chính đã trở nên đáng ngại tới mức tôi sớm nhận ra rằng: Hoặc là tôi phải bỏ chốn đô thành này mà rút về sống đâu đó ở vùng quê, hoặc là phải thay đổi hoàn toàn lối sống của mình. Chọn cách thứ hai, tôi bắt đầu bằng cách quyết tâm sẽ rời khách sạn, và tá túc ở một chỗ nào đó bớt phô trương và đỡ tốn kém hơn.</p>\n')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE REGISTER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MANGA");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Chapter");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Content");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Category");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS History");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Comment");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Category_Manga");

        onCreate(sqLiteDatabase);
    }
}
