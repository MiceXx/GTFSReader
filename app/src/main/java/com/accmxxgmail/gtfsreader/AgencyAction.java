package com.accmxxgmail.gtfsreader;

/**
 * Created by Michael on 08-Jul-2017.
 */

public class AgencyAction {

    private int label;
    private String data;
    private int type;

    public static final int ACTION_CALL = 1;
    public static final int ACTION_VIEW = 2;

    public AgencyAction(int label, String data, int type) {
        super();
        this.label = label;
        this.data = data;
        this.type = type;
    }

    public int getLabel(){
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
