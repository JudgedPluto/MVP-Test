package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface PlaceInfoActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String userId, String placeId);
        void onEditButtonClicked();
        void onDeleteButtonClicked();
        void onOrderButtonClicked();
    }

    interface View extends BaseView {
        void updateElements(Object placeName, Object landlordName, Object placePrice, Object placeAvailable, Object placeAddress, Object placePhone, Object placeFeatures);
        void startEditPlaceActivity(String id);
        void startDeletePlaceActivity(String id);
        void startAddPaymentActivity(String userId, String placeId);
        void finishActivity();
    }
}
