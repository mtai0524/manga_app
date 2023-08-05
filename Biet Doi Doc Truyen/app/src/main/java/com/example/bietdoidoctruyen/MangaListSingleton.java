    package com.example.bietdoidoctruyen;

    import com.example.bietdoidoctruyen.model.Manga;

    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    public class MangaListSingleton {
        private static MangaListSingleton instance;
        private Set<Manga> mangaSet = new HashSet<>();

        private MangaListSingleton() {
            // Private constructor to prevent instantiation from outside
        }

        public static MangaListSingleton getInstance() {
            if (instance == null) {
                instance = new MangaListSingleton();
            }
            return instance;
        }

        public void addManga(Manga manga) {
            mangaSet.add(manga);
        }

        public List<Manga> getMangaList() {
            return new ArrayList<>(mangaSet);
        }
    }
