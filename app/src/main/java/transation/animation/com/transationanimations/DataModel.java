package transation.animation.com.transationanimations;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {

    private String sno;
    private String name;
    private String image;
    private int image_drawable;



    public DataModel(){

    }

    protected DataModel(Parcel in) {
        sno = in.readString();
        name = in.readString();
        image = in.readString();
        image_drawable = in.readInt();

    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getImage_drawable() {
        return image_drawable;
    }
    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeInt(image_drawable);

    }
}
