package com.subtitlor.DAO;

import java.util.ArrayList;
import java.util.List;

import com.subtitlor.beans.Translation;
import com.subtitlor.beans.Language;

public interface TranslationDao {
	
    void add( Translation translation );
    Translation getOneTranslation(long id, String fileName, Language language);
    ArrayList<Translation> getTranslations(String fileName, Language language);
    ArrayList<Translation> listAll();
    void update(Translation translation);
    void delete(String pathName);
    int count(String fileName, String language);

}
