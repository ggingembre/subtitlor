package com.subtitlor.DAO;

import java.util.ArrayList;

import com.subtitlor.beans.Original;

public interface OriginalDao {

    void add( Original original );
    Original getOneOriginal(long id, String fileName);
    ArrayList<Original> getOriginals(String fileName);
    ArrayList<Original> listAll();
    void update(long id, Original original);
    void delete(String pathName);
	
}
