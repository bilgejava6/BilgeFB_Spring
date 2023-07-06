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

    public static final String BRANS = API+VERSION+"/brans";
    public static final String DOKTOR = API+VERSION+"/doktor";
    public static final String HASTA = API+VERSION+"/hasta";
    public static final String RANDEVU = API+VERSION+"/randevu";


    public static final String SAVE = "/save";
    public static final String SAVEDTO = "/savedto";
    public static final String SAVEDTOMAPPER = "/savedtomapper";
    public static final String SAVEDTOMAPPER2 = "/savedtomapper2";


    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String FINDALL = "/findall";
    public static final String FINDBYAD = "/findbyad";






}
