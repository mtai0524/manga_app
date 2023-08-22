package com.example.bietdoidoctruyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "manga.db", null, 262 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE REGISTER(userId INTEGER PRIMARY KEY autoincrement ,Username text, Password text, avatar TEXT, role TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar, role) VALUES ('minh tai', 'admin123', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230724_084059476.jpg', 'admin tối cao')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar, role) VALUES ('cdmtm', '1', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230815_122103926.jpg', 'admin')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar, role) VALUES ('crush my friend', '123', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230815_122135995.jpg', 'user')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar, role) VALUES ('thinhngoo', '123', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230815_122020924.jpg', 'user')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO REGISTER (Username, Password, avatar, role) VALUES ('tam nicotine', '1', 'file:///storage/emulated/0/Android/data/com.example.bietdoidoctruyen/files/DCIM/IMG_20230815_150138066.jpg', 'user')";
        sqLiteDatabase.execSQL(sql);




        sql = "CREATE TABLE Comment(cmtId INTEGER PRIMARY KEY autoincrement ,userId INTERGER, mangaId INTERGER, comment TEXT, FOREIGN KEY (userId) REFERENCES REGISTER(userId), FOREIGN KEY (mangaId) REFERENCES Manga(mangaId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (1, 1, 'truyen cung dui')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (2, 1, 'cũng tạm tạm')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (4, 1, 'binh thuong, co thi doc')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Comment (userId, mangaId, comment) VALUES (1, 1, 'ok')";
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



        sql = "CREATE TABLE Category (categoryId INTEGER PRIMARY KEY autoincrement, categoryName TEXT, type INTEGER)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category ( categoryName, type) VALUES ('phiêu lưu', 1)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName, type) VALUES ('tổng hợp truyện siu hay', 4)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName, type) VALUES ('truyện tranh màu', 1)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName, type) VALUES ('truyện em pé', 1)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName, type) VALUES ('trinh thám', 1)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category ( categoryName, type) VALUES ('truyện chữ', 1)";
        sqLiteDatabase.execSQL(sql);


//
//        sql = "INSERT INTO Category ( categoryName) VALUES ('truyện em pé')";
//        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE MANGA(mangaId integer primary key autoincrement, mangaName text, image text, description TEXT)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Attack on Titan', 'https://flxt.tmsimg.com/assets/p10701949_b_v9_ah.jpg', 'Đại chiến Titan xoay quanh một nền văn minh nằm trong ba bức tường đồ sộ quây tròn đồng tâm, nơi duy nhất mà nhân loại còn tồn tại. Hơn một trăm năm trước, loài người bị đẩy đến bờ vực tuyệt chủng sau sự xuất hiện của một loài sinh vật mang hình người được gọi là Titan, chúng sẽ ăn thịt bất cứ ai mà chúng thấy.')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('One Piece', 'https://m.media-amazon.com/images/M/MV5BODcwNWE3OTMtMDc3MS00NDFjLWE1OTAtNDU3NjgxODMxY2UyXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_FMjpg_UX1000_.jpg', 'One Piece kể về cuộc hành trình của Monkey D. Luffy - thuyền trưởng của băng hải tặc Mũ Rơm và các đồng đội của cậu. Luffy tìm kiếm vùng biển bí ẩn nơi cất giữ kho báu lớn nhất thế giới One Piece, với mục tiêu trở thành Vua Hải Tặc. One Piece cũng được chuyển thể sang một vài loại hình truyền thông khác nhau')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('League of Legends', 'https://cdn.marvel.com/u/prod/marvel/i/mg/c/90/5e4c16dfc6b56/clean.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Demon Slayer', 'https://cdn.vnreview.vn/524288_70849781053950_1632413989994496?wt=a81fc22c3ab2d4f89d7680f47a2c281e&rt=04af1758ec6ffc66e44de4b5ecd9fab3&width=1080', 'Trong Tiểu Thuyết Thanh Gươm Diệt Quỷ: Cánh Bướm Khuyết : Ngược về quá khứ, Himejima từng cứu giúp Kanae vàShinobu bị quỷ sát hại cha mẹ.\n" +
                "\n" +
                "Himejima thương xót và căm ghét số phận. Nếu không có biến cố kia, hẳn Shinobu đã có thể sống hạnh phúc trong tình yêu thương của cha mẹ và chị gái thay vì bị giận dữ và hận thù vây hãm. Tuy nhiên, anh đã quay lưng với lời thỉnh cầu của hai đứa trẻ. Anh không thể để tương lai của hai chị em bị tước đi vì những cảm xúc nhất thời này.\n" +
                "\n" +
                "Vì không muốn hai chị em dấn thân vào con đường nguy hiểm, anh đã đưa ra thử thách hòng ngăn cản cả hai gia nhập Đội Diệt Quỷ, thế nhưng…\n" +
                "\n" +
                "“Cánh bướm khuyết” là cuốn tiểu thuyết gồm 5 câu chuyện vô cùng thú vị, hé lộ về cuộc sống của các “Trụ cột” - Dàn nhân vật nhận được sự quan tâm lớn từ độc giả! Ngoài ra với những ai vốn yêu thích “Học viện Diệt Quỷ”, tập sách này sẽ tiếp tục mang đến cho các bạn những khoảnh khắc vô cùng hài hước và khó quên!!')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('One-Punch Man', 'https://m.media-amazon.com/images/M/MV5BZjJlNzE5YzEtYzQwYS00NTBjLTk5YzAtYzUwOWQyM2E3OGI2XkEyXkFqcGdeQXVyNTgyNTA4MjM@._V1_FMjpg_UX1000_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Chainsaw Man', 'https://cdn0.fahasa.com/media/catalog/product/_/c/_chainsaw_man_vol_01_b_a_o_m_t_2_.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Dectective Conan', 'https://nhasachquangloi.vn/pub/media/catalog/product/cache/3bd4b739bad1f096e12e3a82b40e551a/c/o/conan100-limited.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Tam Mao lưu lạc ký', 'https://i7.bumcheo.info/manga/27/27018/img202105050000.thumb_500x.jpg', 'day la mo ta truyen')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO MANGA ( mangaName, image, description) VALUES ('Sherlock Homes', 'https://m.media-amazon.com/images/I/81tNnqcHxlL._AC_UF1000,1000_QL80_.jpg', 'Sir Arthur Conan Doyle (22/5/1859 – 7/7/1930) là một nhà văn người Scotland nổi tiếng với tiểu thuyết trinh thám Sherlock Holmes. Đây là cuốn tiểu thuyết trinh thám lừng lẫy nhất mọi thời đại. Sherlock Holmes không chỉ xuất hiện trong bộ truyện của Conan Doyle, mà còn được chuyển thể thành các bộ phim điện ảnh từ nhiều quốc gia trên thế giới')";
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
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (2, 8)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (2, 4)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (2, 2)";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (4, 8)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (4, 7)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (4, 2)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (5, 7)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (5, 9)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Category_Manga ( categoryId, mangaId) VALUES (6, 9)";
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

