package MVVM.Parts.Model;

import java.util.*;

public class SettingModel implements IMVVM_Model {
    boolean isPopAutomatically;
    boolean isSpeechAutomatically;
    int maxShowCount;
    EDefaultURL defaultURL;
    List<String> savedVocabulary;

    public SettingModel() {
        this.isPopAutomatically = false;
        this.isSpeechAutomatically = false;
        this.maxShowCount = 5;
        this.defaultURL = EDefaultURL.Voc;
        this.savedVocabulary = new ArrayList<>();
    }

    public SettingModel(boolean isPopAutomatically, boolean isSpeechAutomatically, int maxShowCount, EDefaultURL defaultURL, List<String> savedVocabulary) {
        this.isPopAutomatically = isPopAutomatically;
        this.isSpeechAutomatically = isSpeechAutomatically;
        this.maxShowCount = maxShowCount;
        this.defaultURL = defaultURL;
        this.savedVocabulary = savedVocabulary;
    }

    public boolean isPopAutomatically() {
        return isPopAutomatically;
    }

    public void setPopAutomatically(boolean popAutomatically) {
        isPopAutomatically = popAutomatically;
    }

    public boolean isSpeechAutomatically() {
        return isSpeechAutomatically;
    }

    public void setSpeechAutomatically(boolean speechAutomatically) {
        isSpeechAutomatically = speechAutomatically;
    }

    public int getMaxShowCount() {
        return maxShowCount;
    }

    public void setMaxShowCount(int maxShowCount) {
        this.maxShowCount = maxShowCount;
    }

    public EDefaultURL getDefaultURL() {
        return defaultURL;
    }

    public void setDefaultURL(EDefaultURL defaultURL) {
        this.defaultURL = defaultURL;
    }

    public List<String> getSavedVocabulary() {
        return savedVocabulary;
    }

    public void setSavedVocabulary(List<String> savedVocabulary) {
        this.savedVocabulary = savedVocabulary;
    }
}
