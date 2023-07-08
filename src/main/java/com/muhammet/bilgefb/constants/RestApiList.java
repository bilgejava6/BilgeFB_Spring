package com.muhammet.bilgefb.constants;

public class RestApiList {
    /**
     * Projeler genellikle belli sunucular ve belli portlar üzerinde çalışırlar.
     * büyük projelerde ekipler ayrışır ve farklı ekipler farklı end pointler ile
     * istek atarlar. Bu ayrışmayı daha kontrollü yapmak için sabitleri yönetmek
     * iyi bir fikirdir.
     */
    public static final String API = "/api";
    public static final String TEST = "/test";
    public static final String PROD = "/prod";

    public static final String VERSION = "/v1";

    public static final String COMMENT = API+VERSION+"/comment";
    public static final String DISLIKE = API+VERSION+"/dislike";
    public static final String FOLLOWER = API+VERSION+"/follower";
    public static final String FOLLOWING = API+VERSION+"/following";
    public static final String LIKE = API+VERSION+"/like";
    public static final String POST = API+VERSION+"/post";
    public static final String USER = API+VERSION+"/user";
    public static final String ONLINE = API+VERSION+"/online";



    public static final String SAVE = "/save";
    public static final String SAVEDTO = "/savedto";
    public static final String SAVEDTOMAPPER = "/savedtomapper";
    public static final String SAVEDTOMAPPER2 = "/savedtomapper2";


    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String FINDALL = "/findall";
    public static final String FINDBYAD = "/findbyad";



    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";






}
