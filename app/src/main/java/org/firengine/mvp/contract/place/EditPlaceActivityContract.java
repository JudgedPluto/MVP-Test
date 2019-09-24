package org.firengine.mvp.contract.place;


import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;



public interface EditPlaceActivityContract {
    interface Presenter extends BasePresenter{
        void onActivityCreated(String id);
        void onEditButtonClicked(String placeName, String placePrice, String placeAvailable, String placeAddress, String placePhone, String placeFeatures);
    }

    interface View extends BaseView{
        void updateFormElements(Object placeName, Object landlordName, Object placePrice, Object placeAvailable, Object placeAddress, Object placePhone, Object placeFeatures);
        void finishActivity();
    }
}
