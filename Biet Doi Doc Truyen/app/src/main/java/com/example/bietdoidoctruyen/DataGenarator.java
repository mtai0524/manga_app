//package com.example.bietdoidoctruyen;
//
//import android.content.Context;
//
//import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
//import com.example.bietdoidoctruyen.dao.CategoryDAO;
//import com.example.bietdoidoctruyen.model.Category;
//import com.example.bietdoidoctruyen.model.ListData;
//import com.example.bietdoidoctruyen.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataGenarator {
//    private List<Category> listCategory;
//
//    public List<Category> getListCategory() {
//        return listCategory;
//    }
//
//    public void setListCategory(List<Category> listCategory) {
//        this.listCategory = listCategory;
//    }
//
//    public List<ListData> getListData (){
//        List<ListData> listData = new ArrayList<>();
//        listCategory = new ArrayList<>();
//        listCategory.add(new Category(R.drawable.poster2, "ngo khong"));
//        listCategory.add(new Category(R.drawable.poster1, "banh bao chien"));
//        listCategory.add(new Category(R.drawable.poster4, "hat dau nho"));
//        listCategory.add(new Category(R.drawable.poster3, "itachiyeuminhem"));
//        listCategory.add(new Category(R.drawable.poster6, "leesin"));
//        listCategory.add(new Category(R.drawable.poster6, "leesin"));
//        listCategory.add(new Category(R.drawable.mg1, "narutobaco"));
//        listCategory.add(new Category(R.drawable.mm2, "sasuketamin"));
//
//        List<Category> listCategory2 = new ArrayList<>();
//        listCategory2.add(new Category(R.drawable.poster1, "ngo khong"));
//        listCategory2.add(new Category(R.drawable.poster2,"hat dau nho"));
//        listCategory2.add(new Category(R.drawable.mm2,"sasuketamin"));
//
//
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(""));
//
//        listData.add(new ListData(ListDataAdapter.TYPE_CATEGORY, listCategory, null ,"cate 1"));
//        listData.add(new ListData(ListDataAdapter.TYPE_ITEM, listCategory, null,"cate 2"));
//        listData.add(new ListData(ListDataAdapter.TYPE_USER, null, userList,null));
//        listData.add(new ListData(ListDataAdapter.TYPE_CATEGORY, listCategory2, null,"cate 3"));
//
//        return listData;
//    }
//}
