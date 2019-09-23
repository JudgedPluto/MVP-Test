package org.firengine.mvp.contract.review;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface ReviewDeleteActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String id);
        void onEDeleteButtonClicked();
    }
    interface View extends BaseView {
        void updateTextViews(Object student_id, Object landlord_id, Object place_id, Object payment_type, Object payment_method, Object payment_amount);
        void startReviewEditActivity(String id);
    }

}
