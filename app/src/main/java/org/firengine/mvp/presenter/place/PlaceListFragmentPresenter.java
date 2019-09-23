package org.firengine.mvp.presenter.place;

import org.firengine.mvp.contract.place.PlaceListFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.view.place.PlaceListFragment;

public class PlaceListFragmentPresenter implements PlaceListFragmentContract.Presenter {

    public PlaceListFragmentPresenter(PlaceListFragment placeListFragment, Injector injector) {

    }

    @Override
    public void onActivityCreated(String filterColumn, String filterValue) {

    }
}