//
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (10, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-1.html', '')";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (11, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-2.html', '')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (12, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-3.html', '')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (13, 'https://truyenqqq.vn/truyen-tranh/one-piece-128-chap-4.html', '')";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (18, '', '<h1>HỒI ỨC CỦA BÁC SĨ JOHN H. WATSON</h1>\n" +
                "<p>&emsp;Tôi lấy bằng bác sĩ ở Đại học London vào năm 1878 rồi chuyển đến Netley để theo khóa đào tạo chỉ định cho bác sĩ ngoại khoa trong quân đội. Học ở đây xong, tôi được chính thức điều về Trung đoàn 5 Bộ binh Northumberland làm quân y phó. Lúc đó trung đoàn này đóng ở Ấn Độ, và tôi chưa kịp trình diện thì cuộc chiến tranh Afghanistan lần hai đã nổ ra. Khi xuống tàu ở Bombay, tôi hay tin quân đoàn của mình đã vượt đèo đi trước và hiện đã tiến sâu vào lãnh thổ của quân địch. Tôi cùng với nhiều sĩ quan khác đồng cảnh ngộ liền đi theo và rồi cũng an toàn tới được Kandahar, nơi tôi tìm được trung đoàn của mình và lập tức bắt đầu nhiệm vụ mới.</p>\n" +
                "<p>&emsp;Chiến dịch này mang lại công trạng và sự thăng tiến cho nhiều người, nhưng với tôi nó chẳng mang lại gì ngoài rủi ro và tai họa. Tôi phải rời lữ đoàn để gia nhập Trung đoàn Berkshire và tôi đã cùng họ tham gia trận chiến sống mái ở Maiwand. Ở trận này tôi bị một viên đạn hỏa mai bắn trúng vai, làm vỡ xương và sượt qua động mạch dưới đòn. Lẽ ra tôi đã sa vào tay quân Ghazi tàn bạo nếu không nhờ lòng tận tuy và can trường của Murray, phụ tá của tôi. Anh ta đã quẳng tôi lên lưng một con ngựa thồ rồi đưa tôi an toàn về đến phòng tuyến quân Anh.</p>\n" +
                "<p>&emsp;Sức cùng lực kiệt vì đau đớn và vì những gian khổ kéo dài đã kinh qua, tôi được chuyển về bệnh viện hậu cứ ở Peshawar cùng với một đoàn thương binh đông đảo. Tại đây, khi tôi hồi phục dần và đã khá tốt để đi lại quanh bệnh xá, thậm chí còn ra được tới hàng hiên sưởi nắng chút ít, thì lại bị quật ngã vì sốt thương hàn - tai ương của thuộc địa Ấn. Suốt mấy tháng, tôi sống trong tuyệt vọng, và cuối cùng khi đã bình tâm và bắt đầu dưỡng bệnh, thì tôi ốm yếu, hốc hác đến nỗi một hội đồng y khoa phải quyết định cho tôi về Anh quốc ngay, không được chậm trễ một ngày nào. Vì thế, tôi được gửi theo tàu chở quân Orontes, và một tháng sau tôi đã bước chân xuống cảng Portsmouth với sức khỏe suy sụp không thể cứu vãn, bù lại tôi được chính quyền mẫu quốc cho phép nghỉ luôn chín tháng để phục hồi.</p>\n" +
                "<p>&emsp;Tôi chẳng có họ hàng thân thích gì ở Anh quốc cho nên tự do như gió trời - hay tự do trong giới hạn mà mức thu nhập mười một đồng shilling và sáu xu một ngày cho phép. Trong hoàn cảnh như thế, tôi đương nhiên phải đi tới London, cái hầm cầu vĩ đại mà mọi kẻ lười nhác và ăn không ngồi rồi khắp cả nước này đều bị hút vào không sao cưỡng lại được. Ở đây, tôi trọ một thời gian tại một khách sạn trên phố Strand, sống một cuộc sống thiếu tiện nghi, vô nghĩa, và vung tay tiêu xài số tiền mình có mà lẽ ra phải dè sẻn hơn nhiều. Tình trạng tài chính đã trở nên đáng ngại tới mức tôi sớm nhận ra rằng: Hoặc là tôi phải bỏ chốn đô thành này mà rút về sống đâu đó ở vùng quê, hoặc là phải thay đổi hoàn toàn lối sống của mình. Chọn cách thứ hai, tôi bắt đầu bằng cách quyết tâm sẽ rời khách sạn, và tá túc ở một chỗ nào đó bớt phô trương và đỡ tốn kém hơn.</p>\n')";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO Content (chapterContentId, imgContent, mangaTxt) VALUES (19, '', '<h1>KHOA HỌC SUY LUẬN</h1>\n" +

                "<p>&emsp;Năm 1878, tôi tốt nghiệp tại trường Đại học Y London, sau đó đến Netley dự khóa tu nghiệp dành cho các bác sỹ quân y.</p>\n" +
                "<p>&emsp;Học xong, tôi được bổ nhiệm về trung đoàn bộ binh Northumberland số 5, khi ấy đang đóng tại Ấn Độ.</p>\n" +
                "<p>&emsp;Tôi chưa kịp tới đơn vị của mình thì cuộc chiến tranh Afghanistan lần thứ hai bùng nổ.</p>\n" +
                "<p>&emsp;Đặt chân lên Bombay, tôi được biết đơn vị và tới được Kandahar an toàn.</p>\n" +
                "<p>&emsp;Tại đây, tôi gặp trung đoàn của mình. Tôi bị chuyển sang trung đoàn Berkshires và tham dự trận đánh Maiwand.</p>\n" +
                "<p>&emsp;Trong trận này, tôi bị thương ở vai, nếu không có người lính hầu xốc tôi lên ngựa và đưa về phía sau chiến tuyến, thì tôi đã rơi vào tay quân địch rồi.</p>\n" +
                "<p>&emsp;Kiệt sức, tôi được đưa về bệnh viện hậu phương Peshawar.</p>\n" +
                "<p>&emsp;Tại đây, tôi bình phục dần, và lúc đó có thể ra sưởi nắng ngoài hiên, thì lại bị thương hàn.</p>\n" +
                "<p>&emsp;Trong hàng tháng trời, người ta ra sức cứu sống tôi; đến ngày khỏi bệnh, tôi gầy yếu đến nổi Cục quân y phải đưa tôi về Anh lập tức.</p>\n" +
                "<p>&emsp;Một tháng sau, tôi đặt chân lên bến cảng Portsmouth, chính phủ cho tôi nghỉ chín tháng để lấy lại sức.</p>\n" +
                "<p>&emsp;Vì không có bà con ở Anh nên tôi được tự do như gió trời, với 11 shillings 6 pence mỗi ngày.</p>\n" +
                "<p>&emsp;Trong tình cảnh đó, tôi bị thu hút về London với một sức mạnh không cưỡng lại nổi.</p>\n" +
                "<p>&emsp;Ở đây, tôi sống ít lâu tại một khách sạn, nhưng chẳng bao lâu tôi thấy mình nên đi tìm một nhà trọ xuyềnh xoàng và đỡ t\n" +
                "<p>&emsp;Chiến dịch này mang lại công trạng và sự thăng tiến cho nhiều người, nhưng với tôi nó chẳng mang lại gì ngoài rủi ro và tai họa. Tôi phải rời lữ đoàn để gia nhập Trung đoàn Berkshire và tôi đã cùng họ tham gia trận chiến sống mái ở Maiwand. Ở trận này tôi bị một viên đạn hỏa mai bắn trúng vai, làm vỡ xương và sượt qua động mạch dưới đòn. Lẽ ra tôi đã sa vào tay quân Ghazi tàn bạo nếu không nhờ lòng tận tuy và can trường của Murray, phụ tá của tôi. Anh ta đã quẳng tôi lên lưng một con ngựa thồ rồi đưa tôi an toàn về đến phòng tuyến quân Anh.</p>\n" +
                "<p>&emsp;Sức cùng lực kiệt vì đau đớn và vì những gian khổ kéo dài đã kinh qua, tôi được chuyển về bệnh viện hậu cứ ở Peshawar cùng với một đoàn thương binh đông đảo. Tại đây, khi tôi hồi phục dần và đã khá tốt để đi lại quanh bệnh xá, thậm chí còn ra được tới hàng hiên sưởi nắng chút ít, thì lại bị quật ngã vì sốt thương hàn - tai ương của thuộc địa Ấn. Suốt mấy tháng, tôi sống trong tuyệt vọng, và cuối cùng khi đã bình tâm và bắt đầu dưỡng bệnh, thì tôi ốm yếu, hốc hác đến nỗi một hội đồng y khoa phải quyết định cho tôi về Anh quốc ngay, không được chậm trễ một ngày nào. Vì thế, tôi được gửi theo tàu chở quân Orontes, và một tháng sau tôi đã bước chân xuống cảng Portsmouth với sức khỏe suy sụp không thể cứu vãn, bù lại tôi được chính quyền mẫu quốc cho phép nghỉ luôn chín tháng để phục hồi.</p>\n" +
                "<p>&emsp;Tôi chẳng có họ hàng thân thích gì ở Anh quốc cho nên tự do như gió trời - hay tự do trong giới hạn mà mức thu nhập mười một đồng shilling và sáu xu một ngày cho phép. Trong hoàn cảnh như thế, tôi đương nhiên phải đi tới London, cái hầm cầu vĩ đại mà mọi kẻ lười nhác và ăn không ngồi rồi khắp cả nước này đều bị hút vào không sao cưỡng lại được. Ở đây, tôi trọ một thời gian tại một khách sạn trên phố Strand, sống một cuộc sống thiếu tiện nghi, vô nghĩa, và vung tay tiêu xài số tiền mình có mà lẽ ra phải dè sẻn hơn nhiều. Tình trạng tài chính đã trở nên đáng ngại tới mức tôi sớm nhận ra rằng: Hoặc là tôi phải bỏ chốn đô thành này mà rút về sống đâu đó ở vùng quê, hoặc là phải thay đổi hoàn toàn lối sống của mình. Chọn cách thứ hai, tôi bắt đầu bằng cách quyết tâm sẽ rời khách sạn, và tá túc ở một chỗ nào đó bớt phô trương và đỡ tốn kém hơn.</p>\n')";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE Rating(userId INTEGER, mangaId INTEGER, rating REAL, ratingContent TEXT, FOREIGN KEY (userId) REFERENCES REGISTER(userId), FOREIGN KEY (mangaId) REFERENCES MANGA(mangaId))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Rating (userId, mangaId, rating, ratingContent) VALUES (1, 1, 1.5, 'vé ri gút')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Rating (userId, mangaId, rating, ratingContent) VALUES (2, 2, 1.5, 'test')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Rating (userId, mangaId, rating, ratingContent) VALUES (1, 3, 1, 'lkjdklsj')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Rating (userId, mangaId, rating, ratingContent) VALUES (1, 2, 2.5, 'lkdajsljd')";
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Rating");

        onCreate(sqLiteDatabase);
    }
}
